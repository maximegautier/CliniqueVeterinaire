package fr.eni.cliniqueveterinaire.log;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFactory {

	private static final Logger LOGGER = Logger.getLogger( LogFactory.class.getName() );
	private static FileHandler fh = null;
	
	public static void init(){
		try {
			fh=new FileHandler("loggerExample.log", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	Logger l = Logger.getLogger("");
    	fh.setFormatter(new SimpleFormatter());
    	l.addHandler(fh);
		l.setLevel(Level.CONFIG);
    }
    
	public static void main(String[] args) {
		LogFactory.init();
 
		LOGGER.log(Level.INFO, "message 1");
		LOGGER.log(Level.SEVERE, "message 2");
		LOGGER.log(Level.FINE, "message 3");
		Log.thing();
	}
}
