package pro0.battleship.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="BoardCell")
@Table(name="boardCell")
public class BoardCell  {
	
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private Integer id;
	@Column(nullable = false)
	private boolean targeted = false;
	@Column(nullable = false)
	private boolean hit = false;

	public boolean getTargeted() {
		return targeted;
	}

	public void setTargeted(Boolean targeted) {
		this.targeted = targeted;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(Boolean hit) {
		this.hit = hit;
	}
	
	
}
