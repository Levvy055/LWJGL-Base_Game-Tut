package pl.grm.game.core.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;

public class Rectangle implements Entity {
	private float	blue;
	private float	green;
	private float	red;
	private float	x;
	private float	y;
	private float	width;
	private float	height;
	
	public Rectangle(float x, float y) {
		this(x, y, 50, 50);
	}
	
	public Rectangle(float x, float y, float width, float height) {
		this(x, y, width, height, 200, 10, 10);
	}
	
	public Rectangle(float x, float y, float width, float height, float red, float green, float blue) {
		this.x = x;
		this.y = Display.getHeight() - y;
		this.width = width;
		this.height = height;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	@Override
	public void render() {
		glColor3f(red, green, blue);
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
		glEnd();
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
