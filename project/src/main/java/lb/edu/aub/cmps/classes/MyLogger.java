package lb.edu.aub.cmps.classes;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Defines the logging system in the program
 * 
 * @author Bilal Abi Farraj
 */

public class MyLogger {
	public final static Logger myLogger = Logger.getLogger("Test");

	private static MyLogger instance = null;

	public static MyLogger getInstance() throws SecurityException, IOException {
		if (instance == null) {
			prepareLogger();
			instance = new MyLogger();
		}
		return instance;
	}

	public Logger getLogger(){
		return myLogger;
	}
	private static void prepareLogger() throws SecurityException, IOException {
		FileHandler myFileHandler = new FileHandler("Log.txt");
		myFileHandler.setFormatter(new SimpleFormatter());
		myLogger.addHandler(myFileHandler);
		myLogger.setUseParentHandlers(false);
		myLogger.setLevel(Level.FINEST);
	}
}
