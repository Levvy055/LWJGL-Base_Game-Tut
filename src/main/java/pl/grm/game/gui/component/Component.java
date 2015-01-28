package pl.grm.game.gui.component;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;
import java.util.concurrent.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.*;

import pl.grm.game.core.basethreads.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.gui.*;

public abstract class Component {
	protected Container								parent;
	protected ConcurrentHashMap<String, Component>	childs					= new ConcurrentHashMap<String, Component>();
	protected int									x						= 0;
	protected int									y						= 0;
	protected int									width					= 64;
	protected int									height					= 64;
	protected Color									backgroundColor			= (Color) ReadableColor.GREY;
	private boolean									backgroundTransparent	= false;
	protected Color									foregroundColor			= (Color) ReadableColor.WHITE;
	protected boolean								visible					= true;
	protected boolean								enabled					= true;
	protected String								name					= "Component";
	protected boolean								focus					= false;
	
	public Component(int x, int y, int width, int height, String name) {
		this.setName(name);
		this.setPosition(x, y);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	public Component() {}
	
	protected abstract void paint();
	
	public void draw() {
		if (isEnabled() && isVisible()) {
			if (!isBackgroundTransparent()) {
				glColor3f(backgroundColor.getRed(), backgroundColor.getGreen(),
						backgroundColor.getBlue());
			}
			if (hasFocus()) {
				glColor3f(1f, 0f, 0f);
			}
			glPushMatrix();
			paint();
			glPopMatrix();
			if (hasChilds()) {
				for (Iterator<String> it = childs.keySet().iterator(); it.hasNext();) {
					Component component = childs.get(it.next());
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
			boolean xT = mX > x1 && mX < x2;
			boolean yT = mY > y1 && mY < y2;
			if (xT && yT) {
				setFocus(true);
			} else {
				setFocus(false);
			}
			if (hasChilds()) {
				for (Iterator<String> it = childs.keySet().iterator(); it.hasNext();) {
					Component component = childs.get(it.next());
					component.update();
				}
			}
		}
	}
	
	protected void addChild(Component child) {
		if (this instanceof Container) {
			child.setParent((Container) this);
			this.childs.put(child.getName(), child);
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
	
	public boolean hasFocus() {
		return this.focus;
	}
	
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	
	public boolean isBackgroundTransparent() {
		return backgroundTransparent;
	}
	
	public void setBackgroundTransparent(boolean backgroundTransparent) {
		this.backgroundTransparent = backgroundTransparent;
	}
}
