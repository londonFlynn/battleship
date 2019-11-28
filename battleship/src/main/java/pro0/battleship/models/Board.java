package pro0.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pro0.battleship.enums.Direction;

@Entity(name = "Board")
@Table(name = "board")
public class Board {
	@Transient
	public static final short boardSize = 10;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToMany(mappedBy = "board")
	@JsonIgnore
	private List<Ship> ships = new ArrayList<Ship>();
	// TODO store spaces data in JPA
	@OneToMany(orphanRemoval = true)
	@Column(name = "rows")
	@JsonIgnore
	private List<BoardRow> rows = new ArrayList<BoardRow>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	

	public Board() {
	}

	public Board(User user) {
		setUser(user);
	}
	
	public void addShip(Ship ship) {
		ships.add(ship);
		ship.setBoard(this);
	}
	public void removeShip(Ship ship) {
		ships.remove(ship);
		ship.setBoard(null);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Ship> getShips() {
		return ships;
	}
//
//	public void setShips(List<Ship> ships) {
//		this.ships = ships;
//	}
	
	public boolean hasSpaceBeenTargeted(BoardPosition position) {
		if (position == null) return false;
		return  rows.get(position.getyPos()).hasSpaceBeenTargeted(position.getxPos());
	}

	public void targetSpace(BoardPosition position) {
		 rows.get(position.getyPos()).targetSpace(position.getxPos());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	void addBoardRow(BoardRow row) {
		 rows.add(row);
	}
	void removeBoardRow(BoardRow row) {
		 rows.remove(row);
	}
	
	
	public List<BoardRow> getRows() {
		return  rows;
	}

	public void setRows(List<BoardRow> spaces) {
		this. rows = spaces;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public static boolean spaceIsOnBoard(BoardPosition position) {
		return position.getxPos() >= 0 && position.getyPos() >= 0 && position.getxPos() < Board.boardSize && position.getyPos() < Board.boardSize;
	}
	public boolean spaceIsOpen(BoardPosition position) {
		boolean open = true;
		for (Ship ship : ships) {
			open = open && !shipIsCoveringSpace(ship, position);
		}
		return open;
	}
	public boolean shipIsCoveringSpace(Ship ship, BoardPosition position) {
		boolean covering = false;
		if (ship != null && ship.getPosition() != null && ship.getDirection() != null && ship.getLength() > 0) {
			if (ship.getPosition().getxPos() == position.getxPos()) {
				if (ship.getDirection().equals(Direction.NORTH)) {
					covering = ship.getPosition().getyPos() >= position.getyPos() && ship.getPosition().getyPos() - ship.getLength() <= position.getyPos();
				} else if (ship.getDirection().equals(Direction.SOUTH)) {
					covering = ship.getPosition().getyPos() <= position.getyPos() && ship.getPosition().getyPos() + ship.getLength() >= position.getyPos();
				}
			} else if (ship.getPosition().getyPos() == position.getyPos()) {
				if (ship.getDirection().equals(Direction.EAST)) {
					covering = ship.getPosition().getxPos() <= position.getxPos() && ship.getPosition().getxPos() + ship.getLength() >= position.getxPos();
				} else if (ship.getDirection().equals(Direction.WEST)) {
					covering = ship.getPosition().getxPos() >= position.getxPos() && ship.getPosition().getxPos() - ship.getLength() <= position.getxPos();
				}
			}
		}
		return covering;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Board)) return false;
		return id != null && id.equals(((Board) o).getId());
	}
	@Override
	public int hashCode() {
		return 31;
	}

}
