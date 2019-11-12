package pro0.battleship.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pro0.battleship.enums.Direction;
import pro0.battleship.enums.ShipType;
@Entity(name="Ship")
@Table(name="ship")
public class Ship {
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private Long id;
	@Enumerated(EnumType.STRING)
	private ShipType shipType;
	@Enumerated(EnumType.STRING)
	private Direction direction;
	private short length;
	private short xPos;
	private short yPos;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
	public Ship() {
	}

	public Ship(ShipType shipType, Direction direction, short length, short xPos, short yPos) {
		this.shipType = shipType;
		this.direction = direction;
		this.length = length;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public Ship(ShipType shipType, short length) {
		this.shipType = shipType;
		this.direction = null;
		this.length = length;
		this.xPos = 0;
		this.yPos = 0;
	}
	public void placeShip(short xPos, short yPos, Direction direction) {
		setXPos(xPos);
		setYPos(yPos);
		setDirection(direction);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public short getXPos() {
		return xPos;
	}

	public void setXPos(short xPos) {
		this.xPos = xPos;
	}

	public short getYPos() {
		return yPos;
	}

	public void setYPos(short yPos) {
		this.yPos = yPos;
	}
	

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ship [id=").append(id).append(", shipType=").append(shipType).append(", direction=")
				.append(direction).append(", length=").append(length).append(", startRow=").append(xPos)
				.append(", startColumn=").append(yPos).append("]");
		return builder.toString();
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ship)) return false;
		return id != null && id.equals(((Ship) o).getId());
	}
	@Override
	public int hashCode() {
		return 31;
	}

}
