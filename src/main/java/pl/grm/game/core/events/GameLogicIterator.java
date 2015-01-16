package pl.grm.game.core.events;

import java.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.loadstages.*;
import pl.grm.game.core.timers.*;

public class GameLogicIterator extends Thread {
	private TickTimer	timer	= GameController.instance.getTpsTimer();
	
	@Override
	public void run() {
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
		System.out.println("Thread end: " + currentThread().getName());
	}
	
	private void introIterate() {
		Thread.currentThread().setName("Game Intro Logic");
		System.out.println("Thread started: " + currentThread().getName());
		while (GameController.instance.getGameLoadStage() == GameLoadStage.INTRO) {
			baseLoop();
			timer.sync();
		}
	}
	
	private void mainMenuIterate() {
		Thread.currentThread().setName("Game Main Menu Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.MAIN_MENU) {
			baseLoop();
		}
	}
	
	private void gameLoadingIterate() {
		Thread.currentThread().setName("Game Loading Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.GAME_LOADING) {
			baseLoop();
		}
	}
	
	/**
	 * Iterates over every event
	 */
	private void gameIterator() {
		Thread.currentThread().setName("Game Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.GAME) {
			
			updateEntities();
			System.out.println(GameController.instance.getEntities().size());
			baseLoop();
		}
	}
	
	private void baseLoop() {
		if (timer.getLastTps() == timer.getTPS()) {
			System.out.println("FPS: " + GameController.instance.getFPSTimer().getLastFps()
					+ " | TPS: " + GameController.instance.getTpsTimer().getLastTps());
		}
		timer.sync();
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
