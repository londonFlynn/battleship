package pro0.battleship.controllers;

import java.net.URLEncoder;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pro0.battleship.models.User;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/login")
public class Login {
    
	@Autowired
	private UserJpaRepository userRepo;
	
	@GetMapping(path="")
	protected String doMainGet(
			Model model,
			@RequestParam(required=false) String error,
			@RequestParam(required=false) String logout
	) {
		String targetResource = "login";
		String loginMsg = null;
		
		if(error != null) loginMsg = "You have not been logged in.";
		if(logout != null) loginMsg = "You have been logged out.";
		if(loginMsg != null) model.addAttribute("loginMsg", loginMsg);
		
		return targetResource;
	}
	
	@PostMapping(path="/signup")
	protected String doSignUp(
		Model model,
		@RequestParam String username,
		@RequestParam String password,
		HttpServletRequest request
	) {
		String targetResource = "redirect:/login";
		Optional<User> optUser = userRepo.findById(username);
		String signupMsg = null;
		
		if(optUser.isPresent())	signupMsg = "That username has already been claimed.";
		else if(username.length() <= 0) signupMsg = "A username must be at least one character in length.";
		else if(password.length() <= 0) signupMsg = "A password must be at least one character in length.";
		else {
			User user = userRepo.save(new User(username, password, null, 0, 0));
			if(user != null) {
				signupMsg = "A user was successfully made for you.";
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				try {
					request.login(username, password);
					targetResource = "redirect:/dock";
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}
			else {
				targetResource = "error";
			}
		}
		
		if(signupMsg != null) model.addAttribute("signupMsg", signupMsg);
		
		return targetResource;
	}
	
//	private void signIn(String encoding) {
//		String encodedUrl = URLEncoder.encode(encoding);
//		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//		
//		redirectStrategy.sendRedirect("login");
//	}

}
