package pl.grm.game.core.loadstages;

import java.util.*;
import java.util.concurrent.*;

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
		FrameSetupData.setupFrame(frame);
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
		return currentTempButttoon.isRepeatEvent();
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
