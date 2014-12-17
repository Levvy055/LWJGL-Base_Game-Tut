package pl.grm.game.base;

import java.util.*;

public class Game {
	private Queue<GameEvent>	events;
	private ArrayList<Entity>	entities;
	private Queue<Entity>		renderQueue;
	
	public Game() {
		
	}
	
	public Queue<GameEvent> getEvents() {
		return this.events;
	}
	
	public void setEvents(Queue<GameEvent> events) {
		this.events = events;
	}
	
	public ArrayList<Entity> getEntities() {
		return this.entities;
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public Queue<Entity> getRenderQueue() {
		return this.renderQueue;
	}
	
	public void setRenderQueue(Queue<Entity> renderQueue) {
		this.renderQueue = renderQueue;
	}
}
