package pl.grm.game;

import static pl.grm.game.core.factory.GameFactory.*;
import pl.grm.game.core.*;

public class GameMain {
	private static GameController	gameController;
	
	public static void main(String[] args) {
		Thread.currentThread().setName("Main");
		GameLogger.setLogger(setupLogger());
		System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir")
				+ "/libs/native/windows");
		gameController = createGameController();
		initDisplay();
		startGame(gameController);
	}
}
