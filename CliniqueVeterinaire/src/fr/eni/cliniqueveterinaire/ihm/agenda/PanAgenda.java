package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Personnels;

public class PanAgenda extends JPanel{

	private static PanAgenda instance;
	private JPanel panelHead;
	private JPanel panelTable;
	private JComboBox<Personnels> cbVeterinaire;
	private JDatePickerImpl dpDate;
	private JButton btnDossier;
	private TableAgendaVet tableAgendaVet;
	private List<Agendas> listAgenda;
	
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
			panelHead.add(getCbVeterinaire());
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
			
			JScrollPane js=new JScrollPane(getTableAgendaVet());
	        js.setVisible(true);
	        panelTable.add(js);
		}
		return panelTable;
	}
	
	public JComboBox<Personnels> getCbVeterinaire()
	{
		if (cbVeterinaire == null){
			cbVeterinaire = new JComboBox<Personnels>();
			List<Personnels> lPerso = new ArrayList<Personnels>();
			try {
				lPerso = PanAgendaController.selectVeterinaires();
			} catch (BLLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			cbVeterinaire.setModel(new DefaultComboBoxModel(lPerso.toArray()));
			cbVeterinaire.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Date date = (Date) getDpDate().getModel().getValue();
					Personnels personnel;
					try {
						personnel = (Personnels) getCbVeterinaire().getSelectedItem();
						List<Agendas> data = PanAgendaController.remplirTableau(date, personnel.getCodePers());
						getTableAgendaVet().setData(data);
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			});
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
			dpDate.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Date date = (Date) getDpDate().getModel().getValue();
					Personnels personnel;
					try {
						personnel = (Personnels) getCbVeterinaire().getSelectedItem();
						List<Agendas> data = PanAgendaController.remplirTableau(date, personnel.getCodePers());
						getTableAgendaVet().setData(data);
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			});
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
					try {
						int numLigne = getTableAgendaVet().getSelectedRow();
						if (numLigne == -1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
						} 
						else
						{
							Animaux animal = AnimauxManager.selectAnimal(listAgenda.get(numLigne).getCodeAnimal());
							PanAgendaController.ouvrirDossier(animal);	
						}
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
				initialiseListeRdv();
				tableAgendaVet = new TableAgendaVet(listAgenda);
			} catch (BLLException e) {
				e.printStackTrace();
			}  
			
			int h = this.getPreferredSize().height - 230;
			int w = this.getPreferredSize().width - 19;
			tableAgendaVet.setPreferredScrollableViewportSize(new Dimension(w,h));
		}
		return tableAgendaVet;
	}
	
	public void initialiseListeRdv()
    {
		Date dateSelect = (Date) getDpDate().getModel().getValue();
		Personnels veterinaireCourant = (Personnels) getCbVeterinaire().getSelectedItem();
		try 
		{
			listAgenda = PanAgendaController.selectAgendasParDateVeterinaire(dateSelect, veterinaireCourant.getCodePers());   
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
    }
}
