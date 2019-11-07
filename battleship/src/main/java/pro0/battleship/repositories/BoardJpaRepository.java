package pro0.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro0.battleship.models.Board;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {

}
