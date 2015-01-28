package pl.grm.game.core.loadstages;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import pl.grm.game.core.events.KeyEvent;
import pl.grm.game.core.inputs.GameKeyListener;
import pl.grm.game.gui.GameFrame;
import pl.grm.game.gui.component.*;

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
		Panel titlePanel = new Panel(100, 50, 100, 20, "Title Panel");
		Label titleLabel = new Label(0, 0, 100, 20, "Title Label");
		titleLabel.setText("THE LWJGL JAVA GAME");
		titleLabel.setFontColor(org.newdawn.slick.Color.green);
		titlePanel.add(titleLabel);
		Label titleLabel2 = new Label(0, 100, 100, 20, "Title 2 Label");
		titleLabel2.setText("Main Menu!");
		titleLabel2.setFontColor(org.newdawn.slick.Color.green);
		titlePanel.add(titleLabel2);
		
		frame.add(titlePanel);
		
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
