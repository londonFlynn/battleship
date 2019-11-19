package pro0.battleship.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pro0.battleship.models.Game;
import pro0.battleship.repositories.GameJpaRepository;

@Controller
@RequestMapping(path="/battle")
public class Battle {

	@Autowired
	GameJpaRepository gameRepo;
	
	@GetMapping(path="")
	protected String doMainGet(Principal prn) {
		// Create game here
		Game game = gameRepo.save(newGame(prn.getName()));
		
		return "forward:/battle/" + game.getId();
	}
	
	@GetMapping(path="/{gameID}")
	protected String doGetBattle(
			@PathVariable int gameID,
			Model model
	) {
		model.addAttribute("gameID", gameID);
		
		return "battle";
	}
}
