package fr.eni.cliniqueveterinaire.ihm.menu;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;

public class EcranMenuController {
	
	private static EcranMenuController instance;
	EcranLogin fenLogin;
	EcranMenu fenMenu;

	private EcranMenuController() throws DALException{
		fenLogin = EcranLogin.getInstance();
	}
	
	public static EcranMenuController getInstance(){
		if ( EcranMenuController.instance == null){
			try {
				EcranMenuController.instance = new EcranMenuController();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return EcranMenuController.instance;
	}

	public void Deconnexion(){
		fenLogin.getTxtLogin().setText("");
		fenLogin.getTxtPassword().setText("");
		fenLogin.getlError().setVisible(false);
		fenLogin.getTxtLogin().requestFocusInWindow();
		fenLogin.setVisible(true);
	}
	
}
