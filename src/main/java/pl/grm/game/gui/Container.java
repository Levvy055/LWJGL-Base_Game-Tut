package pl.grm.game.gui;

import pl.grm.game.gui.component.*;

public interface Container {
	public void add(Component component);
	
	public void reparse();
}
