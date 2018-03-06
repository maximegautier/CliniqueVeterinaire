package fr.eni.cliniqueveterinaire.ihm.animal;

import java.util.List;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.bll.RacesManager;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Races;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;

/* Créé par Erwin DUPUIS */
public class EcranAnimalController 
{
    //region DECLARATION


    //endregion DECLARATION

    //region CTOR

	private EcranAnimalController()
	{	
	}

    //endregion CTOR
    
    //region METHODS    
		
	public static Animaux selectAnimal(int codeAnimal) throws BLLException
	{
		return AnimauxManager.selectAnimal(codeAnimal);		
	}
	
	public static List<String> selectEspeces() throws BLLException
	{
		return AnimauxManager.selectEspece();
	}
    
	public static List<Races> selectRaces(String espece) throws BLLException
	{
		return RacesManager.selectRaces(espece);
	}
	
	public static Clients selectClientParCode(int codeClient) throws BLLException
	{
		return ClientsManager.getInstance().selectById(codeClient);
	}
	
	public static boolean modifier(Animaux aModifier) throws BLLException
	{
		return AnimauxManager.modifier(aModifier);
	}
	
	public static int ajouter(Animaux aAjouter) throws BLLException
	{
		return AnimauxManager.ajouter(aAjouter);
	}
	
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
