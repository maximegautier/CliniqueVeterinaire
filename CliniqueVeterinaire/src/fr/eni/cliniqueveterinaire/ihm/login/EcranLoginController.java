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
		/* Modifi� pour commencer sur fenMenu */
		fenLogin = fenLogin.getInstance();
		fenLogin.setVisible(true);
	}
	
	public void connexion(String login, String password) throws DALException
	{
		Personnels persoConnect = PersonnelsManager.Authentification(login,password);
		
		if (persoConnect == null)
		{
			fenLogin.getlError().setVisible(true);
		} else {
			fenLogin.setVisible(false);
			fenMenu = EcranMenu.getInstance();
		}
	}	
}
