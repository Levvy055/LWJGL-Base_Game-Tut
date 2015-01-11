package pl.grm.game.core.entities.twodim;

import org.lwjgl.opengl.*;

import pl.grm.game.core.entities.*;

public class Circle implements Entity {
	private static final int	NUM_ANGLES	= 360;
	private int					ID;
	private String				name;
	private float				blue;
	private float				green;
	private float				red;
	private float				x;
	private float				y;
	private float				radius;
	
	public Circle(float x, float y, float radius) {
		this(x, y, radius, 0.5f, 0.5f, 1f);
	}
	
	public Circle(float x, float y, float radius, float red, float green, float blue) {
		this.name = "Circle";
		this.ID = 2;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void render() {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		if (radius < 0) {
			radius *= -1;
		}
		GL11.glScalef(radius, radius, 1);
		
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for (int i = 0; i <= NUM_ANGLES; i++) {
			double angle = Math.PI * 2 * i / 180;
			GL11.glVertex2f((float) Math.cos(angle), (float) Math.sin(angle));
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getID() {
		return ID;
	}
}
