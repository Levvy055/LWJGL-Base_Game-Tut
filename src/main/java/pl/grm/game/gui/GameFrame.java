package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.util.*;

public class GameFrame implements Container {
	private HashMap<String, Component>	components;
	private Color						bgColor;
	
	public GameFrame() {
		this.components = new HashMap<String, Component>();
		bgColor = (Color) ReadableColor.BLACK;
	}
	
	@Override
	public void add(Component component) {
		if (component == null) { return; }
		component.setParent(this);
		components.put(component.getName(), component);
	}
	
	public void draw() {
		drawBackground();
		Iterator<String> iterator = components.keySet().iterator();
		while (iterator.hasNext()) {
			Component component = components.get(iterator.next());
			component.draw();
		}
	}
	
	private void drawBackground() {
		glPushMatrix();
		glColor3f(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
		glRecti(0, 0, 800, 600);
		glPopMatrix();
	}
	
	public void setBackgroundColor(Color color) {
		this.bgColor = color;
	}
	
	public void update() {
		Iterator<String> iterator = components.keySet().iterator();
		while (iterator.hasNext()) {
			Component component = components.get(iterator.next());
			component.update();
		}
	}
	
	public HashMap<String, Component> getComponents() {
		return components;
	}
}
