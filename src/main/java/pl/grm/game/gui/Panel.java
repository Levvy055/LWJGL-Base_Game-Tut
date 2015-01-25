package pl.grm.game.gui;

import java.util.Iterator;

public class Panel extends Component implements Container {
	
	public Panel(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
		setBackgroundTransparent(true);
	}
	
	@Override
	public void paint() {
		drawRect(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void add(Component component) {
		addChild(component);
		reparse();
	}
	
	@Override
	public void reparse() {
		for (Iterator<String> it = childs.keySet().iterator(); it.hasNext();) {
			Component component = childs.get(it.next());
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
}
