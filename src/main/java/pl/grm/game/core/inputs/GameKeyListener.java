package pl.grm.game.core.inputs;

import pl.grm.game.core.config.*;
import pl.grm.game.core.events.*;

public abstract class GameKeyListener implements GameListener {
	private static int	TPS				= GameParameters.TPS;
	private double		deadzone		= 0.5 * TPS;
	private long		lastTimeUsed	= 0;
	
	public abstract void keyPressed(KeyEvent e);
	
	public abstract void keyTyped(KeyEvent e);
	
	public abstract void keyReleased(KeyEvent e);
	
	@Override
	public boolean canActionBePerformed() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastTimeUsed > deadzone / TPS * 1000) {
			lastTimeUsed = currentTime;
			return true;
		}
		return false;
	}
	
	public double getDeadzone() {
		return this.deadzone;
	}
	
	public void setDeadzone(double deadzone) {
		this.deadzone = deadzone;
	}
	
	public long getLastTimeUsed() {
		return this.lastTimeUsed;
	}
	
	public void setLastTimeUsed(long lastTimeUsed) {
		this.lastTimeUsed = lastTimeUsed;
	}
}
