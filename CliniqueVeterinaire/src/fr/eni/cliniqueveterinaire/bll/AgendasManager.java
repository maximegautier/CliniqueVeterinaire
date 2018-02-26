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
    
	public List<Agendas> SelectEntreDates(Date Debut, Date Fin)
	{
		List<Agendas> tmp = new ArrayList<Agendas>();
		return tmp;
	}
	
	public int Ajouter(Agendas aAjouter)
	{
		return 0;
	}
	
	public boolean Supprimer(Agendas aSupprimer)
	{
		return false;
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
