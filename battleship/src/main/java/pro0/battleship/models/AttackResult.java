package pro0.battleship.models;

public class AttackResult {
	
	private boolean invalid = false;
	private String playerUsername;
	private BoardPosition position;
	private boolean hit = false;
	public AttackResult() {}
	
	public static AttackResult invalidAttackResult(String playerUsername) {
		AttackResult result = new AttackResult();
		result.setPlayerUsername(playerUsername);
		result.setInvalid(true);
		return result;
	}
	
	public AttackResult(String playerUsername, BoardPosition position, boolean hit) {
		super();
		this.invalid = false;
		this.playerUsername = playerUsername;
		this.position = position;
		this.hit = hit;
	}
	
	public boolean isInvalid() {
		return invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public String getPlayerUsername() {
		return playerUsername;
	}
	public BoardPosition getPosition() {
		return position;
	}
	public void setPosition(BoardPosition position) {
		this.position = position;
	}
	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}
	public boolean isHit() {
		return hit;
	}
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttackResult [invalid=").append(invalid).append(", playerUsername=").append(playerUsername)
				.append(", position=").append(position).append(", hit=").append(hit).append("]");
		return builder.toString();
	}
	
	

}
