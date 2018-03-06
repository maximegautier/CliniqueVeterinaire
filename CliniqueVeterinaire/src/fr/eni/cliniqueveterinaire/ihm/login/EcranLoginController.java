/* Fait par Maxime */

package fr.eni.cliniqueveterinaire.ihm.login;

import javax.swing.JOptionPane;

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
	
	// Permet d'afficher l'écran menu si les identifiants sont OK
	public void connexion(String login, String password)
	{
		// Recuperation du personnel connecté
		currentPersonnel = PersonnelsManager.authentification(login,password);
		
		if (currentPersonnel == null)
		{
			// Si identifiants KO, affichage du message d'erreur
			JOptionPane.showMessageDialog(null, "Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else {
			// Sinon
			fenLogin.dispose();
			fenMenu = new EcranMenu();
			fenMenu.setVisible(true);
		}
	}
	
	public void deconnexion() {
		fenMenu.dispose();
		fenLogin.getTxtLogin().setText("");
		fenLogin.getTxtPassword().setText("");
		fenLogin.getTxtLogin().requestFocusInWindow();
		fenLogin.setVisible(true);	
	}

	public static Personnels getCurrentPersonnel() {
		return currentPersonnel;
	}	
	
}
