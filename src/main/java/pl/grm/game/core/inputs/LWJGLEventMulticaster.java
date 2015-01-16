package pl.grm.game.core.inputs;

import java.util.*;
import java.util.concurrent.*;

import org.lwjgl.input.*;

import pl.grm.game.core.events.*;

public class LWJGLEventMulticaster extends Thread {
	private static ConcurrentHashMap<Integer, GameKeyListener>	keyListenersHandler;
	private static Queue<GameEvent>								gameEventsQueue;
	private static LWJGLEventMulticaster						eventCollector;
	private static LWJGLEventMulticaster						eventCaster;
	private boolean												stopInvoked	= false;
	private static boolean										initialised	= false;
	private boolean												finished	= false;
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
				System.out.println("Thread started: " + currentThread().getName());
				collectEvents();
				break;
			case CASTER :
				currentThread().setName("LWJGL Event Multicaster");
				System.out.println("Thread started: " + currentThread().getName());
				castEvents();
				break;
			default :
				break;
		}
		System.out.println("Thread end: " + currentThread().getName());
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
				e.printStackTrace();
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
				KeyEvent event = new KeyEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState(),
						System.currentTimeMillis(), Keyboard.isRepeatEvent());
				gameEventsQueue.add(event);
			}
		}
		setFinished(true);
	}
	
	private synchronized void castEvents() {
		while (!isStopInvoked()) {
			if (!gameEventsQueue.isEmpty()) {
				gameEventsQueue.poll().perform();
			} else {
				try {
					sleep(100l);
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Iterator<GameEvent> iterator = gameEventsQueue.iterator();
			System.out.print("");
		}
		setFinished(true);
		
		// Iterator<Entry<Integer, GameKeyListener>> iterator =
		// LWJGLEventMulticaster.keyListenersHandler
		// .entrySet().iterator();
		// while (iterator.hasNext()) {
		// Entry<Integer, GameKeyListener> listenerEntry = iterator.next();
		//
		// GameKeyListener keyListener = listenerEntry.getValue();
		// if (keyListener.canActionBePerformed()) {
		// keyListener.keyPressed(null);
		// }
		// }
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
