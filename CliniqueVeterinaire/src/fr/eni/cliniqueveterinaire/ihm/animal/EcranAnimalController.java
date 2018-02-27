package fr.eni.cliniqueveterinaire.ihm.animal;

import java.util.List;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Animaux;
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
	
	public Animaux SelectAnimal(int codeAnimal) throws BLLException
	{
		tmpAnimal = AnimauxManager.getInstance().SelectAnimal(codeAnimal);
		
		return tmpAnimal;
	}
	
	public void RemplirChamps()
	{
		fenAnimal.getTFdCode().setText(String.valueOf(tmpAnimal.getCodeAnimal()));
		fenAnimal.getTfdNom().setText(tmpAnimal.getNomAnimal());
		fenAnimal.getCbSexe().setSelectedItem(tmpAnimal.getSexe());
		if(tmpAnimal.getCouleur() != null)
		{
			fenAnimal.getTfdCouleur().setText(tmpAnimal.getCouleur());
		}
		fenAnimal.getCbEspece().setSelectedItem(tmpAnimal.getEspece());
		fenAnimal.getCbRace().setSelectedItem(tmpAnimal.getRace());
		if(tmpAnimal.getTatouage() != null)
		{
			fenAnimal.getTfdTatouage().setText(tmpAnimal.getTatouage());
		}	
	}
    
	public List<Races> SelectRacesChien()
	{
		return null;
	}
	
	public List<Races> SelectRacesChat()
	{
		return null;
	}
	
	public boolean Modifier(Animaux aModifier) throws BLLException
	{
		return AnimauxManager.getInstance().Modifier(aModifier);
	}
	
	public int Ajouter(Animaux aAjouter) throws BLLException
	{
		return AnimauxManager.getInstance().Ajouter(aAjouter);
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
