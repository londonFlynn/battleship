package pro0.battleship.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class Home {

	@GetMapping(path="")
	protected String doMainGet(Principal principal, Model model) {
		boolean loggedIn = principal != null;
		
		model.addAttribute("loggedIn", loggedIn);
		// For JS requests eventually
//		((UserDetails) principal).getUsername()
		
		return "home";
	}

}
