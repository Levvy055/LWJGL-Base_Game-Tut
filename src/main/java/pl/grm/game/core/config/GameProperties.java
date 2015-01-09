package pl.grm.game.core.config;

public enum GameProperties {
	KEY_ESC(
			"Keys",
			"Close Game",
			0x01) ,
	LAST_PLAYER_NAME(
			"Player",
			"Last Player Name",
			"Player");
	
	private String	category;
	private String	name;
	private String	sValue;
	private float	fValue;
	
	GameProperties(String category, String name, String value) {
		this.category = category;
		this.name = name;
		this.sValue = value;
		
	}
	
	GameProperties(String category, String name, float value) {
		this.category = category;
		this.name = name;
		this.fValue = value;
		
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
}
