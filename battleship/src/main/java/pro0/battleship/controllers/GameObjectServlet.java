package pro0.battleship.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import pro0.battleship.models.Game;


@Controller
public class GameObjectServlet {
	
	@MessageMapping("/game")
    @SendTo("/tojs/game")
    public Game game(Game game) throws Exception {
        //TODO: Alter game before returning it.
        return game;
    }
}
