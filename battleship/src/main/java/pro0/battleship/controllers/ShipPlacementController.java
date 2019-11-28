package pro0.battleship.controllers;

import java.util.List;

import pro0.battleship.enums.Direction;
import pro0.battleship.enums.ShipType;
import pro0.battleship.models.Board;
import pro0.battleship.models.BoardPosition;
import pro0.battleship.models.Ship;
import pro0.battleship.models.ShipPlacementResponse;
import pro0.battleship.models.User;
import pro0.battleship.repositories.BoardPositionJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;

public class ShipPlacementController {
	
	private Board board;
	ShipJpaRepository shipJpaRepository;

	public ShipPlacementController(User user, GameplayController gc) {
		setBoard(gc.getGame().getUsersBoard(user));
		this.shipJpaRepository = gc.getShipJpaRepository();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public ShipPlacementResponse attemptPlacement(ShipType shipType, BoardPosition position, Direction direction, ShipJpaRepository shipJpaRepository, BoardPositionJpaRepository boardPositionJpaRepository) {
		List<Ship> ships = board.getShips();
		Ship selectedShip = ships.get(0);
		for (Ship ship : ships) {
			if (ship.getShipType().equals(shipType)) {
				selectedShip = ship;
			}
		}
		return attemptPlacement(selectedShip, position, direction, shipJpaRepository, boardPositionJpaRepository);
	}
	public ShipPlacementResponse attemptPlacement(Ship ship, BoardPosition position, Direction direction, ShipJpaRepository shpJpaRepository, BoardPositionJpaRepository boardPositionJpaRepository) {
		unplaceShip(ship, shipJpaRepository, boardPositionJpaRepository);
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
		boolean valid = incrimentedPlacementCheck(getShipLengthFromType(ship.getShipType()), position, xIncriment, yIncriment);
		if (valid) {
			ship.placeShip(position, direction, boardPositionJpaRepository, shpJpaRepository);
			shipJpaRepository.save(ship);
		}
		return new ShipPlacementResponse(valid, ship.getSpaceCoords());
	}
	private void unplaceShip(Ship ship, ShipJpaRepository shipJpaRepository, BoardPositionJpaRepository boardPositionJpaRepository) {
		ship.setPosition(null, shipJpaRepository, boardPositionJpaRepository);
	}
	
	private boolean incrimentedPlacementCheck(int length, BoardPosition position, int xIncriment, int yIncriment) {
		BoardPosition incrimentedPosition = new BoardPosition();
		incrimentedPosition.setxPos(position.getxPos());
		incrimentedPosition.setyPos(position.getyPos());
		boolean valid = true;
		for (int i = 0; i < length && valid; i++) {
			if (Board.spaceIsOnBoard(incrimentedPosition) && valid) {
				valid = valid && board.spaceIsOpen(incrimentedPosition);
				incrimentedPosition.setxPos(incrimentedPosition.getxPos() + xIncriment);
				incrimentedPosition.setyPos(incrimentedPosition.getyPos() + yIncriment);
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
		case CRUISER:
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
