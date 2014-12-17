package pl.grm.game.base;

import java.util.*;
import java.util.logging.*;

public class GameController {
	/** Logger to log all msgs and errors */
	private Logger				logger;
	/** Game main Loop of rendering things on screen */
	private GameLoop			gameLoop;
	/** Queue of events */
	private Queue<GameEvent>	events;
	/** List of existing entities */
	private ArrayList<Entity>	entities;
	/** Queue of rendering things on screen */
	private Queue<Entity>		renderQueue;
	/** Iterator which iterates over events */
	private GameEventIterator	iterator;
	/** Specifies that game is running or not */
	private boolean				running;
	/** Timer to count game FPS and Delta */
	private Timer				timer;
	/** The Game Container */
	private Game				game;
	/** Stage of game rendering */
	private GameRenderTypeStage	gameRenderStage;
	
	public GameController(Logger logger) {
		this.logger = logger;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public GameLoop getGameLoop() {
		return gameLoop;
	}
	
	public void setGameLoop(GameLoop loop) {
		this.gameLoop = loop;
	}
	
	public Queue<GameEvent> getEvents() {
		return events;
	}
	
	public void setEvents(Queue<GameEvent> events) {
		this.events = events;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public Queue<Entity> getRenderQueue() {
		return renderQueue;
	}
	
	public void setRenderQueue(Queue<Entity> renderQueue) {
		this.renderQueue = renderQueue;
	}
	
	public GameEventIterator getIterator() {
		return iterator;
	}
	
	public void setIterator(GameEventIterator iterator) {
		this.iterator = iterator;
	}
	
	public void stopGame() {
		setRunning(false);
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public GameRenderTypeStage getGameRenderStage() {
		return gameRenderStage;
	}
	
	public void setGameRenderStage(GameRenderTypeStage gameRenderStage) {
		this.gameRenderStage = gameRenderStage;
	}
}
