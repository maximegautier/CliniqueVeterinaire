package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;

public class AgendasManager 
{
    //region DECLARATION

	//private AgendasDAO agendaDAO;
	private static AgendasManager instance;

    //endregion DECLARATION

    //region CTOR

	private AgendasManager()
	{
		//instanciation du DAO via DAOFactory;
		//agendaDAO = DAOFactory.getAgendaDAO();
	}

    //endregion CTOR
    
    //region METHODS
    
	public List<Agendas> SelectEntreDates(Date Debut, Date Fin) throws BLLException
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
			//Logique de select en base via DAO
			List<Agendas> tmp = new ArrayList<Agendas>();
			return tmp;			
		}
	}
	
	public int Ajouter(Agendas aAjouter) throws Exception
	{
		if(aAjouter == null)
		{
			throw new Exception("(AgendasManager)Ajouter : Le rendez vous à ajouter ne peut pas etre null");
		}
		else if(isNegativeInt(aAjouter.getCodeAnimal()))
		{
			throw new Exception("(AgendasManager)Ajouter : Le code de l'animal à consulter ne peut pas etre null");
		}
		else if(isNegativeInt(aAjouter.getCodeVeto()))
		{
			throw new Exception("(AgendasManager)Ajouter : Le code du vétérinaire en charge ne peut pas etre null");
		}
		else if(aAjouter.getDateRdv() == null)
		{
			throw new Exception("(AgendasManager)Ajouter : La date de rendez vous ne peut pas etre null");
		}
		return 0;
	}
	
	public boolean Supprimer(Agendas aSupprimer)
	{
		return false;
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
