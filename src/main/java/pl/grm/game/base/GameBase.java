package pl.grm.game.base;

import java.util.logging.Logger;

public class GameBase {

	private static GameController gameController;
	private static Logger logger;

	public static void main(String[] args) {
		logger = GameFactory.setupLogger();
		GameFactory.initWindow();
		gameController = GameFactory.createGameController(logger);
		GameFactory.startGame(gameController);
		logger.info("Game stopped!");
	}
}
