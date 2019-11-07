package pro0.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro0.battleship.models.BoardRow;

public interface BoardRowJpaRepository extends JpaRepository<BoardRow, Integer>{

}
