package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.agenda.PanAgendaController;
import fr.eni.cliniqueveterinaire.ihm.agenda.TableAgendaVet;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class PanGDP extends JPanel{

	private static PanGDP instance;
	private JPanel panelHead, panelTable;
	private JButton bAjouter, bSupprimer, bReinitialiser;
	private TablePersonnels tablePersonnels;
	
	public static PanGDP getInstance()
	{
		if (PanGDP.instance == null)
		{
			PanGDP.instance = new PanGDP();
		}
		return PanGDP.instance;
	}
	
	private PanGDP()
	{
		setPreferredSize(new Dimension(700,500));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx =0;
		gbc.gridy =0;
		add(getPanelHead(),gbc);
		
		gbc.gridx =0;
		gbc.gridy =1;
		add(getPanelTable(),gbc);
	}
	
	private JPanel getPanelHead()
	{
		if (panelHead == null)
		{
			panelHead = new JPanel();
			panelHead.setBorder(BorderFactory.createLineBorder(Color.black));
			panelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
			panelHead.setOpaque(true);
			panelHead.setPreferredSize(new Dimension(this.getPreferredSize().width -15,getbAjouter().getPreferredSize().height+12));
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
			panelTable.setPreferredSize(new Dimension(this.getPreferredSize().width -15,380));
			panelTable.add(getTablePersonnel());
		
			JScrollPane js=new JScrollPane(getTablePersonnel());
	        js.setVisible(true);
	        panelTable.add(js);
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
					try {
						PanGDPController.getInstance().ajouter();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					try {
						PanGDPController.getInstance().supprimer();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					try {
						PanGDPController.getInstance().reinitialiser();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return bReinitialiser;
	}
	
	public TablePersonnels getTablePersonnel()
	{
		if (tablePersonnels == null)
		{
			try {
				tablePersonnels = new TablePersonnels(PersonnelsManager.selectTousPersonnels());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tablePersonnels.setRowHeight(50);  
			tablePersonnels.setBackground(new Color(238,238,238));
			tablePersonnels.setShowGrid(false);
			tablePersonnels.setFont(new Font("Arial", Font.BOLD, 15));
			tablePersonnels.isCellEditable(5, 2);
			int h = this.getPreferredSize().height - 80 -getPanelHead().getPreferredSize().height;
			tablePersonnels.setPreferredScrollableViewportSize(new Dimension(this.getPreferredSize().width-30,h-22));
			tablePersonnels.setFillsViewportHeight(true);
		}
		return tablePersonnels;
	}
	
/*	public JTable getTablePersonnel() throws DALException
	{
		if (tPersonnel == null)
		{
			tPersonnel = new JTable(getDefTableModel()){ 
					public boolean isCellEditable(int row, int column) 
					{ 
							return false; 
					}
			};
	        tPersonnel.setRowHeight(50);  
	        tPersonnel.setBackground(new Color(238,238,238));
			tPersonnel.setShowGrid(false);
			tPersonnel.setFont(new Font("Arial", Font.BOLD, 15));
			tPersonnel.isCellEditable(5, 2);
			int h = this.getPreferredSize().height - 80 -getPanelHead().getPreferredSize().height;
			tPersonnel.setPreferredScrollableViewportSize(new Dimension(this.getPreferredSize().width-30,h-22));
			tPersonnel.setFillsViewportHeight(true);
		}
		return tPersonnel;
	}
*/
/*	
	public DefaultTableModel getDefTableModel()
	{
		if (defTableModel == null)
		{
			try {
				defTableModel = new DefaultTableModel(PanGDPController.getInstance().completerTableau(),PanGDPController.getInstance().getEntete());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return defTableModel;
	}
*/
}
