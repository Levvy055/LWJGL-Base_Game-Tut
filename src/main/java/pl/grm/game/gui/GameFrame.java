package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.util.*;

import pl.grm.game.gui.component.*;

public class GameFrame extends Component implements Container {
	private Color	bgColor;
	
	public GameFrame() {
		super("Game Main Menu Frame");
		bgColor = (Color) ReadableColor.BLACK;
	}
	
	@Override
	public void add(Component component) {
		if (component == null) { return; }
		component.setParent(this);
		getChilds().put(component.getName(), component);
	}
	
	@Override
	public void draw() {
		paint();
		Iterator<String> iterator = getChilds().keySet().iterator();
		while (iterator.hasNext()) {
			Component component = getChilds().get(iterator.next());
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
	public void update() {
		Iterator<String> iterator = getChilds().keySet().iterator();
		while (iterator.hasNext()) {
			Component component = getChilds().get(iterator.next());
			component.update();
		}
	}
	
	@Override
	public void setBackgroundColor(Color color) {
		this.bgColor = color;
	}
	
	@Override
	public void reparse() {
		for (Iterator<String> it = this.getChilds().keySet().iterator(); it.hasNext();) {
			Component child = getChilds().get(it.next());
			// TODO adjust parent to its children params
		}
	}
}
