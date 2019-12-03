package pro0.battleship.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import pro0.battleship.models.Game;
import pro0.battleship.models.User;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/savedgames")
public class SavedGames {

	@Autowired
	UserJpaRepository userRepo;
	
	@Autowired
	GameJpaRepository gameRepo;
	
	@GetMapping(path="")
	protected String doMainGet(HttpServletResponse resp, Principal prn, Model model) {
		String targetResource = "savedGames";
		Optional<User> optUser = userRepo.findById(prn.getName());
		
		if(optUser.isPresent()) {
			User user = optUser.get();
			model.addAttribute("username", prn.getName());
			
			List<Game> allGames = user.getGames();
			allGames.addAll(user.getOtherGames());
			model.addAttribute("allGames", allGames);
		} else {
			resp.setStatus(500);
			targetResource = "error";
		}
		
		return targetResource;
	}
	
	@GetMapping(path="/{gameId}")
	protected String doJoinGame(
		@PathVariable int gameId,
		Principal prn,
		Model model
	) {
		String targetResource = "redirect:/savedgames";
		
		Optional<Game> optGame = gameRepo.findById(gameId);
		
		if(optGame.isPresent()) {
			Game game = optGame.get();
			
			if(game.getFirstUserHasJoined()) {
				RestTemplate restTemplate = new RestTemplate();
				RequestEntity req = null;
				
				String opponentUsername = game.getOtherUser(prn.getName()).getUsername();
				
				try {
					req = new RequestEntity(HttpMethod.GET, new URI("http://localhost:8080/betweenjs/DEPLOY/" + opponentUsername));
				} catch (URISyntaxException urise) {
					urise.printStackTrace();
				}
				ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
				System.out.println(resp);
				
				game.setActive(true);
				
				targetResource = "redirect:/battle/" + gameId;
			} else {
				model.addAttribute("username", prn.getName());
				model.addAttribute("gameId", gameId);
				
				game.setFirstUserHasJoined(true);
				gameRepo.save(game);
				
				targetResource = "DEPLOYING";
			}
		}
		
		return targetResource;
	}
}
