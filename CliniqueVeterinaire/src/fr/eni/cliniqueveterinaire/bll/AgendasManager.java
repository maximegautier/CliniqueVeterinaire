package fr.eni.cliniqueveterinaire.bll;

import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;

/* Cr�� par Erwin DUPUIS */
public class AgendasManager 
{
    //region DECLARATION


    //endregion DECLARATION

    //region CTOR

	private AgendasManager()
	{
	}

    //endregion CTOR
    
    //region METHODS
    
	public static List<Agendas> selectParDate(Date jour) throws BLLException
	{
		if(jour == null)
		{
			throw new BLLException("(AgendasManager)SelectEntreDates : La date ne peut pas �tre null");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().selectParDate(jour);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}			
		}
	}
	
	public static boolean ajouter(Agendas aAjouter) throws BLLException
	{
		if(aAjouter == null)
		{
			throw new BLLException("(AgendasManager)Ajouter : Le rendez vous � ajouter ne peut pas etre null");
		}
		else if(isNegativeInt(aAjouter.getCodeAnimal()))
		{
			throw new BLLException("(AgendasManager)Ajouter : Le code de l'animal � consulter ne peut pas etre null");
		}
		else if(isNegativeInt(aAjouter.getCodeVeto()))
		{
			throw new BLLException("(AgendasManager)Ajouter : Le code du v�t�rinaire en charge ne peut pas etre null");
		}
		else if(aAjouter.getDateRdv() == null)
		{
			throw new BLLException("(AgendasManager)Ajouter : La date de rendez vous ne peut pas etre null");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().ajouter(aAjouter);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
	}
	
	public static boolean supprimer(Agendas aSupprimer) throws BLLException
	{
		if(aSupprimer == null)
		{
			throw new BLLException("(AgendasManager)Supprimer : Le rendez vous � ajouter ne peut pas etre null");
		}
		else if(isNegativeInt(aSupprimer.getCodeAnimal()))
		{
			throw new BLLException("(AgendasManager)Supprimer : Le code de l'animal � consulter ne peut pas etre null");
		}
		else if(isNegativeInt(aSupprimer.getCodeVeto()))
		{
			throw new BLLException("(AgendasManager)Supprimer : Le code du v�t�rinaire en charge ne peut pas etre null");
		}
		else if(aSupprimer.getDateRdv() == null)
		{
			throw new BLLException("(AgendasManager)Supprimer : La date de rendez vous ne peut pas etre null");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().supprimer(aSupprimer);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
	}
	
	//************
	//UTILITAIRES
	//************	
	
    private static boolean isEmptyOrNull(String toTest)
    {
        if(toTest != null && !toTest.trim().isEmpty())
            return false;
        else
            return true;
    }

    private static boolean isNegativeInt(int toCheck)
    {
        if(toCheck >= 0)
            return false;
        else
            return true;
    }
    
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
