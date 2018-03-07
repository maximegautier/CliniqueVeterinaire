/* Fait par Maxime */

package fr.eni.cliniqueveterinaire.ihm.login;

import java.util.logging.Level;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;
import fr.eni.cliniqueveterinaire.log.LogFactory;

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
	
	// Permet d'afficher l'écran menu si les identifiants sont OK
	public Personnels connexion(String login, String password) throws BLLException
	{
		// Recuperation du personnel connecté
		currentPersonnel =  PersonnelsManager.authentification(login,password);
		return currentPersonnel;
	}
	
	public void affichageMenu() {
		fenLogin.dispose();
		fenMenu = new EcranMenu();
		fenMenu.setVisible(true);
	}
	
	public void deconnexion() {
		LogFactory.getLog().createLog(Level.INFO, "Déconnexion : " + currentPersonnel.getDisplayName());
		fenMenu.dispose();
		fenLogin.getTxtLogin().setText("");
		fenLogin.getTxtPassword().setText("");
		fenLogin.getTxtLogin().requestFocusInWindow();
		fenLogin.setVisible(true);
	}
	
	public void fermeture() {
		LogFactory.getLog().createLog(Level.INFO, "Déconnexion : " + currentPersonnel.getDisplayName());
		fenMenu.dispose();
		System.exit(0);
	}

	public static Personnels getCurrentPersonnel() {
		return currentPersonnel;
	}	
	
}
