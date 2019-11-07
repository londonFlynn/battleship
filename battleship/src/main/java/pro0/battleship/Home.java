package pro0.battleship;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@GetMapping(path="/")
	public String doMainGet() {
		System.out.println("Reached");
		return "forward:classpath:/templates/something.jsp";
//		return "forward:" + getClass().getResource("/templates/something.jsp");
	}

}
