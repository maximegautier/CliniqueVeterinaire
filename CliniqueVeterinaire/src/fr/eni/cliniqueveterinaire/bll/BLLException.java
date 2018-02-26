package fr.eni.cliniqueveterinaire.bll;

public class BLLException extends Exception
{
    //region DECLARATION

	private String message;

    //endregion DECLARATION

    //region CTOR

	public BLLException() 
	{
        System.out.println("Erreur BLLException");
    }

    public BLLException(String message)
    {
        this.message = message;
        System.out.println("BLLException : "+message+".");
    }

    public BLLException(String message, Throwable exception)
    {
        System.out.println("BLLException : "+message+", Exception : "+exception);
    }

    //endregion CTOR

    //region GET/SET

    public String getMessage()
    {
        return message;
    }

    //endregion GET/SET
}
