package pl.grm.game.gui;

import org.lwjgl.util.*;

import pl.grm.game.core.*;
import pl.grm.game.core.events.*;
import pl.grm.game.core.inputs.*;
import pl.grm.game.gui.component.*;

public class FrameSetupData {
	private static Panel	titlePanel		= new Panel(0, 0, 400, 300, "TitlePanel");
	private static Panel	buttonsPanel	= new Panel(400, 400, 200, 200, "ButtonsPanel");
	private static Label	titleLabel1		= new Label(0, 0, 100, 10, "TitleLabel");
	private static Label	titleLabel2		= new Label(0, 100, 100, 10, "Title2Label");
	private static Button	closeButton		= new Button("CloseButton");
	
	public static void setupFrame(GameFrame frame) {
		connectAllTogether(frame);
		closeButton.setBackgroundColor((Color) ReadableColor.ORANGE);
		// closeButton.setLocationByMid(0, 0);
		// buttonsPanel.reparse();
		closeButton.addActionListener(new GameKeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				GameController.stopGame();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		titleLabel1.setText("THE LWJGL JAVA GAME");
		titleLabel1.setFontColor(org.newdawn.slick.Color.green);
		
		titleLabel2.setText("Main Menu!");
		titleLabel2.setFontColor(org.newdawn.slick.Color.green);
		
	}
	
	private static void connectAllTogether(GameFrame frame) {
		frame.add(buttonsPanel);
		frame.add(titlePanel);
		buttonsPanel.add(closeButton);
		titlePanel.add(titleLabel1);
		titlePanel.add(titleLabel2);
	}
}
