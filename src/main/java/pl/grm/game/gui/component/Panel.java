package pl.grm.game.gui.component;

import java.util.*;

import pl.grm.game.gui.*;

public class Panel extends Component implements Container {
	
	public Panel(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
		setBackgroundTransparent(false);
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
		for (Iterator<String> it = this.getChilds().keySet().iterator(); it.hasNext();) {
			Component child = getChilds().get(it.next());
			int xC = child.getX() + getX();
			int yC = child.getY() + getY();
			child.setPosition(xC, yC);
			if (xC + child.getWidth() > getX() + getWidth()) {
				this.setWidth(xC + child.getWidth() - getX());
			}
			if (xC + child.getHeight() > getY() + getHeight()) {
				this.setHeight(yC + child.getHeight() - getY());
			}
		}
	}
}
