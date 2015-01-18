package pl.grm.game.core;

import java.awt.event.*;
import java.util.*;

import pl.grm.game.core.entities.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.factory.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.loadstages.*;
import pl.grm.game.core.timers.*;

import com.google.common.collect.*;

public class GameController {
	/** Game main Loop of rendering things on screen */
	private RenderThread				renderThread;
	/** Iterator which iterates over events */
	private LogicThread			logicThread;
	/** Specifies that game is running or not */
	private boolean						running;
	/** Timer to count game FPS and Delta */
	private FPSTimer					fpsTimer;
	/** Timer to count game logic tps */
	private TickTimer					tpsTimer;
	/** The Game Container */
	private Game						game;
	/** Stage of game rendering */
	private GameLoadStage				gameLoadStage;
	/** Map of keyboard listener */
	private Map<Integer, KeyListener>	listenerMap;
	/** Instance of GameController to control all game components */
	public static GameController		instance;
	
	public static void addEntity(Entity entity) {
		instance.game.addEntity(entity);
	}
	
	public static void destroyAllEntities(int id) {
		instance.game.destroyAllEntities(id);
	}
	
	@SuppressWarnings("static-access")
	public static synchronized void stopGame() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("Closing Thread");
				GameFactory.changeLoadStageTo(GameLoadStage.CLOSING);
				LWJGLEventMulticaster.discharge();
				instance.setRunning(false);
				long initTime = System.currentTimeMillis();
				while (instance.getLogicThread().isAlive()
						|| instance.getRenderThread().isAlive()) {
					try {
						Thread.currentThread().sleep(100l);
					}
					catch (InterruptedException e) {
						GameLogger.logException(e);
					}
					long timeDelay = System.currentTimeMillis() - initTime;
					if (timeDelay > 5 * 1000) {
						System.out.println("Thread rage quit: " + Thread.currentThread().getName());
						System.exit(0);
						break;
					}
				}
			}
		}).start();
	}
	
	public RenderThread getRenderThread() {
		return renderThread;
	}
	
	public void setGameLoop(RenderThread thread) {
		this.renderThread = thread;
	}
	
	public Queue<GameEvent> getEvents() {
		return game.getEvents();
	}
	
	public Multimap<Integer, Entity> getEntities() {
		return game.getEntities();
	}
	
	public Queue<Entity> getRenderQueue() {
		return game.getRenderQueue();
	}
	
	public LogicThread getLogicThread() {
		return logicThread;
	}
	
	public void setLogicThread(LogicThread thread) {
		this.logicThread = thread;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public FPSTimer getFPSTimer() {
		return fpsTimer;
	}
	
	public void setFPSTimer(FPSTimer fpsTimer) {
		this.fpsTimer = fpsTimer;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public GameLoadStage getGameLoadStage() {
		return gameLoadStage;
	}
	
	public void setGameLoadStage(GameLoadStage gameLoadStage) {
		this.gameLoadStage = gameLoadStage;
	}
	
	public Map<Integer, KeyListener> getListenerMap() {
		return listenerMap;
	}
	
	public void setListenerMap(Map<Integer, KeyListener> listenerMap) {
		this.listenerMap = listenerMap;
	}
	
	public TickTimer getTpsTimer() {
		return tpsTimer;
	}
	
	public void setTpsTimer(TickTimer tpsTimer) {
		this.tpsTimer = tpsTimer;
	}
}
