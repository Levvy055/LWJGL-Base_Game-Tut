package pl.grm.game.gui.component;

import javax.swing.*;

import org.lwjgl.input.*;

import pl.grm.game.core.loadstages.*;

public class Button extends Component {
	private boolean	pressed;
	private boolean	pressedBefore;
	
	public Button(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
	}
	
	@Override
	public void paint() {
		drawRect(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void update() {
		new JFrame().addWindowListener(null);
		super.update();
		if (hasFocus() && Mouse.isButtonDown(0)) {
			if (!isPressed()) {
				setPressed(true);
				System.out.println("Pressed");
				MainMenu.buttonQueue.add(this);
			} else if (!wasPressed()) {
				setPressedBefore(true);
				System.out.println("Still pressed");
				MainMenu.buttonQueue.add(this);
			}
		} else if (hasFocus()) {
			if (isPressed()) {
				setPressed(false);
			} else if (wasPressed()) {
				setPressedBefore(false);
				System.out.println("Released");
				MainMenu.buttonQueue.add(this);
			}
		} else {
			setPressedBefore(false);
		}
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean wasPressed() {
		return pressedBefore;
	}
	
	public void setPressedBefore(boolean pressedBefore) {
		this.pressedBefore = pressedBefore;
	}
}
