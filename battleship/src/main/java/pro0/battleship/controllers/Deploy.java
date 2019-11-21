package pro0.battleship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/DEPLOY")
public class Deploy {

	@GetMapping(path="")
	protected String doMainGet() {
		
		return "DEPLOY";
	}
}
