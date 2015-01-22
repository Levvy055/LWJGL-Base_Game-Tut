package pl.grm.game.gui;


public class Button extends Component {
	
	public Button(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
	}
	
	@Override
	public void paint() {
		drawRect(getX(), getY(), getWidth(), getHeight());
	}
}
