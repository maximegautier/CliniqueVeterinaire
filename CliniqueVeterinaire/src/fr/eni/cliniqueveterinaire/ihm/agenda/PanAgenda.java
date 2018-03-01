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

public class PanAgenda {

	private static PanAgenda instance;
	private JPanel panelAgenda;
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
		
	}
	
	public JPanel getPanAgenda() throws DALException{
		if (panelAgenda == null){
			panelAgenda = new JPanel();
			panelAgenda.setPreferredSize(new Dimension(EcranMenu.getInstance().getWidth(),EcranMenu.getInstance().getHeight()));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			
			panelAgenda.add(getPanelHead(),gbc);
		}
		return panelAgenda;
	}
	
	public JPanel getPanelHead(){
		if (panelHead == null)
		{
			panelHead = new JPanel();
			panelHead.setBorder(BorderFactory.createTitledBorder("De"));
			panelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
			panelHead.setOpaque(true);
			try {
				panelHead.setPreferredSize(new Dimension(getPanAgenda().getPreferredSize().width -15,60));
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			panelHead.add(new JLabel("Veterinaire :"));

		}
		return panelHead;
	}
	
}
