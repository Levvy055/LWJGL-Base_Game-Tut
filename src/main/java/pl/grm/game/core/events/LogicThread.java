package pl.grm.game.core.events;

import java.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.loadstages.*;
import pl.grm.game.core.timers.*;

public class LogicThread extends Thread {
	private TickTimer	timer	= GameController.instance.getTpsTimer();
	
	@Override
	public void run() {
		timer.initTime(GameParameters.TPS);
		boolean isClosing = false;
		while (GameController.instance.isRunning()) {
			GameLoadStage loadState = GameController.instance.getGameLoadStage();
			if (loadState != GameLoadStage.CLOSING) {
				GameLogger.info(loadState.toString());
			}
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
				case CLOSING :
					if (!isClosing) {
						isClosing = true;
						GameLogger.info(loadState.toString());
					}
					break;
				default :
					break;
			}
		}
	}
	
	private void introIterate() {
		Thread.currentThread().setName("Game Intro Logic");
		while (GameController.instance.getGameLoadStage() == GameLoadStage.INTRO) {
			baseLoop();
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
		if (timer.isFullCycle()) {
			long lastFps = GameController.instance.getFPSTimer().getLastFps();
			long lastTps = GameController.instance.getTpsTimer().getLastTps();
			if (lastFps != 0 & lastTps != 0) {
				System.out.println("FPS: " + lastFps + " | TPS: " + lastTps);
			} else {
				System.out.println("Please wait. Game is loading ... ");
			}
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
