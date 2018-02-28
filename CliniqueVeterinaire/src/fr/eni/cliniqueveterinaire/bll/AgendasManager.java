package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;

public class AgendasManager 
{
    //region DECLARATION

	private static AgendasManager instance;

    //endregion DECLARATION

    //region CTOR

	private AgendasManager()
	{
	}

    //endregion CTOR
    
    //region METHODS
    
	public List<Agendas> SelectEntreDates(Date Debut, Date Fin) throws BLLException
	{
		if(Debut == null)
		{
			throw new BLLException("(AgendasManager)SelectEntreDates : La date de d�but ne peut pas �tre null");
		}
		else if(Fin == null)
		{
			throw new BLLException("(AgendasManager)SelectEntreDates : La date de fin ne peut pas �tre null");
		}
		else if(Fin.before(Debut))
		{
			throw new BLLException("(AgendasManager)SelectEntreDates : La date de fin ne peut pas �tre inf�rieur � la date de d�but");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().SelectParDate(Debut, Fin);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}			
		}
	}
	
	public boolean Ajouter(Agendas aAjouter) throws Exception
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
				return DAOFactory.getAgendasDAO().Ajouter(aAjouter);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
	}
	
	public boolean Supprimer(Agendas aSupprimer) throws BLLException
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
				return DAOFactory.getAgendasDAO().Supprimer(aSupprimer);
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
	
    private boolean isEmptyOrNull(String toTest)
    {
        if(toTest != null && !toTest.trim().isEmpty())
            return false;
        else
            return true;
    }

    private boolean isNegativeInt(int toCheck)
    {
        if(toCheck >= 0)
            return false;
        else
            return true;
    }
    
    //endregion METHODS

    //region GET/SET

	public AgendasManager getInstance() 
	{
		if(AgendasManager.instance == null)
		{
			AgendasManager.instance = new AgendasManager();
		}
		return AgendasManager.instance;
	}

    //endregion GET/SET
}
