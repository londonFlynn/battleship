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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pro0.battleship.models.Game;
import pro0.battleship.models.User;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/battle")
public class Battle {

	@Autowired
	UserJpaRepository userRepo;
	@Autowired
	GameJpaRepository gameRepo;
	@Autowired
	BoardJpaRepository boardRepo;
	@Autowired
	BoardRowJpaRepository boardRowRepo;
	@Autowired
	BoardCellJpaRepository boardCellRepo;
	@Autowired
	ShipJpaRepository shipRepo;
	
	
	@GetMapping(path="")
	protected String doMainGet(Principal prn) {
		String targetResource = "error";
		Optional<User> user = userRepo.findById(prn.getName());

		Game game = null;
		
		if(user.isPresent()) {
			game = gameRepo.save(Game.newGame(user.get(), gameRepo, boardRepo, boardRowRepo, boardCellRepo, shipRepo));
			targetResource = "redirect:/battle/" + game.getId();
		}
		
		return targetResource;
	}
	
	@GetMapping(path="/{gameID}")
	protected String doGetBattle(
			@PathVariable int gameID,
			Model model, Principal prn
	) {
		model.addAttribute("gameID", gameID);
		model.addAttribute("username", prn.getName());
		return "battle";
	}
}
