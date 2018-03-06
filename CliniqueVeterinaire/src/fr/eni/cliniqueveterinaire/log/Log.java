package fr.eni.cliniqueveterinaire.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    
    private final static Logger logger = Logger.getLogger(LogFactory.class.getName());
    
	public static void thing(){
		logger.log(Level.WARNING,"something to log");
	}
	
}
