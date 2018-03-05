package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JPanel;

import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;


public class EcranDossier extends JFrame{

	private JPanel panelDossier, panelHead, panelInfo, panelAntecedent;
	private JButton btnValider, btnAnnuler;
	private GridBagConstraints gbc;
	
	private Animaux animal;
	private Clients client;
	
	public EcranDossier(Animaux animal, Clients client) {
		this.animal = animal;
		this.client = client;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			panelDossier.setOpaque(true);
			gbc = new GridBagConstraints();	
			gbc.insets = new Insets(5, 5, 15, 5);
			gbc.anchor = GridBagConstraints.CENTER;
			
			// Head
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			panelDossier.add(getPanelHead(), gbc);
			gbc.gridwidth = 0;
			
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
			panelHead.setPreferredSize(new Dimension(this.getPreferredSize().width -15,30));
			panelHead.add(getBtnValider());
			panelHead.add(getBtnAnnuler());
		}
		return panelHead;
	}
	
	private JPanel getPanelInfo() {
		if (panelInfo == null) {
			panelInfo = new JPanel();
			
		}
		return panelInfo;
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
					
				}
			});
		}
		return btnAnnuler;
	}
	
}
