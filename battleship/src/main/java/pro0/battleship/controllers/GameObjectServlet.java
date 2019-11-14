package pro0.battleship.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import pro0.battleship.models.Board;
import pro0.battleship.models.BoardCell;
import pro0.battleship.models.BoardRow;
import pro0.battleship.models.Game;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;



@Controller
public class GameObjectServlet {
	
	@Autowired
	GameJpaRepository gameJpaRepository;
	@Autowired
	BoardJpaRepository boardJpaRepository;
	@Autowired
	BoardRowJpaRepository boardRowJpaRepository;
	@Autowired
	BoardCellJpaRepository boardCellJpaRepository;
	
	@MessageMapping("/game")
    @SendTo("/tojs/game")
    public Game game(Game game) throws Exception {
		System.out.println(game);
		gameJpaRepository.save(game);
        //TODO: Alter game before returning it.
        return setupGame();
    }
	
	private Game setupGame() {
		List<Board> boards = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			List<BoardRow> boardRows = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				List<BoardCell> boardCells = new ArrayList<>();
				for (int k = 0; k < 10; k++) {
					BoardCell cell = new BoardCell();
					boardCellJpaRepository.save(cell);
					boardCells.add(cell);
				}
				BoardRow row = new BoardRow();
				row.setCells(boardCells);
				boardRowJpaRepository.save(row);
				boardRows.add(row);
			}
			Board board = new Board();
			board.setRows(boardRows);
			boardJpaRepository.save(board);
			boards.add(board);
		}
		Game game = new Game();
		game.setBoards(boards);
		gameJpaRepository.save(game);
		return game;
	}
	
}
