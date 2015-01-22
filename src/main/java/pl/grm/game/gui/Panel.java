package pl.grm.game.gui;

public class Panel extends Component implements Container {
	
	public Panel(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
	}
	
	@Override
	public void paint() {
		drawRect(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void add(Component component) {
		int xC = component.getX() + getX();
		int yC = component.getY() + getY();
		component.setPosition(xC, yC);
		addChild(component);
		if (xC + component.getWidth() > getX() + getWidth()) {
			setWidth(xC + component.getWidth() - getX());
		}
		if (xC + component.getHeight() > getY() + getHeight()) {
			setHeight(yC + component.getHeight() - getY());
		}
	}
}
