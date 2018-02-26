package fr.eni.cliniqueveterinaire.ihm;

import javax.swing.SwingUtilities;

import fr.eni.cliniqueveterinaire.ihm.login.EcranLoginController;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
					EcranLoginController.getInstance().startApp();
			}
			
		});
		
	}
}
