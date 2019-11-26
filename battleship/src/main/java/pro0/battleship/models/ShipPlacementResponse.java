package pro0.battleship.models;

import java.util.List;

import javax.persistence.Entity;


public class ShipPlacementResponse {
	private boolean success = false;
	private List<BoardPosition> positions;
	public ShipPlacementResponse() {}
	
	public ShipPlacementResponse(boolean success, List<BoardPosition> list) {
		super();
		this.success = success;
		this.positions = list;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<BoardPosition> getPositions() {
		return positions;
	}
	public void setPositions(List<BoardPosition> positions) {
		this.positions = positions;
	}
	
	

}
