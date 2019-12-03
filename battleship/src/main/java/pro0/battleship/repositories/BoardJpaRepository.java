package pro0.battleship.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro0.battleship.models.Board;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
	
	@Query(
			"SELECT b " +
			"FROM Game g " +
			"JOIN g.boards b "+
			"WHERE g.id LIKE :gameId")
	public List<Board> findByGame(@Param(value="gameId") int gameId);
	
	

}
