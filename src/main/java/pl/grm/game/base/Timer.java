package pl.grm.game.base;

import org.lwjgl.*;

public class Timer {
	private GameController	gameController;
	/** time at last frame */
	long					lastFrame;
	/** frames per second */
	int						fps;
	/** last fps time */
	long					lastFPS;
	
	public Timer(GameController gameController) {
		this.gameController = gameController;
	}
	
	public void initTime() {
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
	}
	
	/**
	 * Calculate how many milliseconds have passed since last frame.
	 *
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta - 1000;
	}
	
	/**
	 * Get the accurate system time
	 *
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Get the current fps count
	 * 
	 * @return fps Counter
	 */
	public int getFPS() {
		return fps;
	}
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			System.out.println("FPS: " + getFPS() + " | Delta: " + getDelta());
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
}