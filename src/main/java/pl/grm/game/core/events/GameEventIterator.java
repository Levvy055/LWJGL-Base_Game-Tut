package pl.grm.game.core.events;

import java.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.timers.*;

public class GameEventIterator {
	private TickTimer	timer	= new TickTimer();
	
	/**
	 * Iterates over every event
	 */
	public synchronized void fullIterator() {
		Thread.currentThread().setName("Game Logic");
		timer.initTime(GameParameters.TPS);
		while (GameController.instance.isRunning()) {
			timer.updateTPS();
			
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
