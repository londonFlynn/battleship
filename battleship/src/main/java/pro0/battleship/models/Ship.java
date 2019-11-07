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
	private short startRow;
	private short startColumn;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
	public Ship() {
	}

	public Ship(ShipType shipType, Direction direction, short length, short startRow, short startColumn) {
		this.shipType = shipType;
		this.direction = direction;
		this.length = length;
		this.startRow = startRow;
		this.startColumn = startColumn;
	}

	public Ship(ShipType shipType, short length) {
		this.shipType = shipType;
		this.direction = null;
		this.length = length;
		this.startRow = 0;
		this.startColumn = 0;
	}
	public void placeShip(short startRow, short startColumn, Direction direction) {
		setStartRow(startRow);
		setStartColumn(startColumn);
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

	public short getStartRow() {
		return startRow;
	}

	public void setStartRow(short startRow) {
		this.startRow = startRow;
	}

	public short getStartColumn() {
		return startColumn;
	}

	public void setStartColumn(short startColumn) {
		this.startColumn = startColumn;
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
				.append(direction).append(", length=").append(length).append(", startRow=").append(startRow)
				.append(", startColumn=").append(startColumn).append("]");
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
