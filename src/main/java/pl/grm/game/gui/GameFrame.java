package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.util.*;

import pl.grm.game.gui.component.*;

public class GameFrame extends Component implements Container {
	private HashMap<String, Component>	components;
	private Color						bgColor;
	
	public GameFrame() {
		this.components = new HashMap<String, Component>();
		bgColor = (Color) ReadableColor.BLACK;
	}
	
	public GameFrame(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
	}
	
	@Override
	public void add(Component component) {
		if (component == null) { return; }
		component.setParent(this);
		components.put(component.getName(), component);
	}
	
	@Override
	public void draw() {
		paint();
		Iterator<String> iterator = components.keySet().iterator();
		while (iterator.hasNext()) {
			Component component = components.get(iterator.next());
			component.draw();
		}
	}
	
	@Override
	protected void paint() {
		drawBackground();
	}
	
	private void drawBackground() {
		glPushMatrix();
		glColor3f(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
		glRecti(0, 0, 800, 600);
		glPopMatrix();
	}
	
	@Override
	public void setBackgroundColor(Color color) {
		this.bgColor = color;
	}
	
	@Override
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
	
	@Override
	public void reparse() {
		// TODO Auto-generated method stub
		
	}
}
