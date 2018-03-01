package fr.eni.cliniqueveterinaire.ihm.animal;

import java.util.List;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.RacesManager;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Races;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;

/* Créé par Erwin DUPUIS */
public class EcranAnimalController 
{
    //region DECLARATION

	private static EcranAnimalController instance;
	private EcranAnimal fenAnimal;
	private Animaux tmpAnimal;

    //endregion DECLARATION

    //region CTOR

	private EcranAnimalController()
	{	
	}

    //endregion CTOR
    
    //region METHODS    
	
	public void startApp()
	{
		fenAnimal = new EcranAnimal();
		fenAnimal.setVisible(true);
	}
	
	public void remplirChamps()
	{
		fenAnimal.getTFdCode().setText(String.valueOf(tmpAnimal.getCodeAnimal()));
		fenAnimal.getTfdNom().setText(tmpAnimal.getNomAnimal());
		fenAnimal.getCbSexe().setSelectedItem(tmpAnimal.getsexe());
		if(tmpAnimal.getCouleur() != null)
		{
			fenAnimal.getTfdCouleur().setText(tmpAnimal.getCouleur());
		}
		fenAnimal.getCbEspece().setSelectedItem(tmpAnimal.getEspece());
		fenAnimal.getCbRace().setSelectedItem(tmpAnimal.getRace());
		if(tmpAnimal.gettatouage() != null)
		{
			fenAnimal.getTfdTatouage().setText(tmpAnimal.gettatouage());
		}	
	}
	
	public Animaux selectAnimal(int codeAnimal) throws BLLException
	{
		tmpAnimal = AnimauxManager.selectAnimal(codeAnimal);
		
		return tmpAnimal;
	}
	
	public List<String> selectEspeces() throws BLLException
	{
		return AnimauxManager.selectEspece();
	}
    
	public List<Races> selectRacesChien() throws BLLException
	{
		return RacesManager.selectRacesChien();
	}
	
	public List<Races> selectRacesChat() throws BLLException
	{
		return RacesManager.selectRacesChat();
	}
	
	public Clients selectClientParCode(int codeClient)
	{
		return null;
	}
	
	public boolean modifier(Animaux aModifier) throws BLLException
	{
		return AnimauxManager.modifier(aModifier);
	}
	
	public int ajouter(Animaux aAjouter) throws BLLException
	{
		return AnimauxManager.ajouter(aAjouter);
	}
	
    //endregion METHODS

    //region GET/SET

	public static EcranAnimalController getInstance()
	{
		if(EcranAnimalController.instance == null)
		{
			EcranAnimalController.instance = new EcranAnimalController();
		}
		return EcranAnimalController.instance;
	}

    //endregion GET/SET
}
