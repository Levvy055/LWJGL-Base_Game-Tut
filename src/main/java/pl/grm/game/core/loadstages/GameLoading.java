package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;

public class GameLoading implements ILoadStage {
	private static ILoadStage	instance;
	private static boolean		running	= false;
	
	public static void startStage() {
		instance = new GameLoading();
		running = true;
	}
	
	public static void stopStage() {
		instance = null;
		running = false;
	}
	
	public static void renderStage() {
		instance.render();
	}
	
	@Override
	public void render() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glPushMatrix();
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(0, 64);
		glVertex2f(64, 64);
		glVertex2f(64, 0);
		glEnd();
		glPopMatrix();
	}
	
	public static boolean isRunning() {
		return running;
	}
}