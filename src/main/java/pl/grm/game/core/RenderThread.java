package pl.grm.game.core;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;
import java.util.logging.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.pregamestages.*;
import pl.grm.game.core.timers.*;

import com.google.common.collect.*;

public class RenderThread extends Thread {
	private FPSTimer					timer;
	private Queue<Entity>				renderQueue;
	private Multimap<Integer, Entity>	entities;
	
	public RenderThread() {
		super(GameParameters.GAME_TITLE + " Game-Render-Loop");
		this.timer = GameController.instance.getFPSTimer();
	}
	
	@Override
	public void run() {
		initLoop();
		while (!Display.isCloseRequested() && GameController.instance.isRunning()) {
			loop();
		}
		closeGame();
	}
	
	private void loop() {
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		switch (GameController.instance.getGameRenderStage()) {
			case MENU :
				renderMenu();
				break;
			default :
				break;
		}
		renderEntities();
		
		Display.update();
		timer.updateFPS();
		Display.sync(GameParameters.FPS);
	}
	
	private void renderEntities() {
		Set<Integer> keySet = entities.keySet();
		Iterator<Integer> keySetIterator = keySet.iterator();
		while (keySetIterator.hasNext()) {
			Integer key = keySetIterator.next();
			Collection<Entity> entityCollection = entities.get(key);
			Iterator<Entity> entityCollectionIterator = entityCollection.iterator();
			synchronized (entityCollectionIterator) {
				while (entityCollectionIterator.hasNext()) {
					Entity entity = entityCollectionIterator.next();
					entity.render();
				}
			}
		}
	}
	
	private void renderMenu() {
		Menu menu = (Menu) GameController.instance.getGamePreStage();
		// menu.render();
	}
	
	/**
	 * Method called after breaking loop
	 */
	private void closeGame() {
		GameController.instance.stopGame();
		Display.destroy();
	}
	
	/**
	 * Called before loop
	 */
	private void initLoop() {
		this.timer.initTime();
		this.renderQueue = GameController.instance.getGame().getRenderQueue();
		this.entities = GameController.instance.getGame().getEntities();
		try {
			Display.create();
		}
		catch (LWJGLException e) {
			GameLogger.log(Level.SEVERE, e.toString(), e);
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL11.GL_MODELVIEW);
	}
}
