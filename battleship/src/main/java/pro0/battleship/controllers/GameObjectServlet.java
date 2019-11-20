package pro0.battleship.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro0.battleship.models.Board;
import pro0.battleship.models.BoardRow;
import pro0.battleship.models.Game;
import pro0.battleship.models.Ship;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;
import pro0.battleship.repositories.UserJpaRepository;



@Controller
@RequestMapping("/gamestate")
public class GameObjectServlet {
	
	@Autowired
	GameJpaRepository gameJpaRepository;
	@Autowired
	BoardJpaRepository boardJpaRepository;
	@Autowired
	BoardRowJpaRepository boardRowJpaRepository;
	@Autowired
	BoardCellJpaRepository boardCellJpaRepository;
	@Autowired
	UserJpaRepository userJpaRepository;
	@Autowired
	ShipJpaRepository shipJpaRepository;
	
//	@MessageMapping("/game")
//    @SendTo("/tojs/game")
//    public Game game(Game game) throws Exception {
//		System.out.println(game);
//		gameJpaRepository.save(game);
//        return setupGame();
//    }

	@RequestMapping(path="/game/{gameId}", method=RequestMethod.GET)
	public Game sendGameState(@PathVariable int gameId) {
		Game game =gameJpaRepository.findById(gameId).orElse(null);
		return game;
	}
	@RequestMapping(path="/myBoard/{gameId}", method=RequestMethod.GET)
	public Board sendUsersBoard(int gameId) {
		return null;
	}
	@RequestMapping(path="/opponentBoard/{gameId}", method=RequestMethod.GET)
	public Board sendOpponentBoard(int gameId) {
		return null;
	}
	@RequestMapping(path="/row/{gameId}/{row}", method=RequestMethod.GET)
	public BoardRow sendBoardRow(int gameId, int row) {
		return null;
	}
	@RequestMapping(path="/ships/{gameId}", method=RequestMethod.GET)
	public List<Ship> sendUsersShips(int gameId) {
		return null;
	}
	
	
}
