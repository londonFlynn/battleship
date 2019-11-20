package pro0.battleship.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Direction direction = null;
	private short length;
	@OneToOne(fetch = FetchType.EAGER)
	private BoardPosition position;
	@ManyToOne
	@JsonIgnore
//	@JoinColumn(name = "board")
	private Board board;
	
	public Ship() {
	}

	public Ship(ShipType shipType, Direction direction, short length, BoardPosition position) {
		this.shipType = shipType;
		this.direction = direction;
		this.length = length;
		this.position = position;
	}

	public Ship(ShipType shipType, short length) {
		this.shipType = shipType;
		this.direction = null;
		this.length = length;
	}
	public void placeShip(BoardPosition position, Direction direction) {
		setPosition(position);
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

	

	public BoardPosition getPosition() {
		return position;
	}

	public void setPosition(BoardPosition position) {
		this.position = position;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public BoardPosition getSpaceCoords(int cellOfShip) {
		if (direction != null && cellOfShip >= 0 && cellOfShip < length) {
			BoardPosition position = new BoardPosition();
			position.setxPos(this.position.getxPos());
			position.setyPos(this.position.getyPos());
			if (direction.equals(Direction.NORTH)) {
				position.setyPos(position.getyPos() - cellOfShip);
			} else if (direction.equals(Direction.SOUTH)) {
				position.setyPos(position.getyPos() + cellOfShip);
			} else if (direction.equals(Direction.EAST)) {
				position.setxPos(position.getxPos() + cellOfShip);
			} else if (direction.equals(Direction.WEST)) {
				position.setxPos(position.getxPos() - cellOfShip);
			}
			return position;
		} else {
			return null;
		}
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
