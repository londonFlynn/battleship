package pro0.battleship.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="BoardRow")
@Table(name="boardRow")
public class BoardRow {
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private Integer id;
	
	@Column(name = "row_number")
	private int rowNumber;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<BoardCell> cells = new ArrayList<BoardCell>();
	
	public BoardRow() {
		
	}
	
	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	boolean hasSpaceBeenTargeted(int xPos) {
		return cells.get(xPos).getTargeted();
	}

	void targetSpace(int xPos) {
		if (!hasSpaceBeenTargeted(xPos)) {
			cells.get(xPos).setTargeted(true);;
		}
	}
	
	
	
	public List<BoardCell> getCells() {
		return cells;
	}

	public void setCells(List<BoardCell> cells) {
		this.cells = cells;
	}

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
