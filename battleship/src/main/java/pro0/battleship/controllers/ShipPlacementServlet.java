package pro0.battleship.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro0.battleship.models.ShipPlacementRequest;
import pro0.battleship.models.ShipPlacementResponse;

@Controller
@RequestMapping("/shipPlacement")
public class ShipPlacementServlet {
	
	@RequestMapping(path="/{gameId}", method=RequestMethod.POST)
	public ShipPlacementResponse recieveShipPlacementRequest(@PathVariable int gameId, Principal prn, @RequestBody ShipPlacementRequest request) {
		String username = prn.getName();
		
		
		return null;
	}

}
