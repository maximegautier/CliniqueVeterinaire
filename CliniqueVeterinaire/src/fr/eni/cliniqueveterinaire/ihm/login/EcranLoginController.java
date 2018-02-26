package fr.eni.cliniqueveterinaire.ihm.login;

import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class EcranLoginController {
	
	private static EcranLoginController instance;
	EcranLogin fenLogin;
	EcranMenu fenMenu;

	private EcranLoginController(){
		
	}
	
	public static EcranLoginController getInstance(){
		if ( EcranLoginController.instance == null){
			EcranLoginController.instance = new EcranLoginController();
		}
		return EcranLoginController.instance;
	}
	
	public void startApp(){
		fenLogin = new EcranLogin();
		fenLogin.setVisible(true);
	}
	
	public void Connexion(){
		System.out.println("Login : " + fenLogin.getTxtLogin().getText());
		System.out.println("Password : " + fenLogin.getTxtPassword().getText());
		//fenLogin.lError.setVisible(true);
		fenLogin.dispose();
		fenMenu = new EcranMenu();
		fenMenu.setVisible(true);
	}
	
}
