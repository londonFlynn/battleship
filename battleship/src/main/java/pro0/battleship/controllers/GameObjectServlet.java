package pro0.battleship.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro0.battleship.models.BoardRow;
import pro0.battleship.models.Game;
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



@Controller
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
	@RequestMapping(path="/row/{gameId}/{row}/{opponent}", method=RequestMethod.GET)
	public BoardRow sendBoardRow(@PathVariable int gameId, @PathVariable int row, @PathVariable boolean opponent, Principal prn) {
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
		List<BoardRow> boardRows = boardRowJpaRepository.searchByGameAndRowNumber(gameId, row, searchUsername);
		if (boardRows.size() > 0) {
			return boardRows.get(0);
		}
		return null;
	}
	@RequestMapping(path="/ships/{gameId}", method=RequestMethod.GET)
	public List<ShipPlacementResponse> sendUsersShips(@PathVariable int gameId, Principal prn) {
		String username = prn.getName();
		List<Ship> ships = shipJpaRepository.getShipsByGameId(gameId, username);
		List<ShipPlacementResponse> shipPlacementResponses = new ArrayList<ShipPlacementResponse>();
		for (Ship ship : ships) {
			if (ship.getPosition() != null) {
				shipPlacementResponses.add(new ShipPlacementResponse(true, ship.getSpaceCoords()));
			}
		}
		return shipPlacementResponses;
	}
	
	
	
	@RequestMapping(path="/destroyed/{gameId}", method=RequestMethod.GET)
	public List<ShipSunkNotification> sendDestroyedShips(@PathVariable int gameId, Principal prn) {
		String username = prn.getName();
		List<Ship> ships = shipJpaRepository.getShipsByGameId(gameId);
		List<ShipSunkNotification> shipSunkNotifications = new ArrayList<ShipSunkNotification>();
		Game game = gameJpaRepository.findById(gameId).orElse(null);
		GameplayController gc = new GameplayController(game, boardJpaRepository, boardRowJpaRepository, boardCellJpaRepository, shipJpaRepository);
		for (Ship ship : ships) {
			if (gc.shipIsSunk(ship)) {
				shipSunkNotifications.add(new ShipSunkNotification(ship.getBoard().getUser().getUsername(), ship.getShipType()));
			}
		}
		return shipSunkNotifications;
	}
	@RequestMapping(path="/started/{gameId}", method=RequestMethod.GET)
	public GameStartNotification sendGameStarted(@PathVariable int gameId) {
		String currentTurnUsername = gameJpaRepository.getCurrentTurn(gameId);
		if (currentTurnUsername != null) {
			return new GameStartNotification(true, new TurnChangeNotification(currentTurnUsername));
		} else {
			return new GameStartNotification(false, null);
		}
	}
	
	
	
	
}
