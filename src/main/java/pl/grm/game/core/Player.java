package pl.grm.game.core;

import pl.grm.game.core.entities.*;

public class Player implements Entity {
	private String	entityName;
	private int		ID;
	
	public Player() {
		this.entityName = "Player";
		this.ID = 0;
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		return entityName;
	}
	
	@Override
	public int getID() {
		return ID;
	}
	
}
