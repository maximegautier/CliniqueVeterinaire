package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.awt.Button;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class DialogAdd{

	private JDialog dialogAjouter;
	private JPanel panelAdd;

	public DialogAdd() throws DALException
	{
		dialogAjouter = new JDialog(EcranMenu.getInstance());
		dialogAjouter.setSize(300, 180);
		dialogAjouter.setResizable(false);
		dialogAjouter.setTitle("Ajouter un personnel");
		dialogAjouter.setModal(true);
		dialogAjouter.setLocationRelativeTo(null);
		dialogAjouter.setVisible(true);
		
	}

	private JPanel getPanAdd(){
		return panelAdd;
		//panelAdd = new JP
	}
	
	
}
