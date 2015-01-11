package pl.grm.game.core.inputs;

import pl.grm.game.core.config.*;

public class KeyListenerData {
	private static int	TPS				= GameParameters.TPS;
	private double		deadzone		= 0.5 * TPS;
	private long		lastTimeUsed	= 0;
	private boolean		enabled			= true;
	
	public boolean canActionBePerformed() {
		long currentTime = System.currentTimeMillis();
		if (isEnabled() && currentTime - lastTimeUsed > deadzone / TPS * 1000) {
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
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}