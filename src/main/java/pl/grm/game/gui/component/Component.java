package pl.grm.game.gui.component;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;
import java.util.concurrent.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.*;

import pl.grm.game.core.inputs.*;
import pl.grm.game.core.threads.*;
import pl.grm.game.gui.*;
import pl.grm.game.math.*;

public abstract class Component {
	protected Container								parent;
	protected ConcurrentHashMap<String, Component>	childs					= new ConcurrentHashMap<String, Component>();
	protected Coordinates2D							coords					= new Coordinates2D();
	protected Color									backgroundColor			= (Color) ReadableColor.PURPLE;
	protected Color									focusBackgroundColor	= (Color) ReadableColor.LTGREY;
	protected boolean								backgroundTransparent	= false;
	protected Color									foregroundColor			= (Color) ReadableColor.WHITE;
	protected boolean								visible					= true;
	protected boolean								enabled					= true;
	protected String								name					= "Component";
	protected boolean								focus					= false;
	
	public Component(int x, int y, int width, int height, String name) {
		this.setName(name);
		this.setPosition(x, y);
		this.coords.setWidth(width);
		this.coords.setHeight(height);
		this.updateCoords();
	}
	
	public Component(int x, int y, String name) {
		this(x, y, 64, 64, name);
	}
	
	public Component(String name) {
		this(0, 0, name);
	}
	
	public void draw() {
		if (isEnabled() && isVisible()) {
			if (!isBackgroundTransparent()) {
				if (hasFocus()) {
					glColor3f(focusBackgroundColor.getRed(), focusBackgroundColor.getGreen(),
							focusBackgroundColor.getBlue());
				} else {
					glColor3f(backgroundColor.getRed(), backgroundColor.getGreen(),
							backgroundColor.getBlue());
				}
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
	
	protected abstract void paint();
	
	public void update() {
		if (isEnabled()) {
			int mX = Mouse.getX();
			int mY = Display.getHeight() - Mouse.getY();
			int x1 = coords.getRX1();
			int x2 = coords.getRX2();
			int y1 = coords.getRY1();
			int y2 = coords.getRY2();
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
			child.getCoords().setCoordinateSystem(coords);
			this.childs.put(child.getName(), child);
		}
	}
	
	public void addActionListener(GameKeyListener gameButtonListener) {
		LWJGLEventMulticaster.addButtonListener(this.getName(), gameButtonListener);
	}
	
	public synchronized void setLocationByMid(int x, int y) {
		setPosition(2 * x - coords.getWidth() / 2, 2 * y - coords.getHeight() / 2);// TODO
	}
	
	public synchronized void setPosition(int x1, int y1) {
		this.coords.setX1(x1);
		this.coords.setY1(y1);
	}
	
	public synchronized void setSize(int s) {
		coords.setWidth(coords.getWidth() * s);
		coords.setHeight(coords.getHeight() * s);
	}
	
	public synchronized void setSize(int width, int height) {
		coords.setWidth(width);
		coords.setHeight(height);
	}
	
	private void updateCoords() {
		if (hasParent()) {
			coords.setCoordinateSystem(((Component) parent).getCoords());
		}
	}
	
	protected void drawRect(int x, int y, int width, int height) {
		glRecti(x, y, x + width, y + height);
	}
	
	public boolean hasChilds() {
		return !childs.isEmpty();
	}
	
	public boolean hasParent() {
		return parent != null ? true : false;
	}
	
	public Container getParent() {
		return this.parent;
	}
	
	public void setParent(Container parent) {
		this.parent = parent;
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
	
	public ConcurrentHashMap<String, Component> getChilds() {
		return childs;
	}
	
	public Coordinates2D getCoords() {
		return this.coords;
	}
}
