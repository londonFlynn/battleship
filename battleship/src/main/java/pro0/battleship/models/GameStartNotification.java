package pro0.battleship.models;

public class GameStartNotification {
	private boolean started;
	private TurnChangeNotification turn;

	public GameStartNotification(boolean started, TurnChangeNotification turn) {
		super();
		this.started = started;
		this.turn = turn;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public TurnChangeNotification getTurn() {
		return turn;
	}

	public void setTurn(TurnChangeNotification turn) {
		this.turn = turn;
	}
	

}
