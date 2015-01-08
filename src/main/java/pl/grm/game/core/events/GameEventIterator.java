package pl.grm.game.core.events;

import pl.grm.game.core.*;
import pl.grm.game.core.entities.*;

public class GameEventIterator {
	private long	time, tick;
	private Timer	timer;
	
	public GameEventIterator() {
		this.timer = GameController.instance.getTimer();
	}
	
	/**
	 * Iterates over every event
	 */
	public synchronized void fullIterator() {
		time = 0l;
		while (GameController.instance.isRunning()) {
			time++;
			if (time % 10 == 0) {
				tick++;
			}
			// System.out.print("Time = " + time + " | Tick = " + tick);
			// System.out.print(" | FPS: " + timer.getFPS() + " | Delta: " +
			// timer.getDelta() + "\n");
			try {
				this.wait(500l);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (time == 5) {
				Rectangle rec = new Rectangle(0, 0);
				GameController.addEntity(rec);
			}
		}
	}
}
