package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.ihm.personnels.PanGDPController;
import fr.eni.cliniqueveterinaire.ihm.personnels.TablePersonnels;

public class PanAgenda extends JPanel{

	private static PanAgenda instance;
	private JPanel panelHead;
	private JPanel panelTable;
	private JComboBox<Personnels> cbVeterinaire;
	private JDatePickerImpl dpDate;
	private JButton btnDossier;
	private TableAgendaVet tableAgendaVet;
	
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
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(getPanelHead(),gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(getPanelTable(),gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		//gbc.anchor = GridBagConstraints.WEST;
		add(getBtnDossier(),gbc);
	}

	
	private JPanel getPanelHead()
	{
		if (panelHead == null)
		{
			panelHead = new JPanel();
			panelHead.setBorder(BorderFactory.createTitledBorder("De"));
			panelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
			panelHead.setOpaque(true);
			panelHead.setPreferredSize(new Dimension(this.getPreferredSize().width -15,60));
			panelHead.add(new JLabel("Veterinaire :"));
			try {
				panelHead.add(getCbVeterinaire());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			panelHead.add(new JLabel("   Date :"));
			panelHead.add((Component)getDpDate());

		}
		return panelHead;
	}
	
	private JPanel getPanelTable()
	{
		if (panelTable == null)
		{
			panelTable = new JPanel();
			panelTable.setPreferredSize(new Dimension(this.getPreferredSize().width -15,300));
			panelTable.add(getTableAgendaVet());
		}
		return panelTable;
	}
	
	public JComboBox<Personnels> getCbVeterinaire() throws BLLException
	{
		if (cbVeterinaire == null){
			cbVeterinaire = new JComboBox<Personnels>();
			List<Personnels> lPerso = PanAgendaController.selectVeterinaires();
			
			cbVeterinaire.setModel(new DefaultComboBoxModel(lPerso.toArray()));
		}
		return cbVeterinaire;
	}
	
	public JDatePickerImpl getDpDate() 
	{
		if(dpDate == null)
		{
			UtilDateModel model = new UtilDateModel();
			model.setSelected(true);
			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			dpDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		}
		return dpDate;
	}
	
	public JButton getBtnDossier()
	{
		if (btnDossier == null)
		{
			btnDossier = new JButton("Dossier medical",new ImageIcon("ressources/icons8-dossier-32.png"));
			btnDossier.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					PanAgendaController.ouvrirDossier(getTableAgendaVet());
				}
			});
		}
		return btnDossier;
	}
	
	public TableAgendaVet getTableAgendaVet()
	{
		if (tableAgendaVet == null)
		{
			try {	
				Date date = (Date) getDpDate().getModel().getValue();
				Personnels personnel = (Personnels) getCbVeterinaire().getSelectedItem();
				tableAgendaVet = new TableAgendaVet(PanAgendaController.remplirTableau(date,personnel.getCodePers()));
			} catch (BLLException e) {
				e.printStackTrace();
			}
			tableAgendaVet.setRowHeight(50);  
			tableAgendaVet.setBackground(new Color(238,238,238));
			tableAgendaVet.setShowGrid(false);
			tableAgendaVet.setFont(new Font("Arial", Font.BOLD, 15));
			tableAgendaVet.isCellEditable(5, 2);
			int h = this.getPreferredSize().height - 80 -getPanelHead().getPreferredSize().height;
			tableAgendaVet.setPreferredScrollableViewportSize(new Dimension(this.getPreferredSize().width-30,h-22));
			tableAgendaVet.setFillsViewportHeight(true);
		}
		return tableAgendaVet;
	}
}
