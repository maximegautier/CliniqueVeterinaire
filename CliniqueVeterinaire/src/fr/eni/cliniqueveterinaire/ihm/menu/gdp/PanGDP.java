package fr.eni.cliniqueveterinaire.ihm.menu.gdp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanGDP {

	private static PanGDP instance;
	private JPanel panelGDP, panelHead;
	private JButton bAjouter, bSupprimer, bReinitialiser;
	
	
	public static PanGDP getInstance(){
		if (PanGDP.instance == null){
			instance = new PanGDP();
		}
		return instance;
	}
	
	private PanGDP() {
		
	}
	
	public JPanel getPanGDP(){
		if (panelGDP == null){
			panelGDP = new JPanel();

			panelGDP.add(getPanelHead());
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
			panelHead.add(getbAjouter());
			panelHead.add(getbSupprimer());
			panelHead.add(getbReinitialiser());
		}
		return panelHead;
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
	
}
