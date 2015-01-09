package pl.grm.game.core.config;

import java.io.*;

import org.ini4j.*;

import pl.grm.game.core.*;
import pl.grm.game.core.inputs.*;

public class ConfigFile {
	
	public static void loadDefaults() {
		for (GameProperties property : GameProperties.values()) {
			if (property.getCategory().equals("Keys")) {
				float value = property.getfValue();
				KeyManager.addKeyListener((int) value, DefaultListeners.getListener((int) value));
			}
		}
	}
	
	public static void loadConfigFromFile() {
		if (configExists()) {
			readConfig();
		}
		
	}
	
	private static void readConfig() {
		// TODO Auto-generated method stub
		
	}
	
	private static boolean configExists() {
		boolean exists = true;
		File dir = new File(GameParameters.GAME_LOCATION);
		if (!dir.exists()) {
			dir.mkdir();
			exists = false;
		}
		File config = new File(GameParameters.GAME_LOCATION + GameParameters.CONFIG_FILE_NAME);
		if (!config.exists()) {
			try {
				createNewConfig(config);
			}
			catch (IOException e) {
				GameLogger.logException(e);
				e.printStackTrace();
			}
			exists = false;
		}
		return exists;
	}
	
	private static void createNewConfig(File config) throws IOException {
		config.createNewFile();
		Wini ini = new Wini(config);
		for (GameProperties property : GameProperties.values()) {
			String name = property.getName();
			String category = property.getCategory();
			Object value = property.getsValue();
			if (value == null) {
				value = property.getfValue();
			}
			ini.put(category, name, value);
		}
		ini.store();
	}
}
