package fr.eni.cliniqueveterinaire.bll;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;
import fr.eni.cliniqueveterinaire.dal.RacesDAO;

/* Créé par Erwin DUPUIS */
public class RacesManager 
{
    //region DECLARATION

	private RacesDAO racesDAO;
	private static RacesManager instance;

    //endregion DECLARATION

    //region CTOR

	private RacesManager() throws BLLException
	{
		try 
		{
			racesDAO = DAOFactory.getRacesDAO();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
	}

    //endregion CTOR
    
    //region METHODS
    
    
    //endregion METHODS

    //region GET/SET

	public static RacesManager getInstance() throws BLLException
	{
		if(RacesManager.instance == null)
		{
			RacesManager.instance = new RacesManager();
		}
		return RacesManager.instance;
	}

    //endregion GET/SET
}
