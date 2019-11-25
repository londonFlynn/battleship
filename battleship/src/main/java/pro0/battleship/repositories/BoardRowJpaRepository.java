package pro0.battleship.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro0.battleship.models.BoardRow;

public interface BoardRowJpaRepository extends JpaRepository<BoardRow, Integer>{
	@Query(
			"SELECT r " +
			"FROM Game g " +
			"JOIN g.boards b " +
			"JOIN b.rows r " +
			"WHERE r.rowNumber LIKE :rowNumber AND g.id LIKE :gameId AND b.user LIKE :username")
	public List<BoardRow> searchByGameAndRowNumber(@Param(value="gameId") int gameId, @Param(value="rowNumber") int rowNumber, @Param(value="username") String username);
//	@Query(
//			"SELECT r " +
//			"FROM BoardRow r " +
//			"JOIN board_rows s " +
//			"ON s.rows_id = r.id " +
//			"JOIN board b " +
//			"ON b.id = s.board_id "+
//			"JOIN game_boards gb " +
//			"ON gb.boards_id = b.id "+
//			"JOIN game g " +
//			"ON gb.game_id = g.id "+
//			"WHERE r.rowNumber LIKE :rowNumber AND g.id LIKE :gameId AND b.user_id LIKE :username")

}
