package pl.grm.game.core.loadstages;

import java.util.*;
import java.util.concurrent.*;

import pl.grm.game.gui.*;
import pl.grm.game.gui.component.*;

public class MainMenu extends LoadGameStage {
	private GameFrame			frame;
	public static Queue<Button>	buttonQueue;
	private static Button		currentTempButttoon;
	
	MainMenu() {
		frame = new GameFrame();
		buttonQueue = new ConcurrentLinkedQueue<Button>();
	}
	
	@Override
	protected void render() {
		if (!isInitialized()) { return; }
		frame.draw();
	}
	
	@Override
	protected void update() {
		if (!isInitialized()) {
			FrameSetupData.setupFrame(frame);
			setInitialized(true);
		}
		this.frame.update();
	}
	
	public static boolean next() {
		return buttonQueue != null ? !buttonQueue.isEmpty() : false;
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
}
