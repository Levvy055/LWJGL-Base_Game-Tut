package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.*;

public abstract class Component {
	private Container	parent;
	private int			x;
	private int			y;
	private int			width;
	private int			height;
	private Color		backgroundColor	= (Color) Color.CYAN;
	private Color		foregroundColor;
	private boolean		visible;
	private boolean		enabled;
	private String		name;
	private boolean		focus;
	
	public Component() {}
	
	public Container getParent() {
		return this.parent;
	}
	
	public void setParent(Container parent) {
		this.parent = parent;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Color getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public Color getForegroundColor() {
		return this.foregroundColor;
	}
	
	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isFocus() {
		return this.focus;
	}
	
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	
	public void draw() {
		glColor3f(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue());
		glPushMatrix();
		paint();
		glPopMatrix();
		
	}
	
	public abstract void paint();
}
