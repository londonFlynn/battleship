package pro0.battleship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	protected String doMainGet() {
		String targetResource = "login";
//		if(sess.getAttribute("username") != null) {
//			targetResource = "forward:/";
//			sess.setAttribute("msg", "You are already logged in.");
//		}
		
		return targetResource;
	}
	
	@PostMapping(path="/signup")
	protected String doSignUp(
		@RequestParam(required=true) String username,
		@RequestParam(required=true) String password
	) {
		if(!userRepo.findById(username).isPresent() && username.length() > 0 && password.length() > 0) {
			userRepo.save(new User(username, password, null, 0, 0));
		}
		
		return "login";
	}

//	@PostMapping(path="")
//	protected String doMainPost(
//		@RequestParam(required=true) String username,
//		@RequestParam(required=true) String password,
//		HttpSession sess
//	) {
//		String targetResource = "login";
//		String storedPassword = null;
//		
//		Optional<User> optUser = userJpaRepo.findById(username); 
//		if(optUser.isPresent()) {
//			storedPassword = optUser.get().getPassword();
//		}
//		
//		if(storedPassword != null && password.equals(storedPassword)) {
//			sess.setAttribute("username", username);
//			targetResource = "forward:/";
//		} else {
//			sess.setAttribute("msg", "Login Failed");
//		}
//		
//		return targetResource;
//	}
	
//	@GetMapping(path="/logout")
//	protected String doLogOut(HttpServletRequest req, HttpSession sess) {
//		sess.invalidate();
//		sess = req.getSession();
//		sess.setAttribute("msg", "You logged out.");
//		
//		return "login";
//	}

}
