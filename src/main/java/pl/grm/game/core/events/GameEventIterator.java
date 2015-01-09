package pl.grm.game.core.events;

import pl.grm.game.core.*;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.timers.*;

public class GameEventIterator {
	private long		gameTickTime;
	private TickTimer	timer	= new TickTimer();
	
	/**
	 * Iterates over every event
	 */
	public synchronized void fullIterator() {
		timer.initTime(GameParameters.TPS);
		while (GameController.instance.isRunning()) {
			if (gameTickTime == 5) {
				Rectangle rec = new Rectangle(0, 0);
				GameController.addEntity(rec);
			}
			if (gameTickTime == 10) {
				Rectangle rec = new Rectangle(15, 20, 120, 60, 0.1f, 0.3f, 0.1f);
				GameController.addEntity(rec);
			}
			timer.updateTPS();
			timer.sync();
		}
	}
}
