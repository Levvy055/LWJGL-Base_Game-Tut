package pl.grm.game.core.timers;

public class TickTimer {
	private long	lastTick;
	private int		tpsT	= 0;
	private long	lastCallTime;
	private long	lastTPS	= 0;
	private int		tps;
	
	public void initTime(int tps) {
		this.tps = tps;
		getDelta();
		lastCallTime = getTime();
	}
	
	/**
	 * Calculate the TPS and set it
	 */
	public void updateTPS() {
		long currentTime = getTime();
		if (currentTime - lastCallTime > 1000) {
			System.out.println("TPS: " + getTPS() + " | " + getDelta() + " | " + currentTime);
			lastTPS = tpsT;
			tpsT = 0;
			lastCallTime += 1000;
		}
		tpsT++;
	}
	
	/**
	 * Calculate how many milliseconds have passed since last frame.
	 *
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastTick);
		lastTick = time;
		return delta;
	}
	
	/**
	 * Get the accurate system time
	 *
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return System.nanoTime() / 1000000;
	}
	
	/**
	 * Get the current tps count (fake)
	 * 
	 * @return tps Counter
	 */
	public int getTPS() {
		return tpsT;
	}
	
	/**
	 * Gets the real TPS of display renderer
	 * 
	 * @return frames per second
	 */
	public long getLastTps() {
		return lastTPS;
	}
	
	public void sync() {
		Sync.sync(tps);
	}
}