package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;

/* Créé par Erwin DUPUIS */
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
    
	public static List<Agendas> SelectEntreDates(Date Debut, Date Fin) throws BLLException
	{
		if(Debut == null)
		{
			throw new BLLException("(AgendasManager)SelectEntreDates : La date de début ne peut pas être null");
		}
		else if(Fin == null)
		{
			throw new BLLException("(AgendasManager)SelectEntreDates : La date de fin ne peut pas être null");
		}
		else if(Fin.before(Debut))
		{
			throw new BLLException("(AgendasManager)SelectEntreDates : La date de fin ne peut pas être inférieur à la date de début");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().selectParDate(Debut, Fin);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}			
		}
	}
	
	public static boolean Ajouter(Agendas aAjouter) throws BLLException
	{
		if(aAjouter == null)
		{
			throw new BLLException("(AgendasManager)Ajouter : Le rendez vous à ajouter ne peut pas etre null");
		}
		else if(isNegativeInt(aAjouter.getCodeAnimal()))
		{
			throw new BLLException("(AgendasManager)Ajouter : Le code de l'animal à consulter ne peut pas etre null");
		}
		else if(isNegativeInt(aAjouter.getCodeVeto()))
		{
			throw new BLLException("(AgendasManager)Ajouter : Le code du vétérinaire en charge ne peut pas etre null");
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
	
	public static boolean Supprimer(Agendas aSupprimer) throws BLLException
	{
		if(aSupprimer == null)
		{
			throw new BLLException("(AgendasManager)Supprimer : Le rendez vous à ajouter ne peut pas etre null");
		}
		else if(isNegativeInt(aSupprimer.getCodeAnimal()))
		{
			throw new BLLException("(AgendasManager)Supprimer : Le code de l'animal à consulter ne peut pas etre null");
		}
		else if(isNegativeInt(aSupprimer.getCodeVeto()))
		{
			throw new BLLException("(AgendasManager)Supprimer : Le code du vétérinaire en charge ne peut pas etre null");
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
