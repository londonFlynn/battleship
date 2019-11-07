package pro0.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro0.battleship.models.User;

public interface UserJpaRepository extends JpaRepository<User, String>  {

}
