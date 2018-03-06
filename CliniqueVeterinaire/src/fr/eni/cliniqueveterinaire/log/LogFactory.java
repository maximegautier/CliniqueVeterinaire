package fr.eni.cliniqueveterinaire.log;

public class LogFactory {

	private static LogFileTxt logFile;
	
	public static LogFileTxt getLog()
	{
		if(logFile == null)
		{
			logFile = new LogFileTxt();
		}
		return logFile;
    }
}
