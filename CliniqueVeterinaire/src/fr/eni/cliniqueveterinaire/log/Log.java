package fr.eni.cliniqueveterinaire.log;

import java.util.logging.Level;

public interface Log {
	
	public void init();
	public void createLog(Level info, String message);
}