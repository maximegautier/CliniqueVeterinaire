package fr.eni.cliniqueveterinaire.bll;

import java.util.List;

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
	/* Créé par Maxime GAUTIER */
	private PersonnelsManager()
	{
		personnelsDAO = DAOFactory.getPersonnelsDAO();
	}

    //endregion CTOR
    
    //region METHODS
	/* Créé par Maxime GAUTIER */
	public Personnels Authentification(String nom, String motPasse)
	{
		Personnels personnel = null;
		try {
			personnel = ((PersonnelsDAOJdbcImpl) personnelsDAO).checkConnexion(nom, Cryptage.encrypt(motPasse));
		} catch (DALException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	/* Créé par Maxime GAUTIER */
	public int Ajouter(Personnels personnel) throws BLLException
	{
		if(personnel == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel null.");
		}
		else if(personnel.getMotPasse() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un mot de passe null.");
		}
		else if(personnel.getRole() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un role null.");
		}
		else if(personnel.getNom() == null)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : On ne peut pas ajouter un personnel avec un nom null.");
		}
		else if(personnel.getRole().length() > 3)
		{
			throw new BLLException("(PersonnelsManager)Ajouter : Le role est définie par : vet, adm... et ne peut pas excéder 3 caractères");
		}
		else
		{
			//Logique d'ajout à la base via DAO
			try {
				personnel.setMotPasse(Cryptage.encrypt(personnel.getMotPasse()));
				personnelsDAO.insert(personnel);
			} catch (DALException e) {
				e.printStackTrace();
			}
			return 0;			
		}
	}
	
	/* Créé par Maxime GAUTIER */
	public List<Personnels> selectTousPersonnels() throws DALException
	{
		List<Personnels> lPersonnels = null;
		lPersonnels = personnelsDAO.selectAll();
		return lPersonnels;
	}
	
	/* Créé par Maxime GAUTIER */
	public void ModificationMotPasse(Personnels personnel, String oldMotPasse, String newMotPasse) throws BLLException
	{
		if(isEmptyOrNull(newMotPasse))
		{
			throw new BLLException("(PersonnelsManager)Modifier : Le nouveau mot de passe ne peut pas être null.");
		}
		else if (isEmptyOrNull(oldMotPasse))
		{
			throw new BLLException("(PersonnelsManager)Modifier : L'ancien mot de passe ne peut pas être null.");
		}
		else
		{
			// Logique de modification en base via DAO
			
			// Si l'ancien MDP est correct
			if (oldMotPasse.equals(Cryptage.decrypt(personnel.getMotPasse())))
			{
				// Modification du mot de page de l'objet
				String newMDPcrypt = Cryptage.encrypt(newMotPasse);
				personnel.setMotPasse(newMDPcrypt);
				
				// Modification dans la BDD
				newMotPasse = Cryptage.encrypt(newMotPasse);
				try {
					personnelsDAO.update(personnel);
				} catch (DALException e) {
					e.printStackTrace();
				}			
			}
		}
	}
	
	/* Créé par Maxime GAUTIER */
	public boolean Supprimer(Personnels perso) throws BLLException
	{
		//Logique de suppression en base (Archive = 1) via DAO
		try {
			personnelsDAO.delete(perso);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;			
	}
	
	//************
	//UTILITAIRES
	//************	
	
	/* Créé par Erwin DUPUIS */
    private boolean isEmptyOrNull(String toTest)
    {
        if(toTest != null && !toTest.trim().isEmpty())
            return false;
        else
            return true;
    }
    
    /* Créé par Erwin DUPUIS */
    private boolean isNegativeInt(int toCheck)
    {
        if(toCheck >= 0)
            return false;
        else
            return true;
    }
    
    //endregion METHODS

    //region GET/SET
    
    /* Créé par Erwin DUPUIS */
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
