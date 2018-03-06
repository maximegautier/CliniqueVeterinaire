package fr.eni.cliniqueveterinaire.log;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFileTxt implements Log {

    
    private final static Logger logger = Logger.getLogger(LogFileTxt.class.getName());

    private static FileHandler fh = null;
	
	public void init(){
		try {
			fh=new FileHandler("logs.log", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	Logger l = Logger.getLogger("");
    	fh.setFormatter(new SimpleFormatter());
    	l.addHandler(fh);
		l.setLevel(Level.CONFIG);
    }
    
	public void createLog(Level info, String message) {
		logger.log(info, message);
	}
	
}
