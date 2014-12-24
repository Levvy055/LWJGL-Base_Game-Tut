package pl.grm.game.core.factory;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import pl.grm.game.core.*;
import pl.grm.game.core.Timer;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.pregamestages.*;

public class GameFactory {
	
	/**
	 * creates logger and associates with fileHandler
	 * 
	 * @return Logger
	 */
	public static Logger setupLogger() {
		Logger logger = Logger.getLogger(GameParameters.LOG_FILE_NAME);
		FileHandler fHandler = null;
		try {
			fHandler = new FileHandler(GameParameters.GAME_LOCATION + GameParameters.LOG_FILE_NAME,
					1048476, 1, true);
			SimpleFormatter formatter = new SimpleFormatter();
			fHandler.setFormatter(formatter);
		}
		catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		logger.addHandler(fHandler);
		logger.info("Game Started");
		return logger;
	}
	
	/**
	 * Inits lwjgl Display with base parameters
	 */
	public static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle(GameParameters.GAME_TITLE);
		}
		catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates GameController and sets all of its params
	 * 
	 * @return GameController
	 */
	public static GameController createGameController() {
		GameController.instance = new GameController();
		GameController gameController = GameController.instance;
		Game game = createGame();
		gameController.setGame(game);
		gameController.setTimer(new Timer(gameController));
		gameController.setIterator(new GameEventIterator());
		gameController.setGameLoop(new RenderThread());
		gameController.setGameRenderStage(GameRenderTypeStage.MENU);
		gameController.setGamePreStage(new Menu());
		gameController.setListenerMap(new HashMap<Integer, KeyListener>());
		return gameController;
	}
	
	/**
	 * Creates Game with specified params
	 * 
	 * @return
	 */
	private static Game createGame() {
		Game game = new Game();
		game.setEvents(new PriorityQueue<GameEvent>());
		game.setEntities(new ArrayList<Entity>());
		game.setRenderQueue(new PriorityQueue<Entity>(GameParameters.RENDER_QUEUE_CAPACITY));
		return game;
	}
	
	/**
	 * Starts a game
	 * 
	 * @param gameController
	 */
	public static void startGame(GameController gameController) {
		gameController.setRunning(true);
		gameController.getGameLoop().start();
	}
	
	/**
	 * Starts iterator over game Events
	 * 
	 * @param gameController
	 */
	public static void startIterator(GameController gameController) {
		gameController.getIterator().fullIterator();
	}
	
	public static void setLoadingMenu(GameController gameController) {
		gameController.setGameRenderStage(GameRenderTypeStage.MENU_LOADING);
		gameController.setGamePreStage(new Menu()); // TODO not menu
	}
	
	public static void setMenu(GameController gameController) {
		gameController.setGameRenderStage(GameRenderTypeStage.MENU);
		gameController.setGamePreStage(new Menu());
	}
	
	public static void setLoadingGame(GameController gameController) {
		gameController.setGameRenderStage(GameRenderTypeStage.GAME_LOADING);
		gameController.setGamePreStage(new Menu());// TODO not menu
	}
	
	public static void setGame(GameController gameController) {
		gameController.setGameRenderStage(GameRenderTypeStage.GAME);
	}
}
