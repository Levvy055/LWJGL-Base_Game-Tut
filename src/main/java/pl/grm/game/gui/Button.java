package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

public class Button extends Component {
	
	public Button(int x, int y, String name) {
		super(x, y, name);
	}
	
	public Button(int x, int y, int width, int height, String name) {
		this(x, y, name);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	@Override
	public void paint() {
		glRecti(getX(), getY(), getWidth(), getHeight());
		glLineWidth(5.8f);
	}
	
}
