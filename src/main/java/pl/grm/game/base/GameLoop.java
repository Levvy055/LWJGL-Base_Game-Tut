package pl.grm.game.base;

public class GameLoop extends Thread {
	private GameController gameController;

	public GameLoop(GameController gameController) {
		super(GameParameters.GAME_TITLE);
		this.gameController = gameController;
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
