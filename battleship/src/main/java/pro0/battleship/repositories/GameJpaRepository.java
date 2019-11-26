package pro0.battleship.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro0.battleship.models.Board;
import pro0.battleship.models.Game;

public interface GameJpaRepository extends JpaRepository<Game, Integer> {
	
	@Query("SELECT u.username "+
			"FROM Game g "+
			"JOIN User u "+
			"ON u.username = g.otherUser "+
			"WHERE g.id = :gameId")
	public String getGuestUsername(@Param(value="gameId") int gameId);
	
	@Query("SELECT g.boards "+
			"FROM Game g " +
			"WHERE g.id = :gameId")
	public List<Board> getGameBoards(@Param(value="gameId") int gameId);
	
	@Query("SELECT u.username "+
			"FROM Game g "+
			"JOIN User u "+
			"ON u.username = g.user "+
			"WHERE g.id = :gameId")
	public String getHostUsername(@Param(value="gameId") int gameId);
	@Query("SELECT u.username "+
			"FROM Game g "+
			"JOIN User u "+
			"ON u.username = g.currentTurn "+
			"WHERE g.id = :gameId")
	public String getCurrentTurn(@Param(value="gameId") int gameId);
}
