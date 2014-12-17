package pl.grm.game.base;

public class GameEventIterator {
	private GameController	gameController;
	private long			time, tick;
	
	public GameEventIterator(GameController gameController) {
		this.gameController = gameController;
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
			System.out.println("Time = " + time + " | Tick = " + tick);
			try {
				this.wait(500l);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
