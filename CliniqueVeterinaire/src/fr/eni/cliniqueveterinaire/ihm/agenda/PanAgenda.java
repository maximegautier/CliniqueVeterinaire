package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import fr.eni.cliniqueveterinaire.bll.BLLException;

public class PanAgenda extends JPanel{

	private static PanAgenda instance;
	private JPanel panelHead;
	private JPanel panelTable;
	private JComboBox<String> cbVeterinaire;
	private JDatePickerImpl dpDate;
	private JButton btnDossier;
	
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
			try {
				panelTable.add(new TableAgendaVet(PanAgendaController.remplirTableau(new Date())));
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return panelTable;
	}
	
	public JComboBox<String> getCbVeterinaire() throws BLLException
	{
		if (cbVeterinaire == null){
			cbVeterinaire = new JComboBox<String>();
			List<String> lPerso = PanAgendaController.remplirComboVeterinaire();
			
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
					System.out.println("btnDossier actionPerformed");
				}
			});
		}
		return btnDossier;
	}
}
