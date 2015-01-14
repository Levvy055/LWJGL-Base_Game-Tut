package pl.grm.game.core.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import pl.grm.game.core.GameController;
import pl.grm.game.core.factory.GameFactory;
import pl.grm.game.core.loadstages.GameLoadStage;

public class DefaultListeners {
	private static HashMap<Integer, KeyListener>	defListeners;
	
	public static KeyListener getListener(int value) {
		if (defListeners == null) {
			init();
		}
		if (defListeners.containsKey(value)) { return defListeners.get(value); }
		return null;
	}
	
	private static void init() {
		defListeners = new HashMap<Integer, KeyListener>();
		defListeners.put(Keyboard.KEY_ESCAPE, ESC_Listener());
	}
	
	private static KeyListener ESC_Listener() {
		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (GameController.instance.getGameLoadStage()) {
					case INTRO :
						GameFactory.changeLoadStageTo(GameLoadStage.MAIN_MENU);
						break;
					case MAIN_MENU :
						GameController.stopGame();
						break;
					case GAME :
						break;
					case GAME_LOADING :
						break;
					case CLOSING :
						break;
					default :
						break;
				}
			}
		};
		return keyListener;
	}
	
}
