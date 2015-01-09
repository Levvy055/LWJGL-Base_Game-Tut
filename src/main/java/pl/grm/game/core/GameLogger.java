package pl.grm.game.core;

import java.util.logging.*;

/**
 * Logging class to log all things from game to file
 */
public class GameLogger {
	private static Logger	logger;
	
	public static void info(String msg) {
		if (logger != null)
			logger.info(msg);
	}
	
	public static void log(Level level, String msg, Throwable thrown) {
		if (logger != null)
			logger.log(level, msg, thrown);
	}
	
	public static void setLogger(Logger logger) {
		GameLogger.logger = logger;
	}
}
