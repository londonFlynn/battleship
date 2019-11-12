package pro0.battleship.controllers;

import java.util.List;

import pro0.battleship.exceptions.SpaceAlreadyAttackedExeception;
import pro0.battleship.models.Board;
import pro0.battleship.models.Game;
import pro0.battleship.models.Ship;
import pro0.battleship.models.User;

public class GameplayController {
	
	private Game game;
	public GameplayController(Game game) {
		this.game = game;
	}
	public boolean attackSquare(User attacker, short xPos, short yPos) throws SpaceAlreadyAttackedExeception {
		defendSquare(game.getOtherUser(attacker), xPos, yPos);
		boolean hit = !game.getUsersBoard(game.getOtherUser(attacker)).spaceIsOpen(xPos, yPos);
		return hit;
	}
	private void defendSquare(User defender, short xPos, short yPos) throws SpaceAlreadyAttackedExeception {
		Board board = game.getUsersBoard(defender);
		if (!board.hasSpaceBeenTargeted(xPos, yPos)) {
			board.targetSpace(xPos, yPos);
		} else {
			throw new SpaceAlreadyAttackedExeception();
		}
	}
	public boolean shipIsSunk(Ship ship, Board board) {
		boolean sunk = true;
		for (int i = 0; i < ship.getLength() && sunk; i++) {
			int[] coords = ship.getSpaceCoords(i);
			sunk = sunk && board.hasSpaceBeenTargeted(coords[0], coords[1]);
		}
		return sunk;
	}
	public boolean gameIsLost(User user) {
		Board board = game.getUsersBoard(user);
		List<Ship> ships = board.getShips();
		boolean losing = true;
		for (Ship ship : ships) {
			losing = losing && shipIsSunk(ship, board);
		}
		return losing;
	}
	public boolean gameIsWon(User user) {
		return gameIsLost(game.getOtherUser(user));
	}

}
