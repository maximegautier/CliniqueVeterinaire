package fr.eni.cliniqueveterinaire.ihm.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.ihm.animal.EcranAnimalController;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLoginController;

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
