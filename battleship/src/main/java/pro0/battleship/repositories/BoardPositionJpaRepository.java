package pro0.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro0.battleship.models.BoardPosition;

public interface BoardPositionJpaRepository extends JpaRepository<BoardPosition, Integer>{

}
