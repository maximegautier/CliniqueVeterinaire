package fr.eni.cliniqueveterinaire.ihm.menu.gdp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class PanGDP {

	private static PanGDP instance;
	private JPanel panelGDP, panelHead, panelTable;
	private JButton bAjouter, bSupprimer, bReinitialiser;
	private JTable tPersonnel;
	
	public static PanGDP getInstance(){
		if (PanGDP.instance == null){
			instance = new PanGDP();
		}
		return instance;
	}
	
	private PanGDP() 
	{
		
	}
	
	public JPanel getPanGDP(){
		if (panelGDP == null){
			panelGDP = new JPanel();
			panelGDP.setPreferredSize(new Dimension(EcranMenu.getInstance().getWidth(),EcranMenu.getInstance().getHeight()));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.gridx =0;
			gbc.gridy =0;
			panelGDP.add(getPanelHead(),gbc);
			
			gbc.gridx =0;
			gbc.gridy =1;
			panelGDP.add(getPanelTable(),gbc);
		}
		return panelGDP;
	}
	
	private JPanel getPanelHead()
	{
		if (panelHead == null)
		{
			panelHead = new JPanel();
			panelHead.setBorder(BorderFactory.createLineBorder(Color.black));
			panelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
			panelHead.setOpaque(true);
			panelHead.setPreferredSize(new Dimension(getPanGDP().getPreferredSize().width -15,getbAjouter().getPreferredSize().height+12));
			panelHead.add(getbAjouter());
			panelHead.add(getbSupprimer());
			panelHead.add(getbReinitialiser());
		}
		return panelHead;
	}
	
	private JPanel getPanelTable()
	{
		if (panelTable == null)
		{
			panelTable = new JPanel();
			panelTable.setBorder(BorderFactory.createLineBorder(Color.black));
			panelTable.setOpaque(true);
			
			panelTable.add(getTablePersonnel(), BorderLayout.CENTER);

		}
		return panelTable;
	}
	
	public JButton getbAjouter()
	{
		if (bAjouter == null)
		{
			bAjouter = new JButton("Ajouter",new ImageIcon("ressources/icons8-add-user-male-32.png"));
			bAjouter.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					PanGDPController.getInstance().Ajouter();
				}
			});
		}
		return bAjouter;
	}
	
	public JButton getbSupprimer()
	{
		if (bSupprimer == null)
		{
			bSupprimer = new JButton("Supprimer",new ImageIcon("ressources/icons8-denied-32.png"));
			bSupprimer.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					PanGDPController.getInstance().Supprimer();
				}
			});
		}
		return bSupprimer;
	}
	
	public JButton getbReinitialiser()
	{
		if (bReinitialiser == null)
		{
			bReinitialiser = new JButton("Reinitialiser",new ImageIcon("ressources/icons8-reset-32.png"));
			bReinitialiser.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					PanGDPController.getInstance().Reinitialiser();
				}
			});
		}
		return bReinitialiser;
	}
	
	public JTable getTablePersonnel()
	{
		if (tPersonnel == null)
		{
			Vector<String> entetes = new Vector<String>();
			entetes.addElement("test");
			entetes.addElement("test");
			entetes.addElement("test");
			tPersonnel = new JTable(PanGDPController.getInstance().completerTableau(),entetes);
			
			
		}
		return tPersonnel;
	}
	
	
}
