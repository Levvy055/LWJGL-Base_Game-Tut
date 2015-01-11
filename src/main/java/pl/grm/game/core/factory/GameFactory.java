package pl.grm.game.core.factory;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import pl.grm.game.core.*;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.loadstages.*;
import pl.grm.game.core.timers.*;

import com.google.common.collect.*;

public class GameFactory {
	
	/**
	 * creates logger and associates with fileHandler
	 * 
	 * @return Logger
	 */
	public static Logger setupLogger() {
		Logger logger = Logger.getLogger(GameParameters.LOG_FILE_NAME);
		FileHandler fHandler = null;
		File mainDir = new File(GameParameters.GAME_LOCATION);
		try {
			if (!mainDir.exists()) {
				if (!mainDir.mkdir()) { throw new IOException("Cannot create main game folder!"); }
			}
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
			GameLogger.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	/**
	 * Creates GameController and sets all of its params
	 * 
	 * @return GameController
	 */
	public static GameController createGameController() {
		GameController gameController = new GameController();
		GameController.instance = gameController;
		changeLoadStageTo(GameLoadStage.INTRO);
		gameController.setFPSTimer(new FPSTimer());
		gameController.setTpsTimer(new TickTimer());
		gameController.setGameLoop(new RenderThread());
		gameController.setGame(createGame());
		gameController.setLogicIterator(new GameLogicIterator());
		gameController.setListenerMap(new HashMap<Integer, KeyListener>());
		return gameController;
	}
	
	/**
	 * Creates Game with specified params
	 * 
	 * @return Game created in this method
	 */
	private static Game createGame() {
		Game game = new Game();
		game.setEvents(new PriorityQueue<GameEvent>());
		HashMultimap<Integer, Entity> entityMap = HashMultimap.create();
		game.setEntities(entityMap);
		ConfigFile.loadDefaults();
		// efgame.setRenderQueue(new
		// PriorityQueue<Entity>(GameParameters.RENDER_QUEUE_CAPACITY));
		return game;
	}
	
	/**
	 * Starts a game
	 * 
	 * @param gameController
	 */
	public static void startGame(GameController gameController) {
		gameController.setRunning(true);
		gameController.getRenderThread().start();
		ConfigFile.loadConfigFromFile();
		gameController.getLogicIterator().mainIterator();
	}
	
	public static void changeLoadStageTo(GameLoadStage stage) {
		GameController.instance.setGameLoadStage(stage);
		switch (stage) {
			case INTRO :
				Intro.startStage();
				MainMenu.stopStage();
				GameLoading.stopStage();
				break;
			case MAIN_MENU :
				MainMenu.startStage();
				Intro.stopStage();
				GameLoading.stopStage();
				break;
			case GAME_LOADING :
				GameLoading.startStage();
				MainMenu.stopStage();
				Intro.stopStage();
				break;
			case CLOSING :
				GameLoading.stopStage();
				MainMenu.stopStage();
				Intro.stopStage();
			default :
				break;
		}
	}
}
