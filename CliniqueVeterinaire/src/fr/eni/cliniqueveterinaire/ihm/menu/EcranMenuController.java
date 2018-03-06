package fr.eni.cliniqueveterinaire.ihm.menu;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLoginController;

public class EcranMenuController {

	private EcranMenuController() throws DALException{
		
	}
	

	public static void Deconnexion(){
		EcranLoginController.getInstance().deconnexion();
		EcranLogin.getInstance().getTxtLogin().setText("");
		EcranLogin.getInstance().getTxtPassword().setText("");
		EcranLogin.getInstance().getlError().setVisible(false);
		EcranLogin.getInstance().getTxtLogin().requestFocusInWindow();
		EcranLogin.getInstance().setVisible(true);
	}
	
}
