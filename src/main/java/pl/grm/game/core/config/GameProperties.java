package pl.grm.game.core.config;

import org.lwjgl.input.*;

public enum GameProperties {
	KEY_ESC(
			"Keys",
			"Close Game",
			Keyboard.KEY_ESCAPE) ,
	KEY_INV(
			"Key",
			"Inventory Key",
			Keyboard.KEY_I) ,
	LAST_PLAYER_NAME(
			"Player",
			"Last Player Name",
			"Player");
	
	private String	category;
	private String	name;
	private String	sValue;
	private float	fValue;
	private int		iValue;
	
	GameProperties(String category, String name, String value) {
		this.category = category;
		this.name = name;
		this.sValue = value;
	}
	
	GameProperties(String category, String name, float value) {
		this.category = category;
		this.name = name;
		this.fValue = value;
		this.iValue = Integer.parseInt(value + "");
	}
	
	GameProperties(String category, String name, int value) {
		this.category = category;
		this.name = name;
		this.iValue = value;
		
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getsValue() {
		return this.sValue;
	}
	
	public float getfValue() {
		return this.fValue;
	}
	
	public int getiValue() {
		return iValue;
	}
}
