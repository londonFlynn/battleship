package pro0.battleship;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pro0.battleship.models.User;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping(path="/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private UserJpaRepository userJpaRepo;
	
	@GetMapping(path="")
	protected String doMainGet(HttpSession sess) {
		String targetResource = "login";
		if(sess.getAttribute("username") != null) {
			targetResource = "forward:/";
			sess.setAttribute("msg", "You are already logged in.");
		}
		return targetResource;
	}

	@PostMapping(path="")
	protected String doMainPost(
		@RequestParam(required=true) String username,
		@RequestParam(required=true) String password,
		HttpSession sess
	) {
		String targetResource = "login";
		String storedPassword = null;
		
		Optional<User> optUser = userJpaRepo.findById(username); 
		if(optUser.isPresent()) {
			storedPassword = optUser.get().getPassword();
		}
		
		if(storedPassword != null && password.equals(storedPassword)) {
			sess.setAttribute("username", username);
			targetResource = "forward:/";
		} else {
			sess.setAttribute("msg", "Login Failed");
		}
		
		return targetResource;
	}
	
	@GetMapping(path="/logout")
	protected String doLogOut(HttpServletRequest req, HttpSession sess) {
		sess.invalidate();
		sess = req.getSession();
		sess.setAttribute("msg", "You logged out.");
		return "login";
	}

}
