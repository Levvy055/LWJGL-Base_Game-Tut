package pl.grm.game.core.entities;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle implements Entity {
	private float	blue;
	private float	green;
	private float	red;
	private float	x;
	private float	y;
	private float	width;
	private float	height;
	private String	name;
	
	public Rectangle(float x, float y) {
		this(x, y, 64, 64);
	}
	
	public Rectangle(float x, float y, float width, float height) {
		this(x, y, width, height, 200, 10, 10);
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
	}
	
	@Override
	public void render() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
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
	
}
