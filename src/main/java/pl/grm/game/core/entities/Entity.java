package pl.grm.game.core.entities;

public interface Entity {
	/**
	 * Update of entity
	 */
	public void update();
	
	/**
	 * Render functions of drawing entity
	 */
	public void render();
	
	/**
	 * Returns Name of Entity
	 * 
	 * @return name of entity
	 */
	public String getName();
}
