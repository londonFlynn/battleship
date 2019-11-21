package pro0.battleship.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro0.battleship.models.Ship;

public interface ShipJpaRepository extends JpaRepository<Ship, Long> {
	
	@Query("SELECT s "+
			"FROM Game g " +
			"JOIN g.boards b " +
			"JOIN b.ships s "+
			"WHERE g.id = :gameId AND b.user LIKE :username")
	public List<Ship> getShipsByGameId(@Param(value="gameId") int gameId, @Param(value="username") String username);
	
	@Query("SELECT s "+
			"FROM Game g " +
			"JOIN g.boards b " +
			"JOIN b.ships s "+
			"WHERE g.id = :gameId")
	public List<Ship> getShipsByGameId(@Param(value="gameId") int gameId);

}
