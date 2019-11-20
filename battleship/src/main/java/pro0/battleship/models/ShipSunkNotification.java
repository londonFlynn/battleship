package pro0.battleship.models;

import pro0.battleship.enums.ShipType;

public class ShipSunkNotification {
	private String playerUsername;
	private ShipType shipType;
	public ShipSunkNotification() {}
	
	public ShipSunkNotification(String playerUsername, ShipType ship) {
		super();
		this.playerUsername = playerUsername;
		this.shipType = ship;
	}

	public String getPlayerUsername() {
		return playerUsername;
	}
	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}
	public ShipType getShipType() {
		return shipType;
	}
	public void setShipType(ShipType ship) {
		this.shipType = ship;
	}
	
	

}
