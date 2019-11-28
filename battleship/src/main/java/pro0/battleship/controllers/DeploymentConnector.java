package pro0.battleship.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeploymentConnector {

	private SimpMessagingTemplate template;
	
	@GetMapping("/betweenjs/DEPLOY/{username}")
	protected ResponseEntity<String> sendOpponentConnected(@PathVariable("username") String username) {
		System.out.println("Attempted DEPLOYMENT.");
		System.out.println(username);
		this.template.convertAndSend("/tojs/DEPLOY/" + username, username);
		
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}
}
