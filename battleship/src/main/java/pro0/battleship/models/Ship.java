package pro0.battleship.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import pro0.battleship.enums.Direction;
import pro0.battleship.enums.ShipType;
@Entity
public class Ship {
	@Id
	private long id;
	private ShipType shipType;
	private Direction direction;
	private short length;
	private short startRow;
	private short startColumn;
	
	public Ship() {
	}

	public Ship(long id, ShipType shipType, Direction direction, short length, short startRow, short startColumn) {
		this.id = id;
		this.shipType = shipType;
		this.direction = direction;
		this.length = length;
		this.startRow = startRow;
		this.startColumn = startColumn;
	}

	public Ship(long id, ShipType shipType, short length) {
		this.id = id;
		this.shipType = shipType;
		this.direction = null;
		this.length = length;
		this.startRow = 0;
		this.startColumn = 0;
	}
//	public boolean placeShip(short startRow, short startColumn, Direction direction) {
//		
//	}
	
	

}
