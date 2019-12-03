package pro0.battleship.models;

import java.util.List;

import pro0.battleship.enums.ShipType;


public class ShipPlacementResponse {
	private boolean success = false;
	private List<BoardPosition> positions;
	private ShipType shipType;
	public ShipPlacementResponse() {}
	
	public ShipPlacementResponse(boolean success, List<BoardPosition> list, ShipType shipType) {
		super();
		this.shipType = shipType;
		this.success = success;
		this.positions = list;
	}
	
	

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShipPlacementResponse [success=").append(success).append(", positions=").append(positions)
				.append(", shipType=").append(shipType).append("]");
		return builder.toString();
	}
	
	

}
