package pl.grm.game.core;

import java.util.*;

import pl.grm.game.core.entities.*;
import pl.grm.game.core.events.*;

import com.google.common.collect.*;

public class Game {
	/** Queue of events */
	private Queue<GameEvent>			events;
	/** List of existing entities */
	private Multimap<Integer, Entity>	entities;
	/** Queue of rendering things on screen */
	private Queue<Entity>				renderQueue;
	
	public Game() {
		
	}
	
	public void addEntity(Entity entity) {
		entities.put(entity.getID(), entity);
	}
	
	@SuppressWarnings("unchecked")
	public void destroyAllEntities(int id) {
		entities.removeAll(id);
	}
	
	public Queue<GameEvent> getEvents() {
		return this.events;
	}
	
	public void setEvents(Queue<GameEvent> events) {
		this.events = events;
	}
	
	public Multimap<Integer, Entity> getEntities() {
		return this.entities;
	}
	
	public void setEntities(Multimap<Integer, Entity> entities) {
		this.entities = entities;
	}
	
	public Queue<Entity> getRenderQueue() {
		return this.renderQueue;
	}
	
	public void setRenderQueue(Queue<Entity> renderQueue) {
		this.renderQueue = renderQueue;
	}
}
