package pl.grm.game.base;

import static pl.grm.game.base.GameFactory.*;

import java.util.logging.*;

public class GameBase {
	
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
