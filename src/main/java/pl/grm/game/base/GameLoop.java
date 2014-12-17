package pl.grm.game.base;

import static org.lwjgl.opengl.GL11.*;

import java.util.logging.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

public class GameLoop extends Thread {
	private GameController	gameController;
	private Logger			logger;
	
	public GameLoop(GameController gameController) {
		super(GameParameters.GAME_TITLE + " Main-Game-Loop");
		this.gameController = gameController;
		this.logger = gameController.getLogger();
	}
	
	@Override
	public void run() {
		initLoop();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		while (!Display.isCloseRequested()) {
			glPushMatrix();
			glBegin(GL_QUADS);
			glVertex2f(0, 0);
			glVertex2f(0, 64);
			glVertex2f(64, 64);
			glVertex2f(64, 0);
			glEnd();
			glPopMatrix();
			
			Display.update();
		}
		closeGame();
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
		try {
			Display.create();
		}
		catch (LWJGLException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
	}
}
