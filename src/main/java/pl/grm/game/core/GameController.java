package pl.grm.game.core;

import java.util.*;

import pl.grm.game.core.entities.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.pregamestages.*;

public class GameController {
	/** Game main Loop of rendering things on screen */
	private RenderThread				gameLoop;
	/** Iterator which iterates over events */
	private GameEventIterator			iterator;
	/** Specifies that game is running or not */
	private boolean						running;
	/** Timer to count game FPS and Delta */
	private Timer						timer;
	/** The Game Container */
	private Game						game;
	/** Stage of game rendering */
	private GameRenderTypeStage			gameRenderStage;
	/** Stage based on gameRenderStage */
	private IGamePreStage				gamePreStage;
	/** Map of keyboard listener */
	private Map<Integer, KeyListener>	listenerMap;
	/** Instance of GameController to control all game components */
	public static GameController		instance;
	
	public static void addEntity(Entity entity) {
		instance.game.addEntity(entity);
	}
	
	public void stopGame() {
		setRunning(false);
	}
	
	public RenderThread getGameLoop() {
		return gameLoop;
	}
	
	public void setGameLoop(RenderThread loop) {
		this.gameLoop = loop;
	}
	
	public Queue<GameEvent> getEvents() {
		return game.getEvents();
	}
	
	public ArrayList<Entity> getEntities() {
		return game.getEntities();
	}
	
	public Queue<Entity> getRenderQueue() {
		return game.getRenderQueue();
	}
	
	public GameEventIterator getIterator() {
		return iterator;
	}
	
	public void setIterator(GameEventIterator iterator) {
		this.iterator = iterator;
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
	
	public IGamePreStage getGamePreStage() {
		return gamePreStage;
	}
	
	public void setGamePreStage(IGamePreStage gameStage) {
		this.gamePreStage = gameStage;
	}
	
	public Map<Integer, KeyListener> getListenerMap() {
		return listenerMap;
	}
	
	public void setListenerMap(Map<Integer, KeyListener> listenerMap) {
		this.listenerMap = listenerMap;
	}
}
