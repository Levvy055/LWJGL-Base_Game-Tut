package pl.grm.game;

import static pl.grm.game.core.factory.GameFactory.*;

import java.util.logging.*;

import pl.grm.game.core.*;

public class GameMain {
	
	private static GameController	gameController;
	private static Logger			logger;
	
	public static void main(String[] args) {
		Thread.currentThread().setName("Main");
		logger = setupLogger();
		gameController = createGameController(logger);
		initDisplay();
		startGame(gameController);
		startIterator(gameController);
		logger.info("Game stopped!");
	}
}
