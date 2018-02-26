/* Fait par Maxime */

package fr.eni.cliniqueveterinaire.ihm.login;

import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class EcranLoginController {
	
	private static EcranLoginController instance;
	private EcranLogin fenLogin;
	private EcranMenu fenMenu;

	private EcranLoginController(){
		
	}
	
	public static EcranLoginController getInstance(){
		if ( EcranLoginController.instance == null){
			EcranLoginController.instance = new EcranLoginController();
		}
		return EcranLoginController.instance;
	}
	
	public void startApp(){
		fenLogin = EcranLogin.getInstance();
		fenLogin.setVisible(true);
	}
	
	public void Connexion(){
		System.out.println("Login : " + fenLogin.getTxtLogin().getText());
		System.out.println("Password : " + fenLogin.getTxtPassword().getText());

		// Si utilisateur OK, Affichage du menu
		fenLogin.setVisible(false);
		fenMenu = EcranMenu.getInstance();
		fenMenu.setVisible(true);
		
		// Sinon
		//fenLogin.getlError().setVisible(true);
	}
	
}
