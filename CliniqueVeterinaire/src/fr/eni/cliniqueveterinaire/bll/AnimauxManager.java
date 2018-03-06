package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.dal.AnimauxDAO;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;

/* Créé par Erwin DUPUIS */
public class AnimauxManager 
{
    //region DECLARATION

	private static AnimauxDAO animauxDAO;

    //endregion DECLARATION

    //region CTOR

	private AnimauxManager() throws BLLException
	{
	}

    //endregion CTOR
    
    //region METHODS
    
	public static List<Animaux> selectAnimaux(int CodeClient) throws BLLException
	{
		List<Animaux> aRetourner = new ArrayList<Animaux>();
		
		if(isNegativeInt(CodeClient))
		{
			throw new BLLException("(AnimauxManager)SelectAnimaux : Le code client ne peut pas être null");
		}
		else
		{
			try 
			{
				aRetourner = getAnimauxDAO().selectAnimaux(CodeClient);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return aRetourner;
	}
	
	public static List<String> selectEspece() throws BLLException
	{
		try 
		{
			return getAnimauxDAO().selectEspeces();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
	}
	
	public static Animaux selectAnimal(int CodeAnimal) throws BLLException
	{
		Animaux aRetourner = null;
		
		if(isNegativeInt(CodeAnimal))
		{
			throw new BLLException("(AnimauxManager)SelectAnimal : Le code animal ne peut pas etre null");
		}
		else
		{
			try 
			{
				aRetourner = getAnimauxDAO().selectAnimal(CodeAnimal);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return aRetourner;
	}
	
	public static int ajouter(Animaux aAjouter) throws BLLException
	{
		int aRetourner = -1;
		
		if(aAjouter == null)
		{
			throw new BLLException("(AnimauxManager)Ajouter : l'object Animal ne peut pas être null");
		}
		else if(isEmptyOrNull(aAjouter.getNomAnimal()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : le nom de l'animal ne peut pas être null");
		}
		else if(isEmptyOrNull(aAjouter.getsexe()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : le sexe de l'animal ne peut pas être null");
		}
		else if(aAjouter.getsexe().length() > 1)
		{
			throw new BLLException("(AnimauxManager)Ajouter : le sexe de l'animal ne peut pas excéder 1 caractère (M,F)");
		}
		else if(isEmptyOrNull(aAjouter.getRace()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : la race de l'animal ne peut pas être null");
		}
		else if(isEmptyOrNull(aAjouter.getEspece()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : l'espece de l'animal ne peut pas être null");
		}
		else if(isNegativeInt(aAjouter.getCodeClient()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : le code client du propriètaire de l'animal ne peut pas être null");
		}
		else if(verifieSiExiste(aAjouter.getCodeClient(), aAjouter.getNomAnimal()))
		{
			throw new BLLException("Un animal de ce nom est déjà associé à ce client");
		}
		else if(!isEmptyOrNull(aAjouter.gettatouage()) && aAjouter.gettatouage().length() > 10)
		{
			throw new BLLException("(AnimauxManager)Ajouter : la longueur du tatouage de l'animal ne peut excéder 10 caracteres");
		}
		else if(!isEmptyOrNull(aAjouter.getCouleur()) && aAjouter.getCouleur().length() > 20)
		{
			
		}
		else
		{
			try 
			{
				aRetourner = getAnimauxDAO().ajouter(aAjouter);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return aRetourner;
	}
	
	public static boolean supprimer(int CodeAnimal) throws BLLException
	{
		boolean aRetourner = false;
		
		if(isNegativeInt(CodeAnimal))
		{
			throw new BLLException("(AnimauxManager)Supprimer : La code animal à supprimer ne peut pas etre null");
		}
		else
		{
			try 
			{
				aRetourner = getAnimauxDAO().supprimer(CodeAnimal);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return aRetourner;
	}

	public static boolean modifier(Animaux aModifier) throws BLLException
	{
		boolean aRetourner = false;
		
		if(aModifier == null)
		{
			throw new BLLException("(AnimauxManager)Ajouter : l'object Animal ne peut pas être null");
		}
		else if(isNegativeInt(aModifier.getCodeAnimal()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : le code animal ne peut pas être null");
		}
		else if(isEmptyOrNull(aModifier.getNomAnimal()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : le nom de l'animal ne peut pas être null");
		}
		else if(isEmptyOrNull(aModifier.getsexe()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : le sexe de l'animal ne peut pas être null");
		}
		else if(isEmptyOrNull(aModifier.getRace()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : la race de l'animal ne peut pas être null");
		}
		else if(isEmptyOrNull(aModifier.getEspece()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : l'espece de l'animal ne peut pas être null");
		}
		else if(isNegativeInt(aModifier.getCodeClient()))
		{
			throw new BLLException("(AnimauxManager)Ajouter : le code client du propriètaire de l'animal ne peut pas être null");
		}
		else
		{
			try 
			{
				aRetourner = getAnimauxDAO().modifier(aModifier);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}
		
		return aRetourner;
	}
	
	public static boolean verifieSiExiste(int codeClient, String nomAnimal) throws BLLException
	{
		boolean aRetourner = false;
		
		if(isNegativeInt(codeClient))
		{
			throw new BLLException("Le code client ne peut pas être négatif");
		}
		else if(isEmptyOrNull(nomAnimal))
		{
			throw new BLLException("Le nom de l'animal ne peut pas être null ni vide");
		}
		else
		{
			try 
			{
				aRetourner = getAnimauxDAO().verifieSiExiste(codeClient, nomAnimal);
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

    public static AnimauxDAO getAnimauxDAO() throws BLLException
    {
    	if(AnimauxManager.animauxDAO == null)
    	{
    		try 
    		{
				AnimauxManager.animauxDAO = DAOFactory.getAnimauxDAO();
			} 
    		catch (DALException e) 
    		{
				throw new BLLException(e.getMessage());
			}
    	}
    	
    	return AnimauxManager.animauxDAO;
    }

    //endregion GET/SET
}
