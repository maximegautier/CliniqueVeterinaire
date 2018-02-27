package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;
import fr.eni.cliniqueveterinaire.dal.PersonnelsDAO;
import fr.eni.cliniqueveterinaire.dal.jdbc.PersonnelsDAOJdbcImpl;

public class PersonnelsManager 
{
    //region DECLARATION

	private PersonnelsDAO personnelsDAO;
	private static PersonnelsManager instance;

    //endregion DECLARATION

    //region CTOR

	private PersonnelsManager()
	{
		personnelsDAO = DAOFactory.getPersonnelsDAO();
	}

    //endregion CTOR
    
    //region METHODS
    
	public Personnels Authentification(String nom, String motPasse)
	{
		Personnels personnel = null;
		try {
			personnel = ((PersonnelsDAOJdbcImpl) personnelsDAO).checkConnexion(nom, motPasse);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personnel;
	}
	
	public int Ajouter(Personnels aAjouter) throws BLLException
	{
		if(aAjouter == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel null.");
		}
		else if(aAjouter.getMotPasse() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un mot de passe null.");
		}
		else if(aAjouter.getRole() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un role null.");
		}
		else if(aAjouter.getNom() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un nom null.");
		}
		else if(aAjouter.getRole().length() > 3)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : Le role est définie par : vet, adm... et ne peut pas excéder 3 caractères");
		}
		else
		{
			//Logique d'ajout à la base via DAO
			return 0;			
		}

	}
	
	public List<Personnels> SelectTous()
	{
		List<Personnels> tmp = new ArrayList<Personnels>();
		return tmp;
	}
	
	public boolean ModifierMDP(int CodePers, String MotPasse) throws BLLException
	{
		if(isNegativeInt(CodePers))
		{
			throw new BLLException("(PersonnelsManager)Modifier : Le code personnel ne peut pas être null.");
		}
		else if(isEmptyOrNull(MotPasse))
		{
			throw new BLLException("(PersonnelsManager)Modifier : Le nouveau mot de passe ne peut pas être null.");
		}
		else
		{
			//Logique de modification en base via DAO
			return false;
		}
	}
	
	public boolean Supprimer(int CodePers) throws BLLException
	{
		if(isNegativeInt(CodePers))
		{
			throw new BLLException("(PersonnelsManager)Supprimer : Le code personnel ne peut pas être null");
		}
		else
		{
			//Logique de suppression en base (Archive = 1) via DAO
			return false;			
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

	public static PersonnelsManager getInstance()
	{
		if(PersonnelsManager.instance == null)
		{
			PersonnelsManager.instance = new PersonnelsManager();
		}
		return PersonnelsManager.instance;
	}

    //endregion GET/SET
}
