package pl.grm.game.core.inputs;

import org.lwjgl.input.*;

import pl.grm.game.core.*;
import pl.grm.game.core.entities.*;

public class KeyManager {
	
	public static void keyActionPerformer() {
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			Rectangle rec = new Rectangle(0, 0);
			GameController.addEntity(rec);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			Rectangle rec = new Rectangle(15, 20, 120, 60, 0.1f, 0.3f, 0.1f);
			GameController.addEntity(rec);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
			GameController.destroyAllEntities(Rectangle.getID());
		}
	}
	
}
