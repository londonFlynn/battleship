package pro0.battleship.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import pro0.battleship.enums.ShipType;
import pro0.battleship.exceptions.SpaceAlreadyAttackedExeception;
import pro0.battleship.models.AttackResult;
import pro0.battleship.models.Game;
import pro0.battleship.models.GameOverNotification;
import pro0.battleship.models.GameStartNotification;
import pro0.battleship.models.Ship;
import pro0.battleship.models.ShipSunkNotification;
import pro0.battleship.models.TargetCellRequest;
import pro0.battleship.models.TurnChangeNotification;
import pro0.battleship.models.User;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
public class GameplayServlet {

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

	private SimpMessagingTemplate template;

	@MessageMapping("/targetCell/{gameId}")
	@SendTo("/tojs/attackResult/{gameId}")
	public AttackResult recieveTargetCellRequest(@DestinationVariable("gameId") int gameId, Principal prn,
			@RequestBody TargetCellRequest request) {
		String username = prn.getName();
		User user = userJpaRepository.findById(username).orElse(null);
		Game game = gameJpaRepository.findById(gameId).orElse(null);
		GameplayController gc = new GameplayController(gameId, gameJpaRepository, boardJpaRepository, boardRowJpaRepository,  boardCellJpaRepository, shipJpaRepository);
		if (game.getCurrentTurn().equals(user)) {
			try {
				boolean hit = gc.attackSquare(user, request.getPosition());
				game.setCurrentTurn(game.getOtherUser(username));
				gameJpaRepository.save(game);
//				sendTurnChangeNotification(gameId, game.getCurrentTurn().getUsername());
				if (hit) {
					Ship ship = gc.getShipInPosition(request.getPosition(), game.getOtherUser(username));
					if (gc.shipIsSunk(ship)) {
						sendShipSunkNotification(gameId, game.getOtherUser(username).getUsername(), ship.getShipType());
					}
					if (gc.gameIsWon(user)) {
						sendGameOverNotification(gameId, username);
					}
				}
				return new AttackResult(game.getOtherUser(username).getUsername(), request.getPosition(), hit);
			} catch (SpaceAlreadyAttackedExeception e) {
				return AttackResult.invalidAttackResult(username);
			}
		} else {
			return AttackResult.invalidAttackResult(username);
		}
	}

	public void sendShipSunkNotification(int gameId, String username, ShipType shipType) {
		this.template.convertAndSend("/tojs/sunkShip/" + gameId, new ShipSunkNotification(username, shipType));
	}

	public void sendGameStartedNotification(int gameId, String username) {
		this.template.convertAndSend("/tojs/sunkShip/" + gameId,
				new GameStartNotification(true, new TurnChangeNotification(username)));
	}

	public void sendTurnChangeNotification(int gameId, String username) {
		this.template.convertAndSend("/tojs/sunkShip/" + gameId, new TurnChangeNotification(username));
	}

	public void sendGameOverNotification(int gameId, String username) {
		this.template.convertAndSend("/tojs/gameOver/" + gameId, new GameOverNotification(username));
	}

}
