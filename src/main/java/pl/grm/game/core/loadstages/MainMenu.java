package pl.grm.game.core.loadstages;

import java.util.*;
import java.util.concurrent.*;

import org.lwjgl.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.gui.*;
import pl.grm.game.gui.component.*;

public class MainMenu implements ILoadStage {
	private static ILoadStage	instance;
	private static boolean		running		= false;
	private GameFrame			frame;
	public static Queue<Button>	buttonQueue;
	private static Button		currentTempButttoon;
	private boolean				initialized	= false;
	
	private MainMenu() {
		frame = new GameFrame();
		buttonQueue = new ConcurrentLinkedQueue<Button>();
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
		if (!isInitialized()) { return; }
		frame.draw();
	}
	
	public static void update() {
		MainMenu instanceE = (MainMenu) instance;
		if (!instanceE.isInitialized()) {
			instanceE.setupFrame();
			instanceE.setInitialized(true);
		}
		instanceE.frame.update();
	}
	
	private void setupFrame() {
		Panel titlePanel = new Panel(50, 50, 100, 200, "TitlePanel");
		Panel buttonsPanel = new Panel(400, 400, 100, 200, "ButtonsPanel");
		frame.add(buttonsPanel);
		frame.add(titlePanel);
		
		Button closeButton = new Button(5, 5, 50, 25, "CloseButton");
		closeButton.setBackgroundColor((Color) ReadableColor.ORANGE);
		closeButton.addActionListener(new GameKeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				GameController.stopGame();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		buttonsPanel.add(closeButton);
		
		Label titleLabel1 = new Label(0, 0, 100, 10, "TitleLabel");
		titleLabel1.setText("THE LWJGL JAVA GAME");
		titleLabel1.setFontColor(org.newdawn.slick.Color.green);
		titlePanel.add(titleLabel1);
		
		Label titleLabel2 = new Label(0, 100, 100, 10, "Title2Label");
		titleLabel2.setText("Main Menu!");
		titleLabel2.setFontColor(org.newdawn.slick.Color.green);
		titlePanel.add(titleLabel2);
	}
	
	public static boolean isRunning() {
		return running;
	}
	
	public static boolean next() {
		return !buttonQueue.isEmpty();
	}
	
	public static String getEventButton() {
		currentTempButttoon = buttonQueue.poll();
		return currentTempButttoon.getName();
	}
	
	public static boolean getEventKeyState() {
		return currentTempButttoon.isPressed();
	}
	
	public static boolean isRepeatEvent() {
		return currentTempButttoon.wasPressed();
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
