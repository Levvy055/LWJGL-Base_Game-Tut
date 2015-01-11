package pl.grm.game.core.events;

import java.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.loadstages.*;
import pl.grm.game.core.timers.*;

public class GameEventIterator {
	private TickTimer	timer	= new TickTimer();
	
	public void mainIterator() {
		timer.initTime(GameParameters.TPS);
		while (GameController.instance.isRunning()) {
			GameLogger.info(GameController.instance.getGameLoadStage().toString());
			switch (GameController.instance.getGameLoadStage()) {
				case INTRO :
					introIterate();
					break;
				case MAIN_MENU :
					mainMenuIterate();
					break;
				case GAME_LOADING :
					gameLoadingIterate();
					break;
				case GAME :
					gameIterator();
					break;
				default :
					break;
			}
		}
	}
	
	private void introIterate() {
		Thread.currentThread().setName("Game Intro Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.INTRO) {
			KeyManager.keyActionPerformer();
			timer.sync();
		}
	}
	
	private void mainMenuIterate() {
		Thread.currentThread().setName("Game Main Menu Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.MAIN_MENU) {
			KeyManager.keyActionPerformer();
			timer.sync();
		}
	}
	
	private void gameLoadingIterate() {
		Thread.currentThread().setName("Game Loading Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.GAME_LOADING) {
			timer.sync();
		}
	}
	
	/**
	 * Iterates over every event
	 */
	private void gameIterator() {
		Thread.currentThread().setName("Game Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.GAME) {
			KeyManager.keyActionPerformer();
			
			updateEntities();
			System.out.println(GameController.instance.getEntities().size());
			timer.sync();
		}
	}
	
	private void updateEntities() {
		Set<Integer> keySet = GameController.instance.getEntities().keySet();
		Iterator<Integer> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			Collection<Entity> entityCollection = GameController.instance.getEntities().get(key);
			for (Entity entity : entityCollection) {
				entity.update();
			}
		}
	}
}
