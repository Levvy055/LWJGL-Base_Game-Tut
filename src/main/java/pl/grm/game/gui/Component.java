package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.*;

import pl.grm.game.core.basethreads.*;
import pl.grm.game.core.inputs.*;

public abstract class Component {
	private Container				parent;
	private ArrayList<Component>	childs			= new ArrayList<Component>();
	private int						x				= 0;
	private int						y				= 0;
	private int						width			= 64;
	private int						height			= 64;
	private Color					backgroundColor	= (Color) ReadableColor.GREY;
	private Color					foregroundColor	= (Color) ReadableColor.WHITE;
	private boolean					visible			= true;
	private boolean					enabled			= true;
	private String					name			= "Component";
	private boolean					focus			= false;
	
	public Component(int x, int y, int width, int height, String name) {
		this.setName(name);
		this.setPosition(x, y);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	protected abstract void paint();
	
	public void draw() {
		if (isEnabled() && isVisible()) {
			glColor3f(backgroundColor.getRed(), backgroundColor.getGreen(),
					backgroundColor.getBlue());
			if (hasFocus()) {
				glColor3f(1f, 0f, 0f);
			}
			glPushMatrix();
			paint();
			glPopMatrix();
			if (hasChilds()) {
				for (Component component : childs) {
					component.draw();
				}
			}
		}
	}
	
	public void update() {
		if (isEnabled()) {
			int mX = Mouse.getX();
			int mY = Display.getHeight() - Mouse.getY();
			int x1 = getX();
			int x2 = getX() + getWidth();
			int y1 = getY();
			int y2 = getY() + getHeight();
			if (hasParent() && getParent() instanceof Component) {
				int pX = ((Component) parent).getX();
				int pY = ((Component) parent).getY();
				x1 = x1 - pX;
				x2 -= pX;
				y1 -= pY;
				y2 -= pY;
			}
			boolean xT = mX > x1 && mX < x2;
			boolean yT = mY > y1 && mY < y2;
			if (xT && yT) {
				setFocus(true);
			} else {
				setFocus(false);
			}
			if (hasChilds()) {
				for (Component component : childs) {
					component.update();
				}
			}
		}
	}
	
	protected void addChild(Component child) {
		if (this instanceof Container) {
			child.setParent((Container) this);
			this.childs.add(child);
		}
	}
	
	public void addActionListener(GameKeyListener gameButtonListener) {
		LWJGLEventMulticaster.addButtonListener(this.getName(), gameButtonListener);
	}
	
	public boolean hasChilds() {
		return !childs.isEmpty();
	}
	
	public synchronized void setPosition(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public synchronized void setSize(int s) {
		setWidth(getWidth() * s);
		setHeight(getHeight() * s);
	}
	
	public synchronized void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public boolean hasParent() {
		return parent != null ? true : false;
	}
	
	protected void drawRect(int x, int y, int width, int height) {
		glRecti(x, y, x + width, y + height);
	}
	
	public Container getParent() {
		return this.parent;
	}
	
	protected void setParent(Container parent) {
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
	
	public boolean hasFocus() {
		return this.focus;
	}
	
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
}
