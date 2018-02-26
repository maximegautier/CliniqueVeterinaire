package fr.eni.cliniqueveterinaire.ihm.menu.gdp;

import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

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
	
	public void Ajouter(){
		System.out.println("Ajouter");
	}
	
	public void Supprimer(){
		System.out.println("Supprimer");
	}
	
	public void Reinitialiser(){
		System.out.println("Reinitialiser");
	}
	
}
