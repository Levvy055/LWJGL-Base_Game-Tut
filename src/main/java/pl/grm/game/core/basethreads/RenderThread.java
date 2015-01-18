package pl.grm.game.core.basethreads;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.*;
import java.util.*;
import java.util.logging.*;

import org.lwjgl.opengl.*;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.config.*;
import pl.grm.game.core.entities.*;
import pl.grm.game.core.loadstages.*;
import pl.grm.game.core.misc.*;
import pl.grm.game.core.misc.timers.*;

import com.google.common.collect.*;

public class RenderThread extends Thread {
	private FPSTimer					timer;
	// private Queue<Entity> renderQueue;
	private Multimap<Integer, Entity>	entities;
	private TrueTypeFont				font;
	private TrueTypeFont				font2;
	
	public RenderThread() {
		super(GameParameters.GAME_TITLE + " Game-Render-Loop");
		this.timer = GameController.instance.getFPSTimer();
	}
	
	@Override
	public void run() {
		initRenderer();
		while (GameController.instance.isRunning()) {
			if (Display.isCloseRequested()) {
				GameController.stopGame();
			}
			loop();
		}
		Display.destroy();
	}
	
	/**
	 * Called before loop
	 */
	private void initRenderer() {
		this.timer.initTime();
		this.entities = GameController.instance.getGame().getEntities();
		try {
			Display.create();
			Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
			font = new TrueTypeFont(awtFont, false);
			InputStream inputStream = ResourceLoader.getResourceAsStream("arial.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(24f);
			font2 = new TrueTypeFont(awtFont2, false);
		}
		catch (Exception e) {
			GameLogger.log(Level.SEVERE, e.toString(), e);
		}
		glShadeModel(GL_SMOOTH);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 600, 0, 1, -1);
		glViewport(0, 0, 800, 600);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void loop() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glEnable(GL_TEXTURE_2D);
		font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
		font2.drawString(100, 100, "NICE LOOKING FONTS!", Color.green);
		glDisable(GL_TEXTURE_2D);
		switch (GameController.instance.getGameLoadStage()) {
			case INTRO :
				Intro.renderStage();
				break;
			case MAIN_MENU :
				MainMenu.renderStage();
				break;
			case GAME_LOADING :
				GameLoading.renderStage();
				break;
			case GAME :
				renderEntities();
				break;
			default :
				break;
		}
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
}
