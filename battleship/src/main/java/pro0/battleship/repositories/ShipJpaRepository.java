package pro0.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro0.battleship.models.Ship;

public interface ShipJpaRepository extends JpaRepository<Ship, Long> {

}
