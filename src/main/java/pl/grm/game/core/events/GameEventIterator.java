package pl.grm.game.core.events;

import pl.grm.game.core.*;

public class GameEventIterator {
	private GameController	gameController;
	private long			time, tick;
	private Timer			timer;
	
	public GameEventIterator(GameController gameController) {
		this.gameController = gameController;
		this.timer = gameController.getTimer();
	}
	
	/**
	 * Iterates over every event
	 */
	public synchronized void fullIterator() {
		time = 0l;
		while (gameController.isRunning()) {
			time++;
			if (time % 10 == 0) {
				tick++;
			}
			System.out.print("Time = " + time + " | Tick = " + tick);
			System.out.print(" | FPS: " + timer.getFPS() + " | Delta: " + timer.getDelta() + "\n");
			try {
				this.wait(500l);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
