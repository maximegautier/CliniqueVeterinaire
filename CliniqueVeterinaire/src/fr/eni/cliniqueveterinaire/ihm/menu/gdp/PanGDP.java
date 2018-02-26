package fr.eni.cliniqueveterinaire.ihm.menu.gdp;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanGDP {

	private static PanGDP instance;
	private JPanel panelGDP;
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

			panelGDP.setLayout(new FlowLayout());
			panelGDP.add(getbAjouter());
			panelGDP.add(getbSupprimer());
			panelGDP.add(getbReinitialiser());
			
		}
		return panelGDP;
	}
	
	public JButton getbAjouter(){
		if (bAjouter == null) {
			bAjouter = new JButton("Ajouter");
			bAjouter.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					PanGDPController.getInstance().Ajouter();
				}
			});
		}
		return bAjouter;
	}
	
	public JButton getbSupprimer(){
		if (bSupprimer == null) {
			bSupprimer = new JButton("Supprimer");
			bSupprimer.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					PanGDPController.getInstance().Supprimer();
				}
			});
		}
		return bSupprimer;
	}
	
	public JButton getbReinitialiser(){
		if (bReinitialiser == null) {
			bReinitialiser = new JButton("Reinitialiser");
			bReinitialiser.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					PanGDPController.getInstance().Reinitialiser();
				}
			});
		}
		return bReinitialiser;
	}
	
}
