package pro0.battleship.models;

import javax.persistence.Entity;

public class ShipPlacementRequest {
	private BoardPosition position;
	private String direction;
	private String shipType;
	
	public BoardPosition getPosition() {
		return position;
	}
	public void setPosition(BoardPosition position) {
		this.position = position;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	
	
}
