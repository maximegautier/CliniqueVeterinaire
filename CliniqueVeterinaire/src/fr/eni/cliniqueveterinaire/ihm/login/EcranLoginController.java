/* Fait par Maxime */

package fr.eni.cliniqueveterinaire.ihm.login;

import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Personnels;
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
	
	public void startApp()
	{
		/* Modifié pour commencer sur fenMenu */
		fenMenu = fenMenu.getInstance();
		fenMenu.setVisible(true);
	}
	
	public void Connexion()
	{
		Personnels persoConnect = PersonnelsManager.getInstance().Authentification(fenLogin.getTxtLogin().getText(), fenLogin.getTxtPassword().getText());
		
		if (persoConnect == null)
		{
			fenLogin.getlError().setVisible(true);
		} else {
			fenLogin.setVisible(false);
			fenMenu = EcranMenu.getInstance();
			fenMenu.setVisible(true);
		}
	}	
}
