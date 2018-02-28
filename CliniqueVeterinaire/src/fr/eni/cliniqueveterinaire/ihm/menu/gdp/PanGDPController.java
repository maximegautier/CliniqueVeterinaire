package fr.eni.cliniqueveterinaire.ihm.menu.gdp;

import java.util.List;
import java.util.Vector;

import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;
import fr.eni.cliniqueveterinaire.ihm.personnels.DialogAdd;

public class PanGDPController {

	private static PanGDPController instance;
	private EcranLogin fenLogin;
	private EcranMenu fenMenu;
	private PanGDP panelGDP;

	private PanGDPController(){
		
	}
	
	public static PanGDPController getInstance(){
		if ( PanGDPController.instance == null){
			PanGDPController.instance = new PanGDPController();
		}
		return PanGDPController.instance;
	}
	
	public void Ajouter() throws DALException{
		System.out.println("Ajouter");
		DialogAdd jdAjouter = new DialogAdd();
	}
	
	public void Supprimer(){
		System.out.println("Supprimer");
	}
	
	public void Reinitialiser() throws DALException{
		System.out.println("Reinitialiser");
		
	}
	
	public Vector<Vector> completerTableau() throws DALException
	{
		List<Personnels> lPersonnels = PersonnelsManager.getInstance().selectTousPersonnels();
		
		Vector<Vector> vecRow = new Vector<Vector>();
		
		for (Personnels tmp : lPersonnels)
		{
			Vector<String> tmpVct = new Vector<String>();
			tmpVct.addElement(tmp.getNom());
			tmpVct.addElement(tmp.getRole());
			tmpVct.addElement(tmp.getMotPasse());
			
			vecRow.addElement(tmpVct);
		}	
		return vecRow;
	}
	
}
