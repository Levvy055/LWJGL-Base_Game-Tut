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
		drawRect(coords.getX1(), coords.getY1(), coords.getWidth(), coords.getHeight());
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
			child.update();
		}
	}
}
