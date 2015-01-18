package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;
import pl.grm.game.gui.*;

public class MainMenu implements ILoadStage {
	private static ILoadStage	instance;
	private static boolean		running	= false;
	private GameFrame			frame;
	
	private MainMenu() {
		frame = new GameFrame();
		setupFrame();
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
		frame.draw();
		
		// int x = 0, y = 100, w = 64, h = 64;
		// glDisable(GL_TEXTURE_2D);
		// glColor3f(1.0f, 0f, 0f);
		// glPushMatrix();
		// glBegin(GL_QUADS);
		// glVertex2f(x, y);
		// glVertex2f(x, y + h);
		// glVertex2f(x + w, y + h);
		// glVertex2f(x + w, y);
		// glEnd();
		// glPopMatrix();
	}
	
	private void setupFrame() {
		frame.add(new Component() {
			int x = 0, y = 100, w = 64, h = 64;
			@Override
			public void paint() {
				glBegin(GL_QUADS);
				glVertex2f(x, y);
				glVertex2f(x, y + h);
				glVertex2f(x + w, y + h);
				glVertex2f(x + w, y);
				glEnd();
			}
		});
	}
	
	public static boolean isRunning() {
		return running;
	}
}
