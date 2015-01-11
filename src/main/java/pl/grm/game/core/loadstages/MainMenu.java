package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;

public class MainMenu implements ILoadStage {
	private static ILoadStage	instance;
	private static boolean		running	= false;
	
	private MainMenu() {
		
	}
	
	public static void startStage() {
		instance = new MainMenu();
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
		glColor3f(1.0f, 0f, 0f);
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
