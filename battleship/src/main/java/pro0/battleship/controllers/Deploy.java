package pro0.battleship.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			Optional<Game> optGameToJoin = scouredOpponent.getGames().stream()
				.filter(game -> game.isActive())
				.sorted((game1, game2) ->
					game1.getCreationTimeStamp().compareTo(game2.getCreationTimeStamp()))
				.findFirst();
			
			if(optGameToJoin.isPresent()) {
				Game gameToJoin = optGameToJoin.get();
				targetResource = "/battle/" + gameToJoin.getId();
			}
		}
		
		return targetResource;
	}

	@GetMapping(path="/DEPLOYING")
	protected String doCreateBattle(Principal prn, Model model) {
		model.addAttribute("username", prn.getName());
		
		return "DEPLOYING";
	}
}
