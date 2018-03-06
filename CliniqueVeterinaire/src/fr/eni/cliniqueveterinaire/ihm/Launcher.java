package fr.eni.cliniqueveterinaire.ihm;

import javax.swing.SwingUtilities;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLoginController;
import fr.eni.cliniqueveterinaire.log.LogFactory;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
					try {
						LogFactory.getLog().init();
						EcranLoginController.getInstance().startApp();
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}	
		});
		
	}
}
