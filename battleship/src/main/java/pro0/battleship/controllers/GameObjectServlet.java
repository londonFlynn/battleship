package pro0.battleship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import pro0.battleship.models.Game;
import pro0.battleship.repositories.GameJpaRepository;



@Controller
public class GameObjectServlet {
	
	@Autowired
	GameJpaRepository gameJpaRepository;
	
	@MessageMapping("/game")
    @SendTo("/tojs/game")
    public Game game(Game game) throws Exception {
		System.out.println(game);
        //TODO: Alter game before returning it.
        return game;
    }
}
