package pl.grm.game.gui.component;

import org.lwjgl.input.*;

import pl.grm.game.core.loadstages.*;

public class Button extends Component {
	private boolean		pressed;
	private KeyState	currentState	= KeyState.RELEASED;
	private KeyState	lastState		= KeyState.RELEASED;
	
	public Button(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
	}
	
	public Button(String name) {
		super(name);
	}
	
	@Override
	public void paint() {
		drawRect(coords.getRX1(), coords.getRY1(), coords.getWidth(), coords.getHeight());
	}
	
	@Override
	public void update() {
		super.update();
		System.out.println(coords.toString());
		if (Mouse.isCreated()) {
			boolean btnDown = Mouse.isButtonDown(0);
			boolean focus = hasFocus();
			if (focus && btnDown && isPressed()) {
				System.out.println("Still pressed");
				MainMenu.buttonQueue.add(this);
			} else if (focus && !btnDown && isPressed()) {
				setPressed(false);
				System.out.println("Released");
				currentState = KeyState.RELEASED;
				MainMenu.buttonQueue.add(this);
			} else if (focus && btnDown && !isPressed()) {
				setPressed(true);
				System.out.println("Pressed");
				currentState = KeyState.PRESSED;
				MainMenu.buttonQueue.add(this);
			} else if (!focus && btnDown && isPressed()) {
				setPressed(false);
				currentState = KeyState.RELEASED;
				System.out.println("Released Off");
			}
			
			if (!currentState.equals(lastState)) {
				lastState = currentState;
			}
		}
	}
	
	private enum KeyState {
		PRESSED ,
		RELEASED
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean isRepeatEvent() {
		return lastState.equals(KeyState.PRESSED);
	}
}
