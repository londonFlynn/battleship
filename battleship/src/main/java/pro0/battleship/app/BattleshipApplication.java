package pro0.battleship.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= { "pro0.battleship.*" })
@EntityScan(basePackages= { "pro0.battleship.models" })
@EnableJpaRepositories(basePackages= { "pro0.battleship.repositories" })
public class BattleshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}

}
