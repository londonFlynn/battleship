package pro0.battleship.models;

public class TargetCellRequest {
	private BoardPosition position;

	public BoardPosition getPosition() {
		return position;
	}

	public void setPosition(BoardPosition position) {
		this.position = position;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TargetCellRequest [position=").append(position).append("]");
		return builder.toString();
	}
	
	
	
	
	

}
