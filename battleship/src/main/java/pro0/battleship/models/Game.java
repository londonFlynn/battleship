package pro0.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pro0.battleship.controllers.ShipPlacementController;
import pro0.battleship.enums.ShipType;
import pro0.battleship.repositories.BoardCellJpaRepository;
import pro0.battleship.repositories.BoardJpaRepository;
import pro0.battleship.repositories.BoardRowJpaRepository;
import pro0.battleship.repositories.GameJpaRepository;
import pro0.battleship.repositories.ShipJpaRepository;

@Entity(name="Game")
@Table(name="game")
public class Game {
	
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private Integer id;
	@OneToMany(orphanRemoval = true)
	@JsonIgnore
	private List<Board> boards = new ArrayList<Board>();
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	@ManyToOne
	private User otherUser;
	@ManyToOne
	private User currentTurn;
	
	private boolean isActive = true;
	
	public static Game newGame(User user, GameJpaRepository gameJpaRepository, BoardJpaRepository boardJpaRepository, BoardRowJpaRepository boardRowJpaRepository, BoardCellJpaRepository boardCellJpaRepository, ShipJpaRepository shipJpaRepository) {
			List<Board> boards = new ArrayList<>();
			for (int i = 0; i < 2; i++) {
				List<BoardRow> boardRows = new ArrayList<>();
				for (int j = 0; j < 10; j++) {
					List<BoardCell> boardCells = new ArrayList<>();
					for (int k = 0; k < 10; k++) {
						BoardCell cell = new BoardCell();
						boardCells.add(cell);
						BoardCell newCell = boardCellJpaRepository.save(cell);
					}
					BoardRow row = new BoardRow();
					row.setCells(boardCells);
					boardRows.add(row);
					BoardRow newRow = boardRowJpaRepository.save(row);
				}
				Board board = new Board();
				board.setRows(boardRows);
				setupShips(board, shipJpaRepository);
				boards.add(board);
				Board newBoard = boardJpaRepository.save(board);
			}
			Game game = new Game();
			game.setUser(user);
			game.setBoards(boards);
			game.getBoards().get(0).setUser(user);
			Game returnGame = gameJpaRepository.save(game);
			return game;
	}
	
	private static void setupShips(Board board, ShipJpaRepository shipJpaRepository) {
		Ship aircraftCarrior = new Ship(ShipType.AIRCRAFT_CARRIER, ShipPlacementController.getShipLengthFromType(ShipType.AIRCRAFT_CARRIER));
		Ship battleShip = new Ship(ShipType.BATTLESHIP, ShipPlacementController.getShipLengthFromType(ShipType.BATTLESHIP));
		Ship submarine = new Ship(ShipType.SUBMARINE, ShipPlacementController.getShipLengthFromType(ShipType.SUBMARINE));
		Ship cruser = new Ship(ShipType.CRUISER, ShipPlacementController.getShipLengthFromType(ShipType.CRUISER));
		Ship destroyer = new Ship(ShipType.DESROYER, ShipPlacementController.getShipLengthFromType(ShipType.DESROYER));
		board.getShips().add(aircraftCarrior);
		board.getShips().add(battleShip);
		board.getShips().add(submarine);
		board.getShips().add(cruser);
		board.getShips().add(destroyer);
	}

	public Game() {
//		setupBoards();
	}
	
	public Game(User user, User otherUser) {
		setUser(user);
		setOtherUser(otherUser);
//		setupBoards();
	}
//	public void setupBoards() {
//		boards = new ArrayList<Board>();
//		boards.add(new Board(user));
//		boards.add(new Board(otherUser));
//	}
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User owner) {
		this.user = owner;
	}
	public User getOtherUser(User user) {
		if (user.equals(this.user)) {
			return otherUser;
		} else if (user.equals(otherUser)) {
			return this.user;
		} else {
			return null;
		}
	}
	public User getOtherUser() {
		return otherUser;
	}
	public void setOtherUser(User other) {
		this.otherUser = other;
	}
	
	public Board getUsersBoard(User user) {
		Board result = null;
		for (Board board : boards) {
			if(board.getUser().equals(user)) {
				result = board;
			}
		}
		return result;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public User getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(User currentTurn) {
		this.currentTurn = currentTurn;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Game)) return false;
		return id != null && id.equals(((Game) o).getId());
	}
	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [id=").append(id).append(", boards=").append(boards).append(", user=").append(user)
				.append(", otherUser=").append(otherUser).append(", currentTurn=").append(currentTurn).append("]");
		return builder.toString();
	}

	

}
