package pro0.battleship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro0.battleship.models.User;

public interface UserJpaRepository extends JpaRepository<User, String>  {
	@Query("SELECT u "+
			"FROM Game g " +
			"JOIN g.user u " +
			"WHERE g.id = :gameId")
	public Optional<User> getHostUserForGame(@Param(value="gameId") int gameId);
	@Query("SELECT u "+
			"FROM Game g " +
			"JOIN g.otherUser u " +
			"WHERE g.id = :gameId")
	public Optional<User> getGuestUserForGame(@Param(value="gameId") int gameId);
	@Query("SELECT u "+
			"FROM Game g " +
			"JOIN g.currentTurn u " +
			"WHERE g.id = :gameId")
	public Optional<User> getTurnForGame(@Param(value="gameId") int gameId);

}
