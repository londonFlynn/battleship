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

import pro0.battleship.enums.ShipType;

@Entity(name = "Board")
@Table(name = "board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ship> ships = new ArrayList<Ship>();
	// TODO store spaces data in JPA
	@Transient
	private boolean[][] spaces = new boolean[10][10];
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private Game game;
	

	public Board() {
		createShips();
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

	private void createShips() {
		addShip(new Ship(ShipType.AIRCRAFT_CARRIER, (short) 5));
		addShip(new Ship(ShipType.BATTLESHIP, (short) 4));
		addShip(new Ship(ShipType.CRUSER, (short) 3));
		addShip(new Ship(ShipType.SUBMARINE, (short) 3));
		addShip(new Ship(ShipType.DESROYER, (short) 2));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public List<Ship> getShips() {
//		return ships;
//	}
//
//	public void setShips(List<Ship> ships) {
//		this.ships = ships;
//	}

	public boolean[][] getSpaces() {
		return spaces;
	}

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
