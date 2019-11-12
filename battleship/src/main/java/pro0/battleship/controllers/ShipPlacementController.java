package pro0.battleship.controllers;

import java.util.List;

import pro0.battleship.enums.Direction;
import pro0.battleship.enums.ShipType;
import pro0.battleship.models.Board;
import pro0.battleship.models.Ship;

public class ShipPlacementController {
	
	private Board board;

	public ShipPlacementController(Board board) {
		setBoard(board);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean attemptPlacement(ShipType shipType, short xPos, short yPos, Direction direction) {
		Ship selectedShip = null;
		List<Ship> ships = board.getShips();
		for (Ship ship : ships) {
			if (ship.getShipType().equals(shipType)) {
				selectedShip = ship;
				break;
			}
		}
		return attemptPlacement(selectedShip, xPos, yPos, direction);
	}
	public boolean attemptPlacement(Ship ship, short xPos, short yPos, Direction direction) {
		unplaceShip(ship);
		int xIncriment = 0;
		int yIncriment = 0;
		switch(direction) {
		case NORTH:
			yIncriment = -1;
			break;
		case SOUTH:
			yIncriment = +1;
			break;
		case EAST:
			xIncriment = +1;
			break;
		case WEST:
			xIncriment = -1;
			break;
		}
		boolean valid = incrimentedPlacementCheck(getShipLengthFromType(ship.getShipType()), xPos, yPos, xIncriment, yIncriment);
		if (valid) {
			ship.placeShip(xPos, yPos, direction);
		}
		return valid;
	}
	private void unplaceShip(Ship ship) {
		ship.placeShip((short)0, (short)0, null);
	}
	
	private boolean incrimentedPlacementCheck(int length, short xPos, short yPos, int xIncriment, int yIncriment) {
		boolean valid = true;
		for (int i = 0; i < length && valid; i++) {
			if (Board.spaceIsOnBoard(xPos, yPos) && valid) {
				valid = valid && board.spaceIsOpen(xPos, yPos);
				yPos += yIncriment;
				xPos += xIncriment;
			} else {
				valid = false;
			}
		}
		return valid;
	}
	public static short getShipLengthFromType(ShipType shipType) {
		short length;
		switch (shipType) {
		case AIRCRAFT_CARRIER:
			length = (short) 5;
			break;
		case BATTLESHIP:
			length = (short) 4;
			break;
		case CRUSER:
			length = (short) 3;
			break;
		case SUBMARINE:
			length = (short) 3;
			break;
		case DESROYER:
			length = (short) 2;
			break;
		default:
			length = (short) 1;
			break;
		}
		return length;
	}
	

}
