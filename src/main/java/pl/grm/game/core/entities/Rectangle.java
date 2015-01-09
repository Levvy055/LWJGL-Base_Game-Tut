package pl.grm.game.core.entities;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle implements Entity {
	private static int	ID;
	private float		blue;
	private float		green;
	private float		red;
	private float		x;
	private float		y;
	private float		width;
	private float		height;
	private String		name;
	
	public Rectangle(float x, float y) {
		this(x, y, 64, 64);
	}
	
	public Rectangle(float x, float y, float width, float height) {
		this(x, y, width, height, 0.5f, 0.5f, 1f);
	}
	
	public Rectangle(float x, float y, float width, float height, float red, float green, float blue) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.name = "Rectangle";
		ID = 1;
	}
	
	@Override
	public void render() {
		glPushMatrix();
		glColor3f(red, green, blue);
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x, y + height);
		glVertex2f(x + width, y + height);
		glVertex2f(x + width, y);
		glEnd();
		glPopMatrix();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public static int getID() {
		return ID;
	}
}
