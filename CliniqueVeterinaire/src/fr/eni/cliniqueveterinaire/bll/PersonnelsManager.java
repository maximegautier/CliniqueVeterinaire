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
	
	public int Ajouter(Personnels perso) throws BLLException
	{
		if(perso == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel null.");
		}
		else if(perso.getMotPasse() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un mot de passe null.");
		}
		else if(perso.getRole() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un role null.");
		}
		else if(perso.getNom() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un nom null.");
		}
		else if(perso.getRole().length() > 3)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : Le role est définie par : vet, adm... et ne peut pas excéder 3 caractères");
		}
		else
		{
			//Logique d'ajout à la base via DAO
			return 0;			
		}

	}
	
	public List<Personnels> getCatalogue()
	{
		List<Personnels> tmp = new ArrayList<Personnels>();
		return tmp;
	}
	
	public boolean ModifierMDP(Personnels perso, String oldMotPasse, String newMotPasse) throws BLLException
	{
		if(isEmptyOrNull(newMotPasse))
		{
			throw new BLLException("(PersonnelsManager)Modifier : Le nouveau mot de passe ne peut pas être null.");
		}
		else
		{
			//Logique de modification en base via DAO
			return false;
		}
	}
	
	public boolean Supprimer(Personnels perso) throws BLLException
	{
		//Logique de suppression en base (Archive = 1) via DAO
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
