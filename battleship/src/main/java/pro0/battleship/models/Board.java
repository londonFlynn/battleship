package pro0.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import pro0.battleship.controllers.ShipPlacementController;
import pro0.battleship.enums.ShipType;

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
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BoardRow>spaces = new ArrayList<BoardRow>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private Game game;
	

	public Board() {
		createShips();
		createBoardRows();
	}

	public Board(User user) {
		setUser(user);
		createShips();
		createBoardRows();
	}
	
	public void addShip(Ship ship) {
		ships.add(ship);
		ship.setBoard(this);
	}
	public void removeShip(Ship ship) {
		ships.remove(ship);
		ship.setBoard(null);
	}

	private void createShips() {
		addShip(new Ship(ShipType.AIRCRAFT_CARRIER, ShipPlacementController.getShipLengthFromType(ShipType.AIRCRAFT_CARRIER)));
		addShip(new Ship(ShipType.BATTLESHIP, ShipPlacementController.getShipLengthFromType(ShipType.BATTLESHIP)));
		addShip(new Ship(ShipType.CRUSER, ShipPlacementController.getShipLengthFromType(ShipType.CRUSER)));
		addShip(new Ship(ShipType.SUBMARINE, ShipPlacementController.getShipLengthFromType(ShipType.SUBMARINE)));
		addShip(new Ship(ShipType.DESROYER, ShipPlacementController.getShipLengthFromType(ShipType.DESROYER)));
	}
	private void createBoardRows() {
		for (int i = 0; i < boardSize; i++) {
			spaces.add(new BoardRow());
		}
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

	public void setSpace(int x, int y, boolean value) {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	void addBoardRow(BoardRow row) {
		spaces.add(row);
		row.setBoard(this);
	}
	void removeBoardRow(BoardRow row) {
		spaces.remove(row);
		row.setBoard(null);
	}
	
	public static boolean spaceIsOnBoard(short xPos, short yPos) {
		return xPos > 0 && yPos > 0 && xPos <= Board.boardSize && yPos <= Board.boardSize;
	}
	public boolean spaceIsOpen(short xPos, short yPos) {
		//TODO logic to determine if a ship is in a space;
		return false;
	}
	public boolean shipIsCoveringSpace(Ship ship, short xPos, short yPos) {
		return false;
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
