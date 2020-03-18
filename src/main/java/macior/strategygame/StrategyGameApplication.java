package macior.strategygame;

import macior.strategygame.game.Game;
import macior.strategygame.game.GameUpdater;
import macior.strategygame.game.TimeManager;
import macior.strategygame.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class StrategyGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrategyGameApplication.class, args);
	}
}
