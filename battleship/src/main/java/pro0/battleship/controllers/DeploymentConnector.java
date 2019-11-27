package pro0.battleship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeploymentConnector {

	@Autowired
	private SimpMessagingTemplate template;
	
	@GetMapping("/betweenjs/DEPLOY/{username}")
	protected ResponseEntity<String> sendOpponentConnected(@PathVariable("username") String username) {
		System.out.println("Attempted DEPLOYMENT.");
		System.out.println(username);
		template.convertAndSend("/tojs/DEPLOY/" + username, username);
		
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}
}
