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
		component.setParent(this);
		components.add(component);
	}
	
	public void draw() {
		// drawBackground();
		for (Component component : components) {
			component.draw();
		}
	}
	
	private void drawBackground() {
		glColor3f(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
		int x = 0, y = 0, w = 800, h = 600;
		glRecti(x, y, w, h);
	}
	
	public void setBackgroundColor(Color color) {
		this.bgColor = color;
	}
	
	public void update() {
		for (Component component : components) {
			component.update();
		}
	}
}
