package pl.grm.game.core.inputs;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

import org.lwjgl.input.*;

public class KeyManager {
	private static ConcurrentHashMap<Integer, KeyListener>	keyListeners	= new ConcurrentHashMap<Integer, KeyListener>();
	
	public static void keyActionPerformer() {
		if (!Keyboard.isCreated()) { return; }
		Iterator<Entry<Integer, KeyListener>> iterator = keyListeners.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, KeyListener> listenerEntry = iterator.next();
			if (Keyboard.isKeyDown(listenerEntry.getKey())) {
				listenerEntry.getValue().actionPerformed();
			}
		}
	}
	
	public static void addKeyListener(int key, KeyListener keyListener) {
		keyListeners.put(key, keyListener);
	}
	
	public static boolean containsListener(int key) {
		return keyListeners.containsKey(key);
	}
	
	public static void removeListener(int key) {
		keyListeners.remove(key);
	}
}
