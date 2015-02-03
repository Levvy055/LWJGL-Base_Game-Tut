package pl.grm.game.core.loadstages;

import static org.lwjgl.opengl.GL11.*;

public class GameLoading extends LoadGameStage {
	
	protected GameLoading() {
		
	}
	
	@Override
	public void render() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glPushMatrix();
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(0, 64);
		glVertex2f(64, 64);
		glVertex2f(64, 0);
		glEnd();
		glPopMatrix();
	}
	
	@Override
	protected void update() {
		if (!isInitialized()) {
			setInitialized(true);
		}
	}
}
