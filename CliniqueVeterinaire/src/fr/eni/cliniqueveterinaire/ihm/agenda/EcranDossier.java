package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.eni.cliniqueveterinaire.bo.Animaux;

public class EcranDossier extends JFrame{

	private JPanel panelDossier, panelHead, panelInfo, panelAntecedent, panelInfoClient, panelInfoAnimal;
	private JButton btnValider, btnAnnuler;
	private GridBagConstraints gbc;
	
	private Animaux animal;
	
	public EcranDossier(Animaux animal) {
		this.animal = animal;
		
		setSize(500, 400);
		setResizable(false);
		setTitle("Dossier Medical");
		setContentPane(getPanelDossier());
		setVisible(true);
		setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("ressources/ico_veto.png").getImage());
	}
	
	private JPanel getPanelDossier() {
		if (panelDossier == null) {
			panelDossier = new JPanel();
			panelDossier.setLayout(new GridBagLayout());
			gbc = new GridBagConstraints();	
			gbc.insets = new Insets(5, 5, 5, 5);
			
			// Head
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelDossier.add(getPanelHead(), gbc);

			
			// Info animal - client
			gbc.gridx = 0;
			gbc.gridy = 1;
			panelDossier.add(getPanelInfo(), gbc);

			
		}
		return panelHead;
	}
	
	private JPanel getPanelHead() {
		if (panelHead == null) {
			panelHead = new JPanel();
			panelHead.setBorder(BorderFactory.createTitledBorder("De"));
			panelHead.setLayout(new FlowLayout(FlowLayout.RIGHT));
			panelHead.setOpaque(true);
			panelHead.add(getBtnValider());
			panelHead.add(getBtnAnnuler());
		}
		return panelHead;
	}
	
	private JPanel getPanelInfo() {
		if (panelInfo == null) {
			panelInfo = new JPanel();
			panelInfo.add(getPanelInfoClient());
			panelInfo.add(getPanelInfoAnimal());
		}
		return panelInfo;
	}
	
	private JPanel getPanelInfoClient() {
		if (panelInfoClient == null) {
			panelInfoClient = new JPanel();
			panelInfoClient.setBorder(BorderFactory.createTitledBorder("Client"));
			panelInfoClient.add(new JLabel("client ..."));
		}
		return panelInfoClient;
	}
	
	
	private JPanel getPanelInfoAnimal() {
		if (panelInfoAnimal == null) {
			panelInfoAnimal = new JPanel();
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			
			// Code animal
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelInfoAnimal.add(new JLabel("Animal :"), gbc);
			gbc.gridx = 1;
			panelInfoAnimal.add(new JLabel(Integer.toString(animal.getCodeAnimal())));
			
			// Nom animal
			gbc.gridx = 1;
			gbc.gridy = 1;
			panelInfoAnimal.add(new JLabel(animal.getNomAnimal()));
			
			// Couleur + sexe animal
			gbc.gridy = 2;
			panelInfoAnimal.add(new JLabel(animal.getCouleur() + "  " + animal.getsexe()));
			
			// Espece + race animal
			gbc.gridy = 3;
			panelInfoAnimal.add(new JLabel(animal.getEspece() + " " + animal.getRace()));
			
			// Tatouage animal
			gbc.gridy = 4;
			panelInfoAnimal.add(new JLabel(animal.gettatouage()));
		}
		return panelInfoClient;
	}
	
	
	public JButton getBtnValider()
	{
		if (btnValider == null)
		{
			btnValider = new JButton("Valider",new ImageIcon("ressources/icons8-coche-32.png"));
			btnValider.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
		}
		return btnValider;
	}
	
	public JButton getBtnAnnuler()
	{
		if (btnAnnuler == null)
		{
			btnAnnuler = new JButton("Annuler",new ImageIcon("ressources/icons8-effacer-32.png"));
			btnAnnuler.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			});
		}
		return btnAnnuler;
	}
	
}
