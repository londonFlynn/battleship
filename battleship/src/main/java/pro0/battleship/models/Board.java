package pro0.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import pro0.battleship.enums.Direction;

@Entity(name = "Board")
@Table(name = "board")
public class Board {
	@Transient
	public static final short boardSize = 10;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ship> ships = new ArrayList<Ship>();
	// TODO store spaces data in JPA
	@OneToMany(orphanRemoval = true)
	@Column(name = "rows")
	private List<BoardRow> rows = new ArrayList<BoardRow>();
	
	@ManyToOne(fetch = FetchType.LAZY)
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
	
	public boolean hasSpaceBeenTargeted(int xPos, int yPos) {
		return  rows.get(yPos-1).hasSpaceBeenTargeted(xPos);
	}

	public void targetSpace(int xPos, int yPos) {
		 rows.get(yPos-1);
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

	public static boolean spaceIsOnBoard(short xPos, short yPos) {
		return xPos > 0 && yPos > 0 && xPos <= Board.boardSize && yPos <= Board.boardSize;
	}
	public boolean spaceIsOpen(short xPos, short yPos) {
		boolean open = true;
		for (Ship ship : ships) {
			open = open && !shipIsCoveringSpace(ship, xPos, yPos);
		}
		return open;
	}
	public boolean shipIsCoveringSpace(Ship ship, short xPos, short yPos) {
		boolean covering = false;
		if (ship != null && ship.getXPos() != 0 && ship.getYPos() != 0 && ship.getDirection() != null && ship.getLength() > 0) {
			if (ship.getXPos() == xPos) {
				if (ship.getDirection().equals(Direction.NORTH)) {
					covering = ship.getYPos() >= yPos && ship.getYPos() - ship.getLength() <= yPos;
				} else if (ship.getDirection().equals(Direction.SOUTH)) {
					covering = ship.getYPos() <= yPos && ship.getYPos() + ship.getLength() >= yPos;
				}
			} else if (ship.getYPos() == yPos) {
				if (ship.getDirection().equals(Direction.EAST)) {
					covering = ship.getXPos() <= xPos && ship.getXPos() + ship.getLength() >= xPos;
				} else if (ship.getDirection().equals(Direction.WEST)) {
					covering = ship.getXPos() >= xPos && ship.getXPos() - ship.getLength() <= xPos;
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
