package pro0.battleship.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pro0.battleship.models.Game;
import pro0.battleship.models.User;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/savedgames")
public class SavedGames {

	@Autowired
	UserJpaRepository userRepo;
	
	@GetMapping(path="")
	protected String doMainGet(Principal prn, Model model) {
		String targetResource = "forward:/";
//		String username = ((UserDetails) prn).getUsername();
		
//		if(userRepo.findById(username).isPresent()) {
//			User user = userRepo.findById(username).get();
			User user = new User("kathy", "test", "/hello", 0, 1);
			user.getGames().add(new Game(user, user));
			model.addAttribute("games", user.getGames());
			targetResource = "savedGames";
//		}
		
		return targetResource;
	}
}
