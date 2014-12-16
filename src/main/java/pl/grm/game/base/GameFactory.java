package pl.grm.game.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameFactory {

	public static Logger setupLogger() {
		Logger logger = Logger.getLogger(GameParameters.LOG_FILE_NAME);
		FileHandler fHandler = null;
		try {
			fHandler = new FileHandler(GameParameters.GAME_LOCATION
					+ GameParameters.LOG_FILE_NAME, 1048476, 1, true);
			SimpleFormatter formatter = new SimpleFormatter();
			fHandler.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		logger.addHandler(fHandler);
		logger.info("Game Started");
		return logger;
	}

	public static void initWindow() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle(GameParameters.GAME_TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public static GameController createGameController(Logger logger) {
		GameController gameController = new GameController(logger);
		gameController.setGameLoop(new GameLoop(gameController));
		gameController.setEvents(new ArrayList<GameEvent>());
		gameController.setEntities(new ArrayList<Entity>());
		return gameController;
	}

	public static void startGame(GameController gameController) {
		gameController.getGameLoop().run();
	}
}
