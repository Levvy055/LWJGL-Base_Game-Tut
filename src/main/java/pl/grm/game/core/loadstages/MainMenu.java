package pl.grm.game.core.loadstages;

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
	}
	
	private void setupFrame() {
		Panel panel = new Panel(100, 0, "Panela");
		// Button button = new Button(10, 0, "Close");
		// button.setSize(10, 10);
		// button.setBackgroundColor((Color) ReadableColor.ORANGE);
		// panel.add(button);
		frame.add(panel);
		
	}
	
	public static void update() {
		((MainMenu) instance).frame.update();
	}
	
	public static boolean isRunning() {
		return running;
	}
}
