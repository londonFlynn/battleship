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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro0.battleship.models.User;
import pro0.battleship.repositories.UserJpaRepository;

@Controller
@RequestMapping("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private UserJpaRepository userJpaRepo;
	
	@RequestMapping(path="", method=RequestMethod.GET)
	protected void doMainGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		String pageToForwardTo = "/login.jsp";
		if(sess.getAttribute("username") != null) {
			pageToForwardTo = "/";
			sess.setAttribute("msg", "You are already logged in.");
		}
		request.getRequestDispatcher(pageToForwardTo).forward(request, response);
	}

	@RequestMapping(path="", method=RequestMethod.POST)
	protected void doMainPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String storedPassword = null;
		
		Optional<User> optUser = userJpaRepo.findById(username); 
		if(optUser.isPresent()) {
			storedPassword = optUser.get().getPassword();
		}
		
		HttpSession sess = request.getSession(true);
		if(storedPassword != null && password.equals(storedPassword)) {
			sess.setAttribute("username", username);
			response.sendRedirect("/");
		} else {
			sess.setAttribute("msg", "Login Failed");
			doMainGet(request, response);
		}
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	protected void doLogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		sess.invalidate();
		sess = request.getSession();
		sess.setAttribute("msg", "You logged out.");
		doMainGet(request, response);
	}

}
