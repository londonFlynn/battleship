package pro0.battleship.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import pro0.battleship.models.Game;
import pro0.battleship.models.ShipPlacementRequest;
import pro0.battleship.models.TargetCellRequest;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;



@Controller
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
	
//	@MessageMapping("/game")
//    @SendTo("/tojs/game")
//    public Game game(Game game) throws Exception {
//		System.out.println(game);
//		gameJpaRepository.save(game);
//        return setupGame();
//    }
	
	
	@MessageMapping("/gameState/{id}")
	@SendTo("/tojs/game/{id}")
	public Game recieveGameStateRequst(@DestinationVariable("id") int id) {
		return gameJpaRepository.findById(id).orElse(null);
	}
	
	@MessageMapping("/placeShip/{id}")
	@SendTo("/tojs/game/{id}")
	public Game recieveShipPlacementRequest(@DestinationVariable("id") int id, Principal prn, @RequestBody ShipPlacementRequest request) {
		prn.getName();
		
		//TODO: Alter game before returning it.
		return null;
	}
	@MessageMapping("/targetCell/{id}")
	@SendTo("/tojs/game/{id}")
	public Game recieveTargetCellRequest(@DestinationVariable("id") int id, Principal prn, @RequestBody TargetCellRequest request) {
		prn.getName();
		
		//TODO: Alter game before returning it.
		return null;
	}
	
}
