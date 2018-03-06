package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
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
	
	public static List<Races> selectRaces(String espece) throws BLLException
	{
		List<Races> aRetourner = new ArrayList<Races>();
		
		if(isEmptyOrNull(espece))
		{
			throw new BLLException("L'espece renseignée ne peut pas être nulle");
		}
		else
		{
			try 
			{
				aRetourner = getRacesDao().selectRaces(espece);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return aRetourner;
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
