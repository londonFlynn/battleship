package pro0.battleship.controllers;

import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/")
@Controller
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@GetMapping(path="")
	public String doMainGet() {
		return "home";
	}

}
