package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class PanAgenda extends JPanel{

	private static PanAgenda instance;
	private JPanel panelHead;
	
	
	public static PanAgenda getInstance()
	{
		if (PanAgenda.instance == null)
		{
			instance = new PanAgenda();
		}
		return instance;
	}
	
	private PanAgenda()
	{
		setPreferredSize(new Dimension(700,500));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		add(getPanelHead(),gbc);
	}

	
	private JPanel getPanelHead(){
		if (panelHead == null)
		{
			panelHead = new JPanel();
			panelHead.setBorder(BorderFactory.createTitledBorder("De"));
			panelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
			panelHead.setOpaque(true);
			panelHead.setPreferredSize(new Dimension(this.getPreferredSize().width -15,60));
			panelHead.add(new JLabel("Veterinaire :"));

		}
		return panelHead;
	}
	
}
