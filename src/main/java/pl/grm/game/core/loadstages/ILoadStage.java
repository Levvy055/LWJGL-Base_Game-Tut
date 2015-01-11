package pl.grm.game.core.loadstages;

public interface ILoadStage {
	/**
	 * Renders a screen of stage
	 */
	public void render();
	
	/**
	 * check for keyboard/mouse buttons
	 */
	public void checkInputs();
}
