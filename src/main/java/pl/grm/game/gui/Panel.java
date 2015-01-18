package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

public class Panel extends Component implements Container {
	
	public Panel(int x, int y, String name) {
		super(x, y, name);
	}
	
	@Override
	public void paint() {
		glRecti(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void add(Component component) {
		int xC = component.getX() + getX();
		int yC = component.getY() + getY();
		component.setPosition(xC, yC);
		addChild(component);
	}
	
}
