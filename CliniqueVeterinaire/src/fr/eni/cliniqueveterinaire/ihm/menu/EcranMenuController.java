package fr.eni.cliniqueveterinaire.ihm.menu;

import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;

public class EcranMenuController {
	
	private static EcranMenuController instance;
	EcranLogin fenLogin = EcranLogin.getInstance();
	EcranMenu fenMenu = EcranMenu.getInstance();

	private EcranMenuController(){
		
	}
	
	public static EcranMenuController getInstance(){
		if ( EcranMenuController.instance == null){
			EcranMenuController.instance = new EcranMenuController();
		}
		return EcranMenuController.instance;
	}

	public void Deconnexion(){
		fenMenu.dispose();
		fenLogin.getTxtLogin().setText("");
		fenLogin.getTxtPassword().setText("");
		fenLogin.getlError().setVisible(false);
		fenLogin.getTxtLogin().requestFocusInWindow();
		fenLogin.setVisible(true);
	}
	
	public void Fermer(){
		System.exit(0);
	}
	
}
