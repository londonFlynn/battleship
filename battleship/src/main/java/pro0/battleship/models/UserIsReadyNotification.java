package pro0.battleship.models;

public class UserIsReadyNotification {
	private String username;
	private boolean ready;
	public UserIsReadyNotification() {}
	
	public UserIsReadyNotification(String username, boolean ready) {
		super();
		this.username = username;
		this.ready = ready;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserIsReadyNotification [username=").append(username).append(", ready=").append(ready)
				.append("]");
		return builder.toString();
	}
	
	
	

}
