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

	private static PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();

    //endregion DECLARATION

    //region CTOR
	
	/* Créé par Maxime GAUTIER */
	private PersonnelsManager()
	{
		
	}

    //endregion CTOR
    
    //region METHODS
	/* Créé par Maxime GAUTIER */
	public static Personnels authentification(String nom, String motPasse)
	{
		Personnels personnel = null;
		try {
			personnel = ((PersonnelsDAOJdbcImpl) personnelsDAO).checkConnexion(nom, motPasse);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	/* Créé par Maxime GAUTIER */
	public static int ajouter(Personnels personnel) throws BLLException
	{
		try {
			if(personnel == null)
			{
				throw new BLLException("On ne peut pas ajouter un personnel null.");
			}			
			else if(isEmptyOrNull(personnel.getNom()))
			{
				throw new BLLException("On ne peut pas ajouter un personnel avec un nom null.");
			}
			else if(verifieSiExiste(personnel.getNom()))
			{
				throw new BLLException("Il existe un personnel du meme nom.");
			}
			else if(personnel.getNom().length()> 30)
			{
				throw new BLLException("La taille du nom depasse 30 caracteres.");
			}
			else if(isEmptyOrNull(personnel.getMotPasse()))
			{
				throw new BLLException("On ne peut pas ajouter un personnel avec un mot de passe null.");
			}
			else if(personnel.getMotPasse().length()>10)
			{
				throw new BLLException("La taille du mot de passe depasse 10 caracteres.");
			}
			else if(isEmptyOrNull(personnel.getRole()))
			{
				throw new BLLException("On ne peut pas ajouter un personnel avec un role null.");
			}
			else if(personnel.getRole().length() > 3)
			{
				throw new BLLException("Le role est définie par : vet, adm... et ne peut pas excéder 3 caractères");
			} 
			else
			{
				//Logique d'ajout à la base via DAO
				personnel.setCodePers(personnelsDAO.insert(personnel));
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage());
		}				
		return personnel.getCodePers();
	}
	
	/* Créé par Maxime GAUTIER */
	public static List<Personnels> selectTousPersonnels() throws DALException
	{
		List<Personnels> lPersonnels = null;
		lPersonnels = personnelsDAO.selectAll();
		return lPersonnels;
	}
	
	/* Créé par Maxime GAUTIER */
	public static List<String> selectTousRoles() throws BLLException
	{
		List<String> lRole = null;
		try {
			lRole = personnelsDAO.selectRole();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage());
		}
		return lRole;
	}
	
	/* Créé par Maxime GAUTIER */
	public static List<Personnels> selectTousVeterinaires() throws BLLException
	{
		List<Personnels> lVeterinaires = null;
		try {
			lVeterinaires = personnelsDAO.selectVeterinaire();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage());
		}
		return lVeterinaires;
	}
	
	/* Créé par Maxime GAUTIER */
	public static void modificationMotPasse(Personnels personnel, String oldMotPasse, String newMotPasse) throws BLLException
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
			if (oldMotPasse.equals(personnel.getMotPasse()))
			{
				// Modification du mot de page de l'objet
				personnel.setMotPasse(newMotPasse);
				
				// Modification dans la BDD
				try {
					personnelsDAO.update(personnel);
				} catch (DALException e) {
					throw new BLLException(e.getMessage());
				}			
			}
			else if (!oldMotPasse.equals(personnel.getMotPasse()))
			{
				throw new BLLException("L'ancien mot de passe est incorrect !");
			}
		}
	}
	
	/* Créé par Maxime GAUTIER */
	public static boolean supprimer(Personnels perso) throws BLLException
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
	
	public static boolean verifieSiExiste(String nomPersonnel) throws BLLException
	{
		boolean aRetourner = false; 
		
		if(isEmptyOrNull(nomPersonnel))
		{
			throw new BLLException("Le nom du personnel ne peut pas etre null.");
		}
		else
		{
			try 
			{
				aRetourner = personnelsDAO.verifieSiExiste(nomPersonnel);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return aRetourner;
	}
	
	public Personnels selectPersonnel(String nom)
	{
		Personnels tmp = null;
		try {
			tmp = personnelsDAO.selectByName(nom);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	
	
	
	//************
	//UTILITAIRES
	//************	
	
	/* Créé par Erwin DUPUIS */
    private static boolean isEmptyOrNull(String toTest)
    {
        if(toTest != null && !toTest.trim().isEmpty())
            return false;
        else
            return true;
    }
    
    /* Créé par Erwin DUPUIS */
    private static boolean isNegativeInt(int toCheck)
    {
        if(toCheck >= 0)
            return false;
        else
            return true;
    }
    
    //endregion METHODS

}
