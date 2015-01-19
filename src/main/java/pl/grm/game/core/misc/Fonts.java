package pl.grm.game.core.misc;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.awt.Font;
import java.io.*;
import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.util.*;

public class Fonts {
	private static ArrayList<TrueTypeFont>	fonts;
	
	private static void init() throws FontFormatException, IOException {
		fonts = new ArrayList<TrueTypeFont>();
		fonts.add(new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false));
		InputStream inputStream = ResourceLoader.getResourceAsStream("arial.ttf");
		;
		fonts.add(new TrueTypeFont(
				Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(24f), false));
	}
	
	public static TrueTypeFont getFont(int i) {
		glEnable(GL_TEXTURE_2D);
		if (fonts == null) {
			try {
				init();
			}
			catch (FontFormatException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fonts.size() > i) { return fonts.get(i); }
		return new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false);
	}
}
