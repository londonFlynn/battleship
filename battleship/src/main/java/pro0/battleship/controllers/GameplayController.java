package pro0.battleship.controllers;

import java.util.List;

import pro0.battleship.exceptions.SpaceAlreadyAttackedExeception;
import pro0.battleship.models.Board;
import pro0.battleship.models.BoardPosition;
import pro0.battleship.models.Game;
import pro0.battleship.models.Ship;
import pro0.battleship.models.User;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;

public class GameplayController {
	
	private Game game;
	
	
	
	
	public GameplayController(int gameId, GameJpaRepository gameJpaRepository, BoardJpaRepository boardJpaRepository, BoardRowJpaRepository boardRowJpaRepository, BoardCellJpaRepository boardCellJpaRepository, ShipJpaRepository shipJpaRepository) {
		this.game = gameJpaRepository.findById(gameId).orElse(null);
	}
	public GameplayController(Game game, BoardJpaRepository boardJpaRepository, BoardRowJpaRepository boardRowJpaRepository, BoardCellJpaRepository boardCellJpaRepository, ShipJpaRepository shipJpaRepository) {
		this.game = game;
	}
	public boolean attackSquare(User attacker, BoardPosition position) throws SpaceAlreadyAttackedExeception {
		defendSquare(game.getOtherUser(attacker), position);
		boolean hit = !game.getUsersBoard(game.getOtherUser(attacker)).spaceIsOpen(position);
		game.getUsersBoard(game.getOtherUser(attacker)).getRows().get(position.getyPos()).getCells().get(position.getyPos()).setHit(hit);
		return hit;
	}
	private void defendSquare(User defender, BoardPosition position) throws SpaceAlreadyAttackedExeception {
		Board board = game.getUsersBoard(defender);
		if (!board.hasSpaceBeenTargeted(position)) {
			board.targetSpace(position);
		} else {
			throw new SpaceAlreadyAttackedExeception();
		}
	}
	public boolean shipIsSunk(Ship ship) {
		Board board = ship.getBoard();
		boolean sunk = true;
		for (int i = 0; i < ship.getLength() && sunk; i++) {
			BoardPosition position = ship.getSpaceCoords(i);
			sunk = sunk && board.hasSpaceBeenTargeted(position);
		}
		return sunk;
	}
	public boolean gameIsLost(User user) {
		Board board = game.getUsersBoard(user);
		List<Ship> ships = board.getShips();
		boolean losing = true;
		for (Ship ship : ships) {
			losing = losing && shipIsSunk(ship);
		}
		return losing;
	}
	public boolean gameIsWon(User user) {
		return gameIsLost(game.getOtherUser(user));
	}

}
