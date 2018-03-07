package fr.eni.cliniqueveterinaire.bll;

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
    
	public static List<Agendas> selectParDate(Date jour) throws BLLException
	{
		if(jour == null)
		{
			throw new BLLException("La date ne peut pas être nulle");
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
	
	public static List<Agendas> selectParDateVeterinaire(Date jour, int codeVeterinaire) throws BLLException
	{
		if(jour == null)
		{
			throw new BLLException("La date ne peut pas être nulle");
		}
		else if(isNegativeInt(codeVeterinaire))
		{
			throw new BLLException("Le code veterinaire ne peut pas être nul");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().selectParDateVeterinaire(jour, codeVeterinaire);
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
			throw new BLLException("Le rendez vous à ajouter ne peut pas etre nul");
		}
		else if(isNegativeInt(aAjouter.getCodeAnimal()))
		{
			throw new BLLException("Le code de l'animal à consulter ne peut pas etre nul");
		}
		else if(isNegativeInt(aAjouter.getCodeVeto()))
		{
			throw new BLLException("Le code du vétérinaire en charge ne peut pas etre nul");
		}
		else if(aAjouter.getDateRdv() == null)
		{
			throw new BLLException("La date de rendez vous ne peut pas etre nulle");
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
			throw new BLLException("Le rendez vous à ajouter ne peut pas etre nul");
		}
		else if(isNegativeInt(aSupprimer.getCodeAnimal()))
		{
			throw new BLLException("Le code de l'animal à consulter ne peut pas etre nul");
		}
		else if(isNegativeInt(aSupprimer.getCodeVeto()))
		{
			throw new BLLException("Le code du vétérinaire en charge ne peut pas etre nul");
		}
		else if(aSupprimer.getDateRdv() == null)
		{
			throw new BLLException("La date de rendez vous ne peut pas etre nulle");
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
	
	public static boolean supprimerParNom(int codeAnimal) throws BLLException
	{
		if(isNegativeInt(codeAnimal))
		{
			throw new BLLException("Le code de l'animal à consulter ne peut pas etre nul");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().supprimerParNom(codeAnimal);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
	}
	
	public static boolean verifierSiExiste(Agendas aVerifier) throws BLLException
	{
		if(aVerifier.getDateRdv() == null)
		{
			throw new BLLException("La date du rendez-vous à verifier ne peut pas etre nulle");
		}
		else if(isNegativeInt(aVerifier.getCodeVeto()))
		{
			throw new BLLException("Le code veterinaire du rendez-vous à verifier ne peut pas etre nul");
		}
		else if(isNegativeInt(aVerifier.getCodeAnimal()))
		{
			throw new BLLException("Le code animal du rendez-vous a verifier ne peut pas etre nul");
		}
		else
		{
			try 
			{
				return DAOFactory.getAgendasDAO().verifieSiExiste(aVerifier);
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
