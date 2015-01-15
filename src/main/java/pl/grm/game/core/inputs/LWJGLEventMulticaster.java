package pl.grm.game.core.inputs;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

import org.lwjgl.input.*;

import pl.grm.game.core.events.*;

public class LWJGLEventMulticaster extends Thread {
	private static ConcurrentHashMap<Integer, GameKeyListener>	keyListenersHandler;
	private static Queue<GameEvent>								gameEventsQueue;
	private static LWJGLEventMulticaster						eventCollector;
	private static LWJGLEventMulticaster						eventCaster;
	private static boolean										stopInvoked	= false;
	private static boolean										initialised	= false;
	private MCType												type		= MCType.NONE;
	
	public LWJGLEventMulticaster(MCType type) {
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
		switch (type) {
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
	public static void discharge() {
		if (eventCollector == null && eventCaster == null) { return; }
		setStopInvoked(true);
		eventCollector = null;
		eventCaster = null;
	}
	
	public void castEvents() {
		while (!isStopInvoked()) {
			if (!gameEventsQueue.isEmpty()) {
				gameEventsQueue.poll().perform();
			}
		}
		
		Iterator<Entry<Integer, GameKeyListener>> iterator = LWJGLEventMulticaster.keyListenersHandler
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, GameKeyListener> listenerEntry = iterator.next();
			
			GameKeyListener keyListener = listenerEntry.getValue();
			if (keyListener.canActionBePerformed()) {
				keyListener.keyPressed(null);
			}
			
		}
	}
	
	public void collectEvents() {
		while (!isStopInvoked()) {
			if (!Keyboard.isCreated()) {
				continue;
			}
			while (Keyboard.next()) {
				KeyEvent event = new KeyEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState(),
						System.currentTimeMillis(), Keyboard.isRepeatEvent());
				gameEventsQueue.add(event);
			}
		}
	}
	
	public static void addKeyListener(int key, GameKeyListener keyListener) {
		LWJGLEventMulticaster.keyListenersHandler.put(key, keyListener);
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
	
	public static void setInitialised(boolean initialised) {
		LWJGLEventMulticaster.initialised = initialised;
	}
	
	public static boolean isStopInvoked() {
		return stopInvoked;
	}
	
	public static void setStopInvoked(boolean stop) {
		LWJGLEventMulticaster.stopInvoked = stop;
	}
}
