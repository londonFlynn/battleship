package pro0.battleship.models;

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
	
	Boolean targeted = false;

	public Boolean getTargeted() {
		return targeted;
	}

	public void setTargeted(Boolean targeted) {
		this.targeted = targeted;
	}
	
	
}
