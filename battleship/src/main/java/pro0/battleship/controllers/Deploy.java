package pro0.battleship.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pro0.battleship.models.Game;
import pro0.battleship.models.User;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/DEPLOY")
public class Deploy {

	@Autowired
	private UserJpaRepository userRepo;
	
	@GetMapping(path="")
	protected String doMainGet() {
		
		return "DEPLOY";
	}
	
	@PostMapping(path="")
	protected String doMainPost(
			@RequestParam String username
	) {
		String targetResource = "/DEPLOY";
		
		Optional<User> optScouredOpponent = userRepo.findById(username);
		
		if(optScouredOpponent.isPresent()) {
			User scouredOpponent = optScouredOpponent.get();
			Game gameToJoin = scouredOpponent.getGames().stream().filter(game -> game.isActive()).findFirst().orElse(null);
		}
		
		return targetResource;
	}
}
