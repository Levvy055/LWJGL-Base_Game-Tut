package pl.grm.game.core.inputs;

import java.util.*;

import org.lwjgl.input.*;

import pl.grm.game.core.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.factory.*;
import pl.grm.game.core.loadstages.*;

public class DefaultListeners {
	private static HashMap<Integer, GameKeyListener>	defListeners;
	
	private static void init() {
		defListeners = new HashMap<Integer, GameKeyListener>();
		defListeners.put(Keyboard.KEY_ESCAPE, ESC_Listener());
	}
	
	public static boolean contains(int value) {
		if (defListeners == null) {
			init();
		}
		return defListeners.containsKey(value);
	}
	
	public static GameKeyListener getListener(int value) {
		if (defListeners == null) {
			init();
		}
		return defListeners.get(value);
	}
	
	private static GameKeyListener ESC_Listener() {
		GameKeyListener keyListener = new GameKeyListener() {
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
