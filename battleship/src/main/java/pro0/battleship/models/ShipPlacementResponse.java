package pro0.battleship.models;

public class ShipPlacementResponse {
	private boolean success = false;
	private BoardPosition[] positions;
	public ShipPlacementResponse() {}
	
	public ShipPlacementResponse(boolean success, BoardPosition[] positions) {
		super();
		this.success = success;
		this.positions = positions;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public BoardPosition[] getPositions() {
		return positions;
	}
	public void setPositions(BoardPosition[] positions) {
		this.positions = positions;
	}
	
	

}
