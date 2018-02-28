package fr.eni.cliniqueveterinaire.ihm.menu;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;

public class EcranMenuController {
	
	private static EcranMenuController instance;
	EcranLogin fenLogin;
	EcranMenu fenMenu;

	private EcranMenuController() throws DALException{
		fenMenu = EcranMenu.getInstance();
		fenLogin = EcranLogin.getInstance();
	}
	
	public static EcranMenuController getInstance() throws DALException{
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
