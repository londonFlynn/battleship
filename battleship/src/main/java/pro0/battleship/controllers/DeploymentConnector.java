package pro0.battleship.controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeploymentConnector {
	
	@MessageMapping("/DEPLOY/{username}")
	@SendTo("/tojs/DEPLOY/{username}")
	protected String sendOpponentConnected(@DestinationVariable("username") String username) {
		return username;
	}
}
