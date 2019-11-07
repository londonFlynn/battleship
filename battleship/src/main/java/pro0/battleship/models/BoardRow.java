package pro0.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="BoardRow")
@Table(name="boardRow")
public class BoardRow {
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
//	private List<Boolean> cells = new ArrayList<Boolean>();
	
	public BoardRow() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

//	List<Boolean> getCells() {
//		return cells;
//	}
//
//	void setCells(List<Boolean> cells) {
//		this.cells = cells;
//	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BoardRow)) return false;
		return id != null && id.equals(((BoardRow) o).getId());
	}
	@Override
	public int hashCode() {
		return 31;
	}
	

}
