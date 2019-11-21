package pro0.battleship.controllers;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pro0.battleship.models.User;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/dock")
public class Dock {

	@Autowired
	UserJpaRepository userRepo;
	
	@GetMapping(path="")
	protected String doMainGet(Principal prn, Model model) {
		String targetResource = "dock";
		Optional<User> optUser = userRepo.findById(prn.getName());

		if(optUser.isPresent()) {
			User user = optUser.get();
			model.addAttribute("username", user.getUsername());
			model.addAttribute("gamesWon", user.getGamesWon());
			model.addAttribute("gamesLost", user.getGamesLost());
			model.addAttribute("games", user.getGames());
			model.addAttribute("imgUrl", user.getImageUrl());
		} else {
			targetResource = "error";
		}
		
		return targetResource;
	}
}
