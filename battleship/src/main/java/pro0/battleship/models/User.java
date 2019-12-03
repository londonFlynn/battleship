package pro0.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="User")
@Table(name="user")
public class User {
	@Id
	private String username;
	private String password;
	private String imageUrl = "./babyduck.jpg";
	private int gamesWon;
	private int gamesLost;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Board> boards = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> games = new ArrayList<>();
	@OneToMany(mappedBy = "otherUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> otherGames = new ArrayList<>();
	
	
	public User() {
	}

	public User(String username, String password, String imageUrl, int gamesWon, int gamesLost) {
		this.username = username;
		this.password = password;
		this.imageUrl = imageUrl;
		this.gamesWon = gamesWon;
		this.gamesLost = gamesLost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesLost() {
		return gamesLost;
	}

	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}

	public List<Game> getGames() {
		return games;
	}
	
	public List<Game> getOtherGames() {
		return otherGames;
	}

	void addBoard(Board board) {
		boards.add(board);
		board.setUser(this);
	}
	void removeBoard(Board board) {
		boards.remove(board);
		board.setUser(null);
	}
//	public void addGame(Game game) {
//		games.add(game);
//		game.setOwner(this);
//	}
//	public void removeGame(Game game) {
//		games.remove(game);
//		game.setOwner(null);
//	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=").append(username).append(", password=").append(password).append(", imageUrl=")
				.append(imageUrl).append(", gamesWon=").append(gamesWon).append(", gamesLost=").append(gamesLost)
				.append("]");
		return builder.toString();
	}
	@Override
	public boolean equals(Object other) {
		if (other.getClass() == User.class) {
			return this.username.equals(((User) other).username);
		} else {
			return false;
		}
	}
	
	
	

}
