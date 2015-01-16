package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.*;

import org.lwjgl.opengl.*;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;

import pl.grm.game.core.factory.*;
import pl.grm.game.core.timers.*;

public class Intro implements ILoadStage {
	private static ILoadStage		instance;
	private static boolean			running		= false;
	private static List<Texture>	textures	= new ArrayList<Texture>();
	private TickTimer				timer;
	private boolean					afterFirstRender;
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
		if (!afterFirstRender) {
			try {
				glEnable(GL_TEXTURE_2D);
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				texture = TextureLoader.getTexture("PNG",
						ResourceLoader.getResourceAsStream("src/main/resources/defaultImage.png"));
				loadImages();
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glOrtho(0, 800, 600, 0, 1, -1);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				afterFirstRender = true;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		texture.bind();
		int tWidth = texture.getTextureWidth();
		int tHeight = texture.getTextureHeight();
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 0);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(tWidth, 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(tWidth, tHeight);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, tHeight);
		glEnd();
		
		// glMatrixMode(GL_PROJECTION);
		// glLoadIdentity();
		// glPushMatrix();
		// glBegin(GL_QUADS);
		// glVertex2f(0, 0);
		// glVertex2f(0, 64);
		// glVertex2f(64, 64);
		// glVertex2f(64, 0);
		// glEnd();
		// glPopMatrix();
		
	}
	
	public void loadImages() throws IOException {
		for (int i = 40; i < 192; i++) {
			String nmb = i < 10 ? "00" + i : i < 100 ? "0" + i : i + "";
			textures.add(TextureLoader.getTexture(
					"PNG",
					ResourceLoader.getResourceAsStream("src/main/resources/animations/intro/Intro."
							+ nmb)));
		}
	}
	
	private void startAnimationThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("Intro animation");
				int textureID = 0;
				while (isRunning()) {
					timer.initTime(60);
					if (afterFirstRender) {
						texture = textures.get(textureID);
						textureID++;
						if (textureID == 192 - 40) {
							GameFactory.changeLoadStageTo(GameLoadStage.MAIN_MENU);
							textureID = 0;
						}
					} else {
						if (textures.size() != 0) {
							texture = textures.get(0);
						}
					}
					timer.sync();
				}
			}
		}).start();
	}
	
	public static boolean isRunning() {
		return running;
	}
}
