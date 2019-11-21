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
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/DEPLOY")
public class Deploy {

	@Autowired
	private UserJpaRepository userRepo;
	
	@Autowired
	private GameJpaRepository gameRepo;
	@Autowired
	private BoardJpaRepository boardRepo;
	@Autowired
	private BoardRowJpaRepository boardRowRepo;
	@Autowired
	private BoardCellJpaRepository boardCellRepo;
	@Autowired
	private ShipJpaRepository shipRepo;
	
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
		
		Optional<User> optUser = userRepo.findById(prn.getName());
		
		if(optUser.isPresent()) {
			User user = optUser.get();
			Game game = Game.newGame(user, gameRepo, boardRepo, boardRowRepo, boardCellRepo, shipRepo);
			model.addAttribute("gameId", game.getId());
			user.getGames().add(game);
			userRepo.save(user);
		}
		
		return "DEPLOYING";
	}
}
