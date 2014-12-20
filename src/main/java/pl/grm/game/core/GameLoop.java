package pl.grm.game.core;

import java.util.logging.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import pl.grm.game.core.config.*;
import pl.grm.game.core.pregamestages.*;

public class GameLoop extends Thread {
	private GameController	gameController;
	private Logger			logger;
	private Timer			timer;
	
	public GameLoop(GameController gameController) {
		super(GameParameters.GAME_TITLE + " Main-Game-Loop");
		this.gameController = gameController;
		this.logger = gameController.getLogger();
		this.timer = gameController.getTimer();
	}
	
	@Override
	public void run() {
		initLoop();
		
		while (!Display.isCloseRequested()) {
			switch (gameController.getGameRenderStage()) {
				case MENU :
					renderMaenu();
					break;
				default :
					break;
			}
			timer.updateFPS();
			Display.update();
		}
		closeGame();
	}
	
	private void renderMaenu() {
		Menu menu = (Menu) gameController.getGamePreStage();
		menu.render();
	}
	
	/**
	 * Method called after breaking loop
	 */
	private void closeGame() {
		gameController.stopGame();
		Display.destroy();
	}
	
	/**
	 * Called before loop
	 */
	private void initLoop() {
		timer.initTime();
		try {
			Display.create();
		}
		catch (LWJGLException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
	}
}
