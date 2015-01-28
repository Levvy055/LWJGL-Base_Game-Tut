package pl.grm.game.core.basethreads;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

import org.lwjgl.input.*;

import pl.grm.game.core.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.loadstages.*;
import pl.grm.game.core.misc.*;

public class LWJGLEventMulticaster extends Thread {
	private static ConcurrentHashMap<Integer, GameKeyListener>	keyListenersHandler;
	private static ConcurrentHashMap<String, GameKeyListener>	buttonListenersHandler;
	private static Queue<GameEvent>								gameEventsQueue;
	private static LWJGLEventMulticaster						eventCollector;
	private static LWJGLEventMulticaster						eventCaster;
	private boolean												stopInvoked	= false;
	private static boolean										initialised	= false;
	private boolean												finished	= false;
	private MCType												type		= MCType.NONE;
	
	private LWJGLEventMulticaster(MCType type) {
		this.type = type;
	}
	
	public static void init() {
		if (eventCollector == null) {
			eventCollector = new LWJGLEventMulticaster(MCType.COLLECTOR);
		}
		if (eventCaster == null) {
			eventCaster = new LWJGLEventMulticaster(MCType.CASTER);
		}
		if (gameEventsQueue == null) {
			gameEventsQueue = new LinkedList<GameEvent>();
		}
		if (keyListenersHandler == null) {
			keyListenersHandler = new ConcurrentHashMap<Integer, GameKeyListener>();
		}
		if (buttonListenersHandler == null) {
			buttonListenersHandler = new ConcurrentHashMap<String, GameKeyListener>();
		}
		setInitialised(true);
	}
	
	public static boolean startMulitCaster() {
		if (isInitialised()) {
			eventCollector.start();
			eventCaster.start();
			return true;
		}
		return false;
	}
	
	@Override
	public void run() {
		switch (this.type) {
			case COLLECTOR :
				currentThread().setName("LWJGL Event Collector");
				collectEvents();
				break;
			case CASTER :
				currentThread().setName("LWJGL Event Multicaster");
				castEvents();
				break;
			default :
				break;
		}
	}
	
	/**
	 * Stops the event caster
	 */
	public static synchronized void discharge() {
		long initTime = System.currentTimeMillis();
		eventCollector.setStopInvoked(true);
		while (!(eventCollector.isFinished() && eventCaster.isFinished())) {
			try {
				sleep(100l);
			}
			catch (InterruptedException e) {
				GameLogger.logException(e);
			}
			if (eventCollector.isFinished() && gameEventsQueue.isEmpty()) {
				eventCaster.setStopInvoked(true);
			}
			long timeDelay = System.currentTimeMillis() - initTime;
			if (!eventCaster.isFinished() && timeDelay > 2 * 1000) {
				eventCaster.setStopInvoked(true);
			}
			if (timeDelay > 4 * 1000) {
				break;
			}
		}
		eventCollector = null;
		eventCaster = null;
		setInitialised(false);
	}
	
	private synchronized void collectEvents() {
		while (!isStopInvoked()) {
			if (!Keyboard.isCreated()) {
				continue;
			}
			while (Keyboard.next()) {
				int eventKey = Keyboard.getEventKey();
				ArrayList<GameListener> eventListeners = collectKeyListeners(Keyboard.getEventKey());
				KeyEvent event = new KeyEvent(eventKey, Keyboard.getEventKeyState(),
						System.currentTimeMillis(), Keyboard.isRepeatEvent(), eventListeners);
				gameEventsQueue.add(event);
			}
			if (GameController.getGameStage() == GameLoadStage.MAIN_MENU) {
				while (MainMenu.next()) {
					String eventButton = MainMenu.getEventButton();
					ArrayList<GameListener> eventListeners = collectKeyListeners(eventButton);
					KeyEvent event = new KeyEvent(eventButton, MainMenu.getEventKeyState(),
							System.currentTimeMillis(), MainMenu.isRepeatEvent(), eventListeners);
					gameEventsQueue.add(event);
				}
			}
		}
		setFinished(true);
	}
	
	private ArrayList<GameListener> collectKeyListeners(int eventKey) {
		ArrayList<GameListener> eventListeners = new ArrayList<GameListener>();
		Iterator<Entry<Integer, GameKeyListener>> iterator = LWJGLEventMulticaster.keyListenersHandler
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, GameKeyListener> listenerEntry = iterator.next();
			if (listenerEntry.getKey() == eventKey) {
				GameKeyListener keyListener = listenerEntry.getValue();
				eventListeners.add(keyListener);
			}
		}
		return eventListeners;
	}
	
	private ArrayList<GameListener> collectKeyListeners(String eventButton) {
		ArrayList<GameListener> eventListeners = new ArrayList<GameListener>();
		Iterator<Entry<String, GameKeyListener>> iterator = LWJGLEventMulticaster.buttonListenersHandler
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, GameKeyListener> listenerEntry = iterator.next();
			if (listenerEntry.getKey() == eventButton) {
				GameKeyListener keyListener = listenerEntry.getValue();
				eventListeners.add(keyListener);
			}
		}
		return eventListeners;
	}
	
	private synchronized void castEvents() {
		while (!isStopInvoked()) {
			if (!gameEventsQueue.isEmpty()) {
				
				System.out.println(gameEventsQueue.size());
				gameEventsQueue.poll().perform();
			} else {
				try {
					sleep(100l);
				}
				catch (InterruptedException e) {
					GameLogger.logException(e);
				}
			}
			Iterator<GameEvent> iterator = gameEventsQueue.iterator();
			System.out.print("");
		}
		setFinished(true);
	}
	
	public static void addKeyListener(int key, GameKeyListener keyListener) {
		keyListenersHandler.put(key, keyListener);
	}
	
	public static void addButtonListener(String button, GameKeyListener gameButtonListener) {
		buttonListenersHandler.put(button, gameButtonListener);
		
	}
	
	public static boolean containsListener(int key) {
		return LWJGLEventMulticaster.keyListenersHandler.containsKey(key);
	}
	
	public static void removeListener(int key) {
		LWJGLEventMulticaster.keyListenersHandler.remove(key);
	}
	
	private enum MCType {
		NONE ,
		COLLECTOR ,
		CASTER;
	}
	
	public static boolean isInitialised() {
		return initialised;
	}
	
	private static void setInitialised(boolean initialised) {
		LWJGLEventMulticaster.initialised = initialised;
	}
	
	public boolean isStopInvoked() {
		return stopInvoked;
	}
	
	public void setStopInvoked(boolean stop) {
		this.stopInvoked = stop;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
