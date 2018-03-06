/* Fait par Maxime */

package fr.eni.cliniqueveterinaire.ihm.login;

import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class EcranLoginController
{
	
	private static EcranLoginController instance;
	private EcranLogin fenLogin;
	private EcranMenu fenMenu;
	private static Personnels currentPersonnel;

	private EcranLoginController()
	{
		
	}
	
	public static EcranLoginController getInstance()
	{
		if ( EcranLoginController.instance == null)
		{
			EcranLoginController.instance = new EcranLoginController();
		}
		return EcranLoginController.instance;
	}
	
	public void startApp() throws DALException
	{
		fenLogin = EcranLogin.getInstance();
		fenLogin.setVisible(true);
	}
	
	public void connexion(String login, String password)
	{
		currentPersonnel = PersonnelsManager.authentification(login,password);
		
		if (currentPersonnel == null)
		{
			fenLogin.getlError().setVisible(true);
		} else {
			fenLogin.dispose();
			fenMenu = new EcranMenu();
			fenMenu.setVisible(true);
		}
	}
	
	public void deconnexion() {
		fenMenu.dispose();
	}

	public static Personnels getCurrentPersonnel() {
		return currentPersonnel;
	}	
	
}
