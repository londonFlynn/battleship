package pro0.battleship.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro0.battleship.models.Board;
import pro0.battleship.models.BoardRow;
import pro0.battleship.models.GameStartNotification;
import pro0.battleship.models.ShipPlacementResponse;
import pro0.battleship.models.ShipSunkNotification;
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
	
	@RequestMapping(path="/myBoard/{gameId}", method=RequestMethod.GET)
	public Board sendUsersBoard(@PathVariable int gameId) {
		return null;
	}
	@RequestMapping(path="/opponentBoard/{gameId}", method=RequestMethod.GET)
	public Board sendOpponentBoard(@PathVariable int gameId) {
		return null;
	}
	@RequestMapping(path="/row/{gameId}/{row}/{opponent}", method=RequestMethod.GET)
	public BoardRow sendBoardRow(@PathVariable int gameId, @PathVariable int row, @PathVariable boolean opponent) {
		return null;
	}
	@RequestMapping(path="/ships/{gameId}", method=RequestMethod.GET)
	public List<ShipPlacementResponse> sendUsersShips(@PathVariable int gameId) {
		return null;
	}
	@RequestMapping(path="/destroyed/{gameId}", method=RequestMethod.GET)
	public List<ShipSunkNotification> sendDestroyedShips(@PathVariable int gameId) {
		return null;
	}
	@RequestMapping(path="/started/{gameId}", method=RequestMethod.GET)
	public GameStartNotification sendGameStarted(@PathVariable int gameId) {
		return null;
	}
	
	
	
	
}
