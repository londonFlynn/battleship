package pro0.battleship.controllers;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping(path="")
	protected String doMainGet(Principal prn, HttpServletResponse resp) {
		String targetResource = "forward:/error";
		Optional<User> user = userRepo.findById(prn.getName());
		System.out.println(prn.getName());
		Game game = null;
		
		if(user.isPresent()) {
			game = gameRepo.save(Game.newGame(user.get(), gameRepo, boardRepo, boardRowRepo, boardCellRepo));
			targetResource = "forward:/battle/" + game.getId();
		}
		else resp.setStatus(500);
		
		return targetResource;
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
