package pl.grm.game.core.misc;

import java.lang.reflect.*;
import java.util.*;
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
	
	public static void logException(Exception e) {
		log(Level.SEVERE, e.getMessage(), e);
	}
	
	public static void setLogger(Logger logger) {
		GameLogger.logger = logger;
	}
	
	public static void closeLogger() {
		Handler handler = logger.getHandlers()[0];
		logger.removeHandler(handler);
		handler.close();
	}
	
	public static void printDebugFieldValue(Object obj, String... stringsFieldNames) {
		Class<?> clazz = obj.getClass();
		if (stringsFieldNames.length == 0) { return; }
		ArrayList<Field> fields = new ArrayList<Field>();
		Field[] fieldsClazz = clazz.getDeclaredFields();
		for (Field field : fieldsClazz) {
			fields.add(field);
		}
		Class<?> sClazz = clazz;
		while (sClazz.getSuperclass() != null && sClazz.getSuperclass() != Object.class) {
			Class<?> superClass = sClazz.getSuperclass();
			fieldsClazz = superClass.getDeclaredFields();
			for (Field field : fieldsClazz) {
				fields.add(field);
			}
			sClazz = superClass;
		}
		System.out.println("ANALYZE OF " + fields.size() + " FIELDS");
		try {
			for (String string : stringsFieldNames) {
				System.out.print("Field ");
				for (Field field : fields) {
					if (field.getName().equalsIgnoreCase(string)) {
						if (clazz.getMethod("toString") != null) {
							Method method = clazz.getMethod("toString");
							Class<?> fieldClass = field.getDeclaringClass();
							Object objCasted = fieldClass.cast(obj);
							System.out.print(string + ": " + method.invoke(objCasted));
						} else {
							System.out.print(string + ": " + field.toString());
						}
					}
				}
				System.out.println("");
			}
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
