package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class DialogAdd{

	private JDialog dialogAjouter;
	private JPanel panelAdd;
	private JTextField tfNom, tfPrenom, tfMDP, tfLogin;
	private JComboBox<String> cRole;
	private JButton bValider, bAnnuler;

	public DialogAdd() throws DALException
	{
		dialogAjouter = new JDialog();
		dialogAjouter.setSize(300, 300);
		dialogAjouter.setResizable(false);
		dialogAjouter.setTitle("Ajouter un personnel");
		dialogAjouter.setModal(true);
		dialogAjouter.add(getPanAdd());
		dialogAjouter.setLocationRelativeTo(null);
		dialogAjouter.setVisible(true);
		
	}

	private JPanel getPanAdd() throws DALException{
		panelAdd = new JPanel();
		panelAdd.setOpaque(true);
		
		panelAdd.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();	
		gbc.insets = new Insets(5, 5, 13, 5);
		gbc.anchor = GridBagConstraints.WEST;
		
		// Nom
		gbc.gridx=0;
		gbc.gridy=0;
		panelAdd.add(new JLabel("Nom * :", JLabel.LEFT),gbc);
		gbc.gridx = 1;
		panelAdd.add(getTfNom(),gbc);
		
		// Prenom
		gbc.gridx=0;
		gbc.gridy=1;
		panelAdd.add(new JLabel("Prenom * :"),gbc);
		gbc.gridx = 1;
		panelAdd.add(getTfPrenom(),gbc);
		
		// Login
		gbc.gridx=0;
		gbc.gridy=2;
		panelAdd.add(new JLabel("Login * :"),gbc);
		gbc.gridx = 1;
		panelAdd.add(getTfLogin(),gbc);
		
		// Role
		gbc.gridx=0;
		gbc.gridy=3;
		panelAdd.add(new JLabel("Role * :"),gbc);
		gbc.gridx = 1;
		panelAdd.add(getCRole(),gbc);
		
		// Mot de passe
		gbc.gridx=0;
		gbc.gridy=4;
		panelAdd.add(new JLabel("Mot de passe * :"),gbc);
		gbc.gridx = 1;
		panelAdd.add(getTfMDP(),gbc);
		
		// Champs obligatoire
		gbc.insets = new Insets(5, 0, 5, 0);
		gbc.gridx=0;
		gbc.gridy=5;
		gbc.gridwidth = 2;
		panelAdd.add(new JLabel("* Champs obligatoires"),gbc);
		
		// Boutons Valider et Annuler
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx=0;
		gbc.gridy=6;
		gbc.gridwidth = 2;
		panelAdd.add(panelBtn(),gbc);
		
		return panelAdd;
	}
	
	// Panel affichage des boutons Valider et Annuler
	public JPanel panelBtn(){
		JPanel panelBtn = new JPanel();
		panelBtn.add(getbValider());
		panelBtn.add(getbAnnuler());
		return panelBtn;
	}
	
	public JTextField getTfNom(){
		if (tfNom == null)
		{
			tfNom = new JTextField(15);
		}
		return tfNom;
	}
	
	public JTextField getTfPrenom(){
		if (tfPrenom == null)
		{
			tfPrenom = new JTextField(15);
		}
		return tfPrenom;
	}
	
	public JTextField getTfMDP(){
		if (tfMDP == null)
		{
			tfMDP = new JTextField(15);
		}
		return tfMDP;
	}
	
	public JTextField getTfLogin(){
		if (tfLogin == null)
		{
			tfLogin = new JTextField(15);
		}
		return tfLogin;
	}
	
	public JComboBox<String> getCRole() throws DALException{
		if (cRole == null)
		{
			cRole = new JComboBox<String>();
			cRole.setModel(new DefaultComboBoxModel(PanGDPController.getInstance().selectTousRoles().toArray()));
		}
		return cRole;
	}
	
	public JButton getbValider(){
		if (bValider == null)
		{
			bValider = new JButton("Valider");
			bValider.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try {
						PanGDPController.getInstance().validerAjout(
								getTfNom().getText(),
								getTfPrenom().getText(),
								getTfLogin().getText(),
								(String) getCRole().getSelectedItem(),
								getTfMDP().getText()
								);
						dialogAjouter.dispose();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return bValider;
	}
	
	public JButton getbAnnuler(){
		if (bAnnuler == null)
		{
			bAnnuler = new JButton("Annuler");
			bAnnuler.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					dialogAjouter.dispose();
				}
			});
		}
		return bAnnuler;
	}
	
}
