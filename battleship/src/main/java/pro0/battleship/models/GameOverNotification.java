package pro0.battleship.models;

public class GameOverNotification {
	private String winner;

	public GameOverNotification(String winner) {
		super();
		this.winner = winner;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	

}
