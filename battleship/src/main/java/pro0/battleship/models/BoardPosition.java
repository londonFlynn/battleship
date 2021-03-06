package pro0.battleship.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="BoardPosition")
@Table(name="board_position")
public class BoardPosition {
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private int id;
	
	private int xPos =-1;
	private int yPos =-1;
	
	public BoardPosition() {}
	
	public BoardPosition(int xPos, int yPos) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equal = false;
		if (obj.getClass() == BoardPosition.class) {
			BoardPosition that = (BoardPosition) obj;
			equal = this.id == that.id || (this.xPos == that.xPos && this.yPos == that.yPos);
		}
		return equal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardPosition [xPos=").append(xPos).append(", yPos=").append(yPos).append("]");
		return builder.toString();
	}
	
	

}
