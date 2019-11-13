package pro0.battleship.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServlet;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/")
@Controller
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@GetMapping(path="")
	protected String doMainGet(Principal principal) {
		String targetResource = "home";
		if(principal != null)
		{
			targetResource = "loggedInHome";
		}
		// For JS requests eventually
//		((UserDetails) principal).getUsername()
		
		return targetResource;
	}
	
	@GetMapping(path="/savedgames")
	protected String doSavedGamesGet() {
		
		return "savedgames";
	}

}
