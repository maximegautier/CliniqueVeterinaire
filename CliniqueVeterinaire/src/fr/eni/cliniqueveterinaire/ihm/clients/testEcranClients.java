package fr.eni.cliniqueveterinaire.ihm.clients;

import javax.swing.SwingUtilities;



public class testEcranClients {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
					try {
						EcranClientsController.getInstance().startApp();
					} catch (BLLException e) {
						e.printStackTrace();
					}
			}	
		});
	}
}
