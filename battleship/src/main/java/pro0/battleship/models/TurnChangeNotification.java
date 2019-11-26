package pro0.battleship.models;

import javax.persistence.Entity;

public class TurnChangeNotification {
	int id;
	private String playerUsername;

	public TurnChangeNotification(String playerUsername) {
		super();
		this.playerUsername = playerUsername;
	}

	public String getPlayerUsername() {
		return playerUsername;
	}

	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}
	

}
