package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.util.*;

import pl.grm.game.core.events.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.core.misc.*;
import pl.grm.game.gui.*;

public class MainMenu implements ILoadStage {
	private static ILoadStage		instance;
	private static boolean			running	= false;
	private GameFrame				frame;
	private static Queue<Button>	buttonEventsQueue;
	
	private MainMenu() {
		frame = new GameFrame();
		buttonEventsQueue = new LinkedList<Button>();
	}
	
	public static void startStage() {
		instance = new MainMenu();
		running = true;
	}
	
	public static void stopStage() {
		running = false;
		instance = null;
	}
	
	public static void renderStage() {
		instance.render();
	}
	
	@Override
	public void render() {
		if (frame.getComponents().isEmpty()) { return; }
		frame.draw();
		Fonts.getFont(0).drawString(100, 50, "THE LWJGL JAVA GAME", org.newdawn.slick.Color.yellow);
		Fonts.getFont(1).drawString(100, 100, "Main Menu!", org.newdawn.slick.Color.green);
		glDisable(GL_TEXTURE_2D);
		
	}
	
	public static void update() {
		if (((MainMenu) instance).frame.getComponents().isEmpty()) {
			((MainMenu) instance).setupFrame();
		}
		((MainMenu) instance).frame.update();
	}
	
	private void setupFrame() {
		Panel btPanel = new Panel(400, 400, 100, 100, "ButtonsPanel");
		Button closeButton = new Button(5, 5, 50, 25, "Close");
		closeButton.setBackgroundColor((Color) ReadableColor.ORANGE);
		closeButton.addActionListener(new GameKeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		btPanel.add(closeButton);
		frame.add(btPanel);
		
	}
	
	public static boolean isRunning() {
		return running;
	}
	
	public static boolean next() {
		return !buttonEventsQueue.isEmpty();
	}
	
	public static String getEventButton() {
		return buttonEventsQueue.poll().getName();
	}
	
	public static boolean getEventKeyState() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static boolean isRepeatEvent() {
		// TODO Auto-generated method stub
		return false;
	}
}
