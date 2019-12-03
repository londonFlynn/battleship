package pro0.battleship.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pro0.battleship.enums.Direction;
import pro0.battleship.enums.ShipType;
import pro0.battleship.models.BoardPosition;
import pro0.battleship.models.Game;
import pro0.battleship.models.Ship;
import pro0.battleship.models.ShipPlacementRequest;
import pro0.battleship.models.ShipPlacementResponse;
import pro0.battleship.models.User;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardPositionJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;

@RestController
@RequestMapping("/shipPlacement")
public class ShipPlacementServlet {
	
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
	@Autowired
	BoardPositionJpaRepository boardPositionJpaRepository;
	
	@RequestMapping(path="/{gameId}", method=RequestMethod.POST)
	public ShipPlacementResponse recieveShipPlacementRequest(@PathVariable int gameId, Principal prn, @RequestBody ShipPlacementRequest request) {
		String username = prn.getName();
		User user = userJpaRepository.findById(username).orElse(null);
		Game game = gameJpaRepository.findById(gameId).orElse(null);
		GameplayController gc = new GameplayController(gameId, gameJpaRepository, boardJpaRepository, boardRowJpaRepository, boardCellJpaRepository, shipJpaRepository);
		ShipPlacementController pc = new ShipPlacementController(user, gc);
		ShipPlacementResponse shipPlacementResponse = new ShipPlacementResponse(false, new ArrayList<BoardPosition>(), ShipType.valueOf(request.getShipType()));
		if (game.getCurrentTurn() == null) {
			shipPlacementResponse = pc.attemptPlacement(ShipType.valueOf(request.getShipType()), request.getPosition(), Direction.valueOf(request.getDirection()), shipJpaRepository, boardPositionJpaRepository);
		}
		List<Ship> ships = shipJpaRepository.getShipsByGameId(gameId);
		boolean allShipsPlaced = true;
		for (Ship ship : ships) {
			allShipsPlaced = allShipsPlaced && ship.getPosition() != null;
		}
		if (allShipsPlaced) {
			sendStartOfGameNotification(gameId);
		}
		return shipPlacementResponse;
	}
	
	private void sendStartOfGameNotification(int gameId) {
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity req = null;
		try {
			req = new RequestEntity(HttpMethod.GET, new URI("http://localhost:8080/betweenjs/start/"+Integer.toString(gameId)));
			req.toString();
		} catch (URISyntaxException urise) {
			urise.printStackTrace();
		}
		restTemplate.exchange(req, String.class);
	}

}
