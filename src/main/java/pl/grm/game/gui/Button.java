package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

public class Button extends Component {
	
	public Button(int x, int y, String name) {
		super(x, y, name);
	}
	
	public Button(String name) {
		super(name);
	}
	
	@Override
	public void paint() {
		glRecti(getX(), getY(), getWidth(), getHeight());
		glLineWidth(5.8f);
	}
	
}
