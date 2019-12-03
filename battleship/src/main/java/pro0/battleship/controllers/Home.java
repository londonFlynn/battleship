package pro0.battleship.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pro0.battleship.models.Game;
import pro0.battleship.repositories.GameJpaRepository;

@Controller
@RequestMapping(path="/")
public class Home {

	@Autowired
	private GameJpaRepository gameRepo;
	
	@GetMapping(path="")
	protected String doMainGet(Principal principal, Model model) {
		boolean loggedIn = principal != null;
		
		model.addAttribute("loggedIn", loggedIn);
		// For JS requests eventually
//		((UserDetails) principal).getUsername()
		
		return "home";
	}

	@GetMapping(path="/{gameId}")
	protected String returnFromGame(Principal prn, @PathVariable int gameId) {
		if(prn.getName() != null) {
			Optional<Game> optGame = gameRepo.findById(gameId);
			
			if(optGame.isPresent()) {
				Game game = optGame.get();
				
				if(game.getUser() != null && game.getUser().getUsername().equals(prn.getName())
					|| game.getOtherUser() != null && game.getOtherUser().getUsername().equals(prn.getName())) {
					game.setActive(false);
					game.setFirstUserHasJoined(false);
				}
					
			}
		}
		
		return "redirect:/";
	}
}
