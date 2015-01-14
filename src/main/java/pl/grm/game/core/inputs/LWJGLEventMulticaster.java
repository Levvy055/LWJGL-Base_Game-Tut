package pl.grm.game.core.inputs;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.input.Keyboard;

public class LWJGLEventMulticaster {
	private static ConcurrentHashMap<Integer, KeyListener>	keyListeners	= new ConcurrentHashMap<Integer, KeyListener>();
	private static HashMap<KeyListener, KeyListenerData>	listenersData	= new HashMap<KeyListener, KeyListenerData>();
	
	public static void keyActionPerformer() {
		if (!Keyboard.isCreated()) { return; }
		Iterator<Entry<Integer, KeyListener>> iterator = keyListeners.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, KeyListener> listenerEntry = iterator.next();
			if (Keyboard.isKeyDown(listenerEntry.getKey())) {
				KeyListener keyListener = listenerEntry.getValue();
				if (listenersData.get(keyListener).canActionBePerformed()) {
					keyListener.keyPressed(null);
				}
			}
		}
	}
	
	public static void addKeyListener(int key, KeyListener keyListener) {
		keyListeners.put(key, keyListener);
		listenersData.put(keyListener, new KeyListenerData());
	}
	
	public static boolean containsListener(int key) {
		return keyListeners.containsKey(key);
	}
	
	public static void removeListener(int key) {
		keyListeners.remove(key);
	}
	
	public static void lockKey(int key) {
		KeyListener listener = keyListeners.get(key);
		KeyListenerData keyListenerData = listenersData.get(listener);
		keyListenerData.setEnabled(false);
	}
	
	public static void unlockKey(int key) {
		KeyListener listener = keyListeners.get(key);
		KeyListenerData keyListenerData = listenersData.get(listener);
		keyListenerData.setEnabled(true);
	}
	
	public static void lockKeyForTime(int key, long secondsLock) {
		KeyListener listener = keyListeners.get(key);
		KeyListenerData keyListenerData = listenersData.get(listener);
		keyListenerData
				.setLastTimeUsed((long) ((System.currentTimeMillis() + secondsLock * 1000) + keyListenerData
						.getDeadzone()));
	}
}
