package pro0.battleship.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro0.battleship.models.AttackResult;
import pro0.battleship.models.Board;
import pro0.battleship.models.BoardCell;
import pro0.battleship.models.BoardPosition;
import pro0.battleship.models.BoardRow;
import pro0.battleship.models.GameStartNotification;
import pro0.battleship.models.Ship;
import pro0.battleship.models.ShipPlacementResponse;
import pro0.battleship.models.ShipSunkNotification;
import pro0.battleship.models.TurnChangeNotification;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;

@RestController
@RequestMapping("/gamestate")
public class GameObjectServlet {

	@Autowired
	GameJpaRepository gameJpaRepository;
	@Autowired
	BoardJpaRepository boardJpaRepository;
	@Autowired
	BoardRowJpaRepository boardRowJpaRepository;
	@Autowired
	BoardCellJpaRepository boardCellJpaRepository;
	@Autowired
	UserJpaRepository userJpaRepository;
	@Autowired
	ShipJpaRepository shipJpaRepository;

//	@RequestMapping(path="/myBoard/{gameId}", method=RequestMethod.GET)
//	public Board sendUsersBoard(@PathVariable int gameId) {
//		return null;
//	}
//	@RequestMapping(path="/opponentBoard/{gameId}", method=RequestMethod.GET)
//	public Board sendOpponentBoard(@PathVariable int gameId) {
//		return null;
//	}
	@GetMapping(path = "/row/{gameId}/{row}/{opponent}")
	public BoardRow sendBoardRow(@PathVariable int gameId, @PathVariable int row, @PathVariable boolean opponent,
			Principal prn) {
		String username = prn.getName();
		String hostUsername = gameJpaRepository.getHostUsername(gameId);
		String guestUsername = gameJpaRepository.getGuestUsername(gameId);
		String searchUsername = null;
		if (username == hostUsername) {
			if (opponent) {
				searchUsername = guestUsername;
			} else {
				searchUsername = hostUsername;
			}
		} else {
			if (opponent) {
				searchUsername = hostUsername;
			} else {
				searchUsername = guestUsername;
			}
		}
		List<BoardRow> boardRows = boardRowJpaRepository.searchByGameAndRowNumber(gameId, row,
				userJpaRepository.findById(searchUsername).orElse(null));
		if (boardRows.size() > 0) {
			return boardRows.get(0);
		}
		System.out.println("SOMETHING WENT VERY WRONG. THIS SHOULD NEVER HAPPEN.");
		return null;
	}

	@GetMapping(path = "/ships/{gameId}")
	public List<ShipPlacementResponse> sendUsersShips(@PathVariable int gameId, Principal prn) {
		String username = prn.getName();
		List<Ship> ships = shipJpaRepository.getShipsByGameId(gameId,
				userJpaRepository.findById(username).orElse(null));
		List<ShipPlacementResponse> shipPlacementResponses = new ArrayList<ShipPlacementResponse>();
		for (Ship ship : ships) {
			if (ship.getPosition() != null) {
				shipPlacementResponses.add(new ShipPlacementResponse(true, ship.getSpaceCoords()));
			}
		}
		return shipPlacementResponses;
	}

	@GetMapping(path = "/attacks/{gameId}")
	public List<AttackResult> sendAttackResults(@PathVariable int gameId, Principal prn) {
		List<AttackResult> results = new ArrayList<AttackResult>();
		List<Board> boards = boardJpaRepository.findByGame(gameId);
		for (Board board : boards) {
			for (BoardRow row : board.getRows()) {
				for (int i = 0; i < 10; i++) {
					BoardCell cell = row.getCells().get(i);
					if (cell.getTargeted()) {
						BoardPosition position = new BoardPosition(i, row.getRowNumber());
						results.add(new AttackResult(board.getUser().getUsername(), position, cell.isHit()));
					}
				}
			}
		}
		return results;
	}

	@GetMapping(path = "/destroyed/{gameId}")
	public List<ShipSunkNotification> sendDestroyedShips(@PathVariable int gameId, Principal prn) {
		List<Ship> ships = shipJpaRepository.getShipsByGameId(gameId);
		List<ShipSunkNotification> shipSunkNotifications = new ArrayList<ShipSunkNotification>();
		GameplayController gc = new GameplayController(gameId, gameJpaRepository, boardJpaRepository,
				boardRowJpaRepository, boardCellJpaRepository, shipJpaRepository);
		for (Ship ship : ships) {
			if (gc.shipIsSunk(ship)) {
				shipSunkNotifications
						.add(new ShipSunkNotification(ship.getBoard().getUser().getUsername(), ship.getShipType()));
			}
		}
		return shipSunkNotifications;
	}

	@GetMapping(path = "/started/{gameId}")
	public GameStartNotification sendGameStarted(@PathVariable int gameId) {
		String currentTurnUsername = gameJpaRepository.getCurrentTurn(gameId);
		System.out.println(currentTurnUsername);
		if (currentTurnUsername != null) {
			return new GameStartNotification(true, new TurnChangeNotification(currentTurnUsername));
		} else {
			return new GameStartNotification(false, null);
		}
	}

}
