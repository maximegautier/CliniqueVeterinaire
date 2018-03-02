package fr.eni.cliniqueveterinaire.bll;

import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Races;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;
import fr.eni.cliniqueveterinaire.dal.RacesDAO;

/* Créé par Erwin DUPUIS */
public class RacesManager 
{
    //region DECLARATION

	private static RacesDAO racesDAO;

    //endregion DECLARATION

    //region CTOR

	private RacesManager() throws BLLException
	{
	}

    //endregion CTOR
    
    //region METHODS
    
	public static List<Races> selectRacesChat() throws BLLException
	{
		try 
		{
			return getRacesDao().selectRacesChat();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
	}
	
	public static List<Races> selectRacesChien() throws BLLException 
	{
		try 
		{
			return getRacesDao().selectRacesChien();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
	}
    
    //endregion METHODS

    //region GET/SET

	public static RacesDAO getRacesDao() throws BLLException
	{
		if(RacesManager.racesDAO == null)
		{
			try 
			{
				RacesManager.racesDAO = DAOFactory.getRacesDAO();
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return RacesManager.racesDAO;
	}

    //endregion GET/SET
}
