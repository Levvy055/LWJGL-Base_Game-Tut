package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.*;

import pl.grm.game.core.misc.*;
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
		Fonts.getFont(0).drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY",
				org.newdawn.slick.Color.yellow);
		Fonts.getFont(1).drawString(100, 100, "NICE LOOKING FONTS!", org.newdawn.slick.Color.green);
		glDisable(GL_TEXTURE_2D);
	}
	
	private void setupFrame() {
		Panel panel = new Panel(200, 200, 100, 100, "Panela");
		Button button = new Button(10, 10, 50, 64, "Close");
		button.setBackgroundColor((Color) ReadableColor.ORANGE);
		panel.add(button);
		frame.add(panel);
		
	}
	
	public static void update() {
		((MainMenu) instance).frame.update();
	}
	
	public static boolean isRunning() {
		return running;
	}
}
