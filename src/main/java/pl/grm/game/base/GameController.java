package pl.grm.game.base;

import java.util.ArrayList;
import java.util.logging.Logger;

public class GameController {
	private Logger logger;
	private GameLoop gameLoop;
	private ArrayList<GameEvent> events;
	private ArrayList<Entity> entities;

	public GameController(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public void setGameLoop(GameLoop loop) {
		this.gameLoop = loop;
	}

	public ArrayList<GameEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<GameEvent> events) {
		this.events = events;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
