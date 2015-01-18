package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.util.*;

public class GameFrame implements Container {
	private ArrayList<Component>	components;
	private Color					bgColor;
	
	public GameFrame() {
		this.components = new ArrayList<Component>();
		bgColor = (Color) ReadableColor.BLACK;
	}
	
	@Override
	public void add(Component component) {
		if (component == null) { return; }
		components.add(component);
	}
	
	public void draw() {
		glColor3f(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
		int x = 0, y = 0, w = 800, h = 600;
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x, y + h);
		glVertex2f(x + w, y + h);
		glVertex2f(x + w, y);
		glEnd();
		for (Component component : components) {
			component.draw();
		}
	}
	
	public void setBackgroundColor(Color color) {
		this.bgColor = color;
	}
}
