package pro0.battleship.controllers;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pro0.battleship.models.User;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/savedgames")
public class SavedGames {

	@Autowired
	UserJpaRepository userRepo;
	
	@GetMapping(path="")
	protected String doMainGet(HttpServletResponse resp, Principal prn, Model model) {
		String targetResource = "savedGames";
		Optional<User> optUser = userRepo.findById(prn.getName());
		
		if(optUser.isPresent()) {
			User user = optUser.get();
			model.addAttribute("games", user.getGames());
		} else {
			resp.setStatus(500);
			targetResource = "error";
		}
		
		return targetResource;
	}
}
