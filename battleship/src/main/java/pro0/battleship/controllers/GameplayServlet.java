package pro0.battleship.controllers;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import pro0.battleship.enums.ShipType;
import pro0.battleship.models.AttackResult;
import pro0.battleship.models.GameOverNotification;
import pro0.battleship.models.GameStartNotification;
import pro0.battleship.models.ShipSunkNotification;
import pro0.battleship.models.TargetCellRequest;
import pro0.battleship.models.TurnChangeNotification;

@Controller
public class GameplayServlet {
	
	private SimpMessagingTemplate template;
	
	@MessageMapping("/targetCell/{gameId}")
	@SendTo("/tojs/attackResult/{gameId}")
	public AttackResult recieveTargetCellRequest(@DestinationVariable("gameId") int gameId, Principal prn, @RequestBody TargetCellRequest request) {
		String username = prn.getName();
		
		return null;
	}
	
	public void sendShipSunkNotification(int gameId, String username, ShipType shipType) {
		this.template.convertAndSend("/tojs/sunkShip/" + gameId, new ShipSunkNotification(username, shipType));
	}
	
	public void sendGameStartedNotification(int gameId, String username) {
		this.template.convertAndSend("/tojs/sunkShip/" + gameId, new GameStartNotification(true, new TurnChangeNotification(username)));
	}
	
	public void sendTurnChangeNotification(int gameId, String username) {
		this.template.convertAndSend("/tojs/sunkShip/" + gameId, new TurnChangeNotification(username));
	}
	

	public void sendGameOverNotification(int gameId, String username) {
		this.template.convertAndSend("/tojs/gameOver/" + gameId, new GameOverNotification(username));
	}

}
