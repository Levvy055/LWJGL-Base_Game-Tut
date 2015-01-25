package pl.grm.game.gui;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import pl.grm.game.core.misc.Fonts;

public class Label extends Component {
	private String			text		= getName();
	private TrueTypeFont	font;
	private Color			fontColor	= Color.black;
	private float			textPosX	= 0;
	private float			textPosY	= 0;
	
	public Label(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
	}
	
	@Override
	protected void paint() {
		setFont(Fonts.getFont(0));
		if (getText().length() * 17 > getWidth()) {
			setWidth(getText().length() * 17);
			getParent().reparse();
		}
		glEnable(GL_TEXTURE_2D);
		font.drawString(getX() + getTextPosX(), getY() + getTextPosY(), getText(),
				getFontColor());
		glDisable(GL_TEXTURE_2D);
	}
	
	public TrueTypeFont getFont() {
		return font;
	}
	
	public void setFont(TrueTypeFont font) {
		this.font = font;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Color getFontColor() {
		return fontColor;
	}
	
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
	public void setTextPos(float x, float y) {
		setTextPosX(x);
		setTextPosY(y);
	}
	
	public float getTextPosX() {
		return textPosX;
	}
	
	public void setTextPosX(float textPosX) {
		this.textPosX = textPosX;
	}
	
	public float getTextPosY() {
		return textPosY;
	}
	
	public void setTextPosY(float textPosY) {
		this.textPosY = textPosY;
	}
	
}
