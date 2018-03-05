package fr.eni.cliniqueveterinaire.ihm.clients;

import javax.swing.SwingUtilities;

import fr.eni.cliniqueveterinaire.bll.BLLException;



public class testEcranClients {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
					try {
						EcranClientsController.getInstance().startApp(EcranClients.getInstance());
					} catch (BLLException e) {
						e.printStackTrace();
					}
			}	
		});
	}
}
