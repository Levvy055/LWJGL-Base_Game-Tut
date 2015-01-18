package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.factory.*;
import pl.grm.game.core.timers.*;

public class Intro implements ILoadStage {
	private static ILoadStage		instance;
	private static boolean			running		= false;
	private static List<Texture>	textures	= new ArrayList<Texture>();
	private TickTimer				timer;
	private boolean					isAfterFirstRender;
	private Texture					texture;
	
	private Intro() {
		timer = new TickTimer();
		startAnimationThread();
	}
	
	public static void startStage() {
		running = true;
		instance = new Intro();
	}
	
	public static void stopStage() {
		instance = null;
		running = false;
	}
	
	public static void renderStage() {
		instance.render();
	}
	
	@Override
	public void render() {
		Color.white.bind();
		try {
			if (!isAfterFirstRender) {
				glEnable(GL_TEXTURE_2D);
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				texture = TextureLoader.getTexture("PNG",
						ResourceLoader.getResourceAsStream("defaultImage.png"));
				loadImages();
				isAfterFirstRender = true;
			}
			texture.bind();
		}
		catch (Exception e) {
			GameLogger.logException(e);
			GameController.stopGame();
		}
		int tWidth = texture.getTextureWidth();
		int tHeight = texture.getTextureHeight();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(tWidth, 0);
		glTexCoord2f(1, 1);
		glVertex2f(tWidth, tHeight);
		glTexCoord2f(0, 1);
		glVertex2f(0, tHeight);
		glEnd();
	}
	
	public void loadImages() throws IOException {
		for (int i = 180; i <= 191; i++) {
			String nmb = i < 10 ? "00" + i : i < 100 ? "0" + i : i + "";
			textures.add(TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("animations/intro/Intro." + nmb)));
		}
	}
	
	private void startAnimationThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("Intro animation");
				int textureID = 0;
				int tps = 5;
				timer.initTime(tps);
				int maxAnimTickTime = tps * 5;
				int currentTick = 0;
				boolean asc = true;
				while (isRunning()) {
					int maxID = textures.size() - 1;
					if (isAfterFirstRender && maxID != -1) {
						if (asc) {
							textureID++;
						} else {
							textureID--;
						}
						texture = textures.get(textureID);
						if (textureID == 0) {
							asc = true;
						} else if (textureID == maxID) {
							asc = false;
						}
					}
					timer.sync();
					currentTick++;
					if (currentTick >= maxAnimTickTime) {
						GameFactory.changeLoadStageTo(GameLoadStage.MAIN_MENU);
					}
				}
			}
		}).start();
	}
	
	public static boolean isRunning() {
		return running;
	}
}
