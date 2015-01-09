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
	@SuppressWarnings("unchecked")
	public synchronized void fullIterator() {
		timer.initTime(GameParameters.TPS);
		while (GameController.instance.isRunning()) {
			timer.updateTPS();
			
			KeyManager.keyActionPerformer();
			
			for (Entity entity : (ArrayList<Entity>) GameController.instance.getEntities().clone()) {
				entity.update();
			}
			
			timer.sync();
		}
	}
}
