package pro0.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro0.battleship.models.BoardCell;

public interface BoardCellJpaRepository extends JpaRepository<BoardCell, Integer>{

}
