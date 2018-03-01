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
    
	public static List<Races> SelectRacesChat() throws BLLException
	{
		try 
		{
			return racesDAO.selectRacesChat();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
	}
	
	public static List<Races> SelectRacesChien() throws BLLException 
	{
		try 
		{
			return racesDAO.selectRacesChien();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
	}
    
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
