package pro0.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro0.battleship.models.Game;

public interface GameJpaRepository extends JpaRepository<Game, Integer> {

}
