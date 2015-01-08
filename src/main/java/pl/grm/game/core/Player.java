package pl.grm.game.core;

import pl.grm.game.core.entities.*;

public class Player implements Entity {
	private String	entityName;
	
	public Player() {
		entityName = "Player";
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
	
}
