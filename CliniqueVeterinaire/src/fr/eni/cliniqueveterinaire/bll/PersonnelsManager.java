package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Personnels;

public class PersonnelsManager 
{
    //region DECLARATION

	//private PersonnelsDAO personnelDAO;
	private static PersonnelsManager instance;

    //endregion DECLARATION

    //region CTOR

	private PersonnelsManager()
	{
		//personnelDAO = DAOFactory.getPersonnelsDAO();
	}

    //endregion CTOR
    
    //region METHODS
    
	public boolean Authentification(String Nom, String MotPasse)
	{
		return false;
	}
	
	public int Ajouter(Personnels aAjouter)
	{
		return 0;
	}
	
	public List<Personnels> SelectTous()
	{
		List<Personnels> tmp = new ArrayList<Personnels>();
		return tmp;
	}
	
	public boolean ModifierMDP(int CodePers, String MotPasse)
	{
		return false;
	}
	
	public boolean Supprimer(int CodePers)
	{
		return false;
	}
    
    //endregion METHODS

    //region GET/SET

	public PersonnelsManager getInstance()
	{
		if(PersonnelsManager.instance == null)
		{
			PersonnelsManager.instance = new PersonnelsManager();
		}
		return PersonnelsManager.instance;
	}

    //endregion GET/SET
}
