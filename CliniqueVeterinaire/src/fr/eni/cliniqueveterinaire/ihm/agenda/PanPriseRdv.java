package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Personnels;

public class PanPriseRdv extends JPanel
{
    //region DECLARATION

	private static PanPriseRdv instance;
	
	private JPanel panelPrincipal;
	
	private JPanel panelListe;
	private TableAgendaVet tableRdv;

	private JButton btnSupprimer;
	private JButton btnValider;
	
	private JPanel panelPour;
	private JLabel lblClient;
	private JComboBox cbClient;
	private JButton btnAjoutClient;
	private JLabel lblAnimal;
	private JComboBox cbAnimal;
	private JButton btnAjoutAnimal;
	
	private JPanel panelPar;
	private JLabel lblVeterinaire;
	private JComboBox cbVeterinaire;
	
	private JPanel panelQuand;
	private JPanel panelHeures;
	private JLabel lblDate;
	private JDatePickerImpl dpDate;
	private JLabel lblHeure;
	private JComboBox cbHeure;
	private JLabel lblH;
	private JComboBox cbMinute;
	
	private List<Clients> clients;
	private List<Personnels> veterinaires;
	private List<Agendas> rdv;
	private List<Animaux> animaux;

    //endregion DECLARATION

    //region CTOR

	private PanPriseRdv()
	{	
		try 
		{
			clients = PanAgendaController.selectClients();
			veterinaires = PanAgendaController.selectVeterinaires();
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
		
		getPanelPrincipal();
	}

    //endregion CTOR
    
    //region METHODS
    
    public void initialisePanelPrincipal()
    {		    	
    	this.add(getPanelPour());

    	this.add(getPanelPar());

    	this.add(getPanelQuand());
    	
    	this.add(getPanelListe());

    	this.add(getBtnSupprimer());

    	this.add(getBtnValider());    	    	
    }
    
    public void initialisePanelPour()
    {
    	panelPour = new JPanel();
    	panelPour.setPreferredSize(new Dimension(225, 175));
    	panelPour.setBorder(BorderFactory.createTitledBorder("Pour"));
    	panelPour.setLayout(new GridBagLayout());
    	GridBagConstraints gbcPanelPour = new GridBagConstraints();
    	gbcPanelPour.insets = new Insets(5,5,5,5);
    	
		//LIGNE 0
		
			//COLONNE0
	    	gbcPanelPour.gridx =0;
	    	gbcPanelPour.gridy =0;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getLblClient(), gbcPanelPour);
			//COLONNE1
	    	//gbcPanelPour.gridx =1;
	    	//gbcPanelPour.gridy =0;
	    	
		//LIGNE 1
			
			//COLONNE0
	    	gbcPanelPour.gridx =0;
	    	gbcPanelPour.gridy =1;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getCbClient(), gbcPanelPour);
			//COLONNE1
	    	gbcPanelPour.gridx =1;
	    	gbcPanelPour.gridy =1;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getBtnAjoutClient(), gbcPanelPour);
	    
		//LIGNE 2
			
			//COLONNE0
	    	gbcPanelPour.gridx =0;
	    	gbcPanelPour.gridy =2;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getLblAnimal(), gbcPanelPour);
			//COLONNE1
	    	//gbcPanelPour.gridx =1;
	    	gbcPanelPour.gridy =2;
	    	
		//LIGNE 3
			
			//COLONNE0
	    	gbcPanelPour.gridx =0;
	    	gbcPanelPour.gridy =3;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getCbAnimal(), gbcPanelPour);
			//COLONNE1
	    	gbcPanelPour.gridx =1;
	    	gbcPanelPour.gridy =3;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getBtnAjoutAnimal(), gbcPanelPour);
    	
    }
    
    public void initialisePanelPar()
    {
    	panelPar = new JPanel();
    	panelPar.setPreferredSize(new Dimension(150, 175));
    	panelPar.setBorder(BorderFactory.createTitledBorder("Par"));
    	panelPar.setLayout(new GridBagLayout());
    	GridBagConstraints gbcPanelPar = new GridBagConstraints();
    	gbcPanelPar.insets = new Insets(5,5,5,5);
    	
		//LIGNE 0
		
			//COLONNE0
	    	gbcPanelPar.gridx =0;
	    	gbcPanelPar.gridy =0;
	    	gbcPanelPar.anchor = GridBagConstraints.WEST;
	    	panelPar.add(getLblVeterinaire(), gbcPanelPar);
	    	
		//LIGNE 1
			
			//COLONNE0
	    	gbcPanelPar.gridx =0;
	    	gbcPanelPar.gridy =1;
	    	gbcPanelPar.anchor = GridBagConstraints.WEST;
	    	panelPar.add(getCbVeterinaire(), gbcPanelPar);
    	
    }
    
    public void initialisePanelQuand()
    {
    	panelQuand = new JPanel();
    	panelQuand.setPreferredSize(new Dimension(250, 175));
    	panelQuand.setBorder(BorderFactory.createTitledBorder("Quand"));
    	panelQuand.setLayout(new GridBagLayout());
    	GridBagConstraints gbcPanelQuand = new GridBagConstraints();
    	gbcPanelQuand.insets = new Insets(5,5,5,5);
    	
		//LIGNE 0
		
			//COLONNE0
    		gbcPanelQuand.gridx =0;
    		gbcPanelQuand.gridy =0;
    		gbcPanelQuand.anchor = GridBagConstraints.WEST;
    		panelQuand.add(getLblDate(), gbcPanelQuand);
	    	
		//LIGNE 1
			
			//COLONNE0
	    	gbcPanelQuand.gridx =0;
	    	gbcPanelQuand.gridy =1;
	    	gbcPanelQuand.anchor = GridBagConstraints.WEST;
    		panelQuand.add((Component) getDpDate(), gbcPanelQuand);
	    
		//LIGNE 2
			
			//COLONNE0
	    	gbcPanelQuand.gridx =0;
	    	gbcPanelQuand.gridy =2;
	    	gbcPanelQuand.anchor = GridBagConstraints.WEST;
    		panelQuand.add(getLblHeure(), gbcPanelQuand);
	    	
		//LIGNE 3
			
			//COLONNE0
	    	gbcPanelQuand.gridx =0;
	    	gbcPanelQuand.gridy =3;
	    	gbcPanelQuand.anchor = GridBagConstraints.WEST;
    		panelQuand.add(getPanelHeures(), gbcPanelQuand);
    	
    }

    public void initialisePanelListe()
    {
    	panelListe = new JPanel();
    	panelListe.setPreferredSize(new Dimension(this.getPreferredSize().width - 15, 200));
   		
		panelListe.add(getTableRdv());

		JScrollPane js=new JScrollPane(getTableRdv());
		js.setPreferredSize(new Dimension(this.getPreferredSize().width - 15, 190));
        js.setVisible(true);
        panelListe.add(js);
    }
   
    public void initialiseListeRdv()
    {
    	Personnels veterinaireCourant = (Personnels) getCbVeterinaire().getSelectedItem();
		Date dateSelect = (Date) getDpDate().getModel().getValue();
		try 
		{
			rdv = PanAgendaController.selectAgendasParDateVeterinaire(dateSelect, veterinaireCourant.getCodePers());   
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		}
    }
    
    public void initialiseListeAnimaux()
    {
		Clients clientCourant = (Clients) cbClient.getSelectedItem();
		
		try 
		{
			animaux = PanAgendaController.selectAnimauxClient(clientCourant.getCodeClient());
		} 
		catch (BLLException e) 
		{
			e.printStackTrace();
		} 
    }
    
    //endregion METHODS

    //region GET/SET

	public static JPanel getInstance()
	{
		if(PanPriseRdv.instance == null)
		{
			PanPriseRdv.instance = new PanPriseRdv();
		}
		return PanPriseRdv.instance;
	}
	
	public JPanel getPanelPrincipal() 
	{
		if(panelPrincipal == null)
		{
			initialisePanelPrincipal();
		}
		return panelPrincipal;
	}
	
	public JPanel getPanelPour() 
	{
		if(panelPour == null)
		{
			initialisePanelPour();
		}
		return panelPour;
	}
	
	public JPanel getPanelPar() 
	{
		if(panelPar == null)
		{
			initialisePanelPar();
		}
		return panelPar;
	}
	
	public JPanel getPanelQuand() 
	{
		if(panelQuand == null)
		{
			initialisePanelQuand();
		}
		return panelQuand;
	}
	
	public JPanel getPanelListe() 
	{
		if(panelListe == null)
		{
			initialisePanelListe();
		}
		return panelListe;
	}

	public JButton getBtnSupprimer() 
	{
		if(btnSupprimer == null)
		{
			btnSupprimer = new JButton("Supprimer");
		}
		return btnSupprimer;
	}
	
	public JButton getBtnValider() 
	{
		if(btnValider == null)
		{
			btnValider = new JButton("Valider");
		}
		return btnValider;
	}
	
	public JLabel getLblClient() 
	{
		if(lblClient == null)
		{
			lblClient = new JLabel("Client");
		}
		return lblClient;
	}
	
	public JComboBox getCbClient() 
	{
		if(cbClient == null)
		{	
			cbClient = new JComboBox();
			cbClient.setModel(new DefaultComboBoxModel(clients.toArray()));	
			cbClient.setSelectedIndex(0);
			
			initialiseListeAnimaux();
			DefaultComboBoxModel model = new DefaultComboBoxModel( animaux.toArray() );
			getCbAnimal().setModel(model);
			
			cbClient.addActionListener(new ActionListener() 
			{				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					initialiseListeAnimaux();
					DefaultComboBoxModel model = new DefaultComboBoxModel( animaux.toArray() );
					getCbAnimal().setModel(model);
				}
			});
			
		}
		return cbClient;
	}
	
	public JButton getBtnAjoutClient() 
	{
		if(btnAjoutClient == null)
		{
			btnAjoutClient = new JButton("+");
			btnAjoutClient.addActionListener(new ActionListener() 
			{				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub					
				}
			});
		}
		return btnAjoutClient;
	}

	public JLabel getLblAnimal() 
	{
		if(lblAnimal == null)
		{
			lblAnimal = new JLabel("Animal");
		}
		return lblAnimal;
	}

	public JComboBox getCbAnimal() 
	{
		if(cbAnimal == null)
		{
			cbAnimal = new JComboBox();
			
			if(animaux != null)
			{
				cbClient.setModel(new DefaultComboBoxModel(clients.toArray()));				
			}

		}
		return cbAnimal;
	}

	public JButton getBtnAjoutAnimal() 
	{
		if(btnAjoutAnimal == null)
		{
			btnAjoutAnimal = new JButton("+");
			btnAjoutAnimal.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					Clients selectClient = (Clients)getCbClient().getSelectedItem();
					PanAgendaController.ouvrirAjoutAnimal(selectClient.getCodeClient());
					
				}
			});
		}
		return btnAjoutAnimal;
	}

	public JLabel getLblVeterinaire() 
	{
		if(lblVeterinaire == null)
		{
			lblVeterinaire = new JLabel("Veterinaire");
		}
		return lblVeterinaire;
	}

	public JComboBox getCbVeterinaire() 
	{
		if(cbVeterinaire == null)
		{
			cbVeterinaire = new JComboBox();
			cbVeterinaire.setModel(new DefaultComboBoxModel(veterinaires.toArray()));
			cbVeterinaire.setSelectedIndex(0);
			
			cbVeterinaire.addActionListener(new ActionListener()
			{				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					initialiseListeRdv();
					getTableRdv().setData(rdv);
				}
			});
		}
		return cbVeterinaire;
	}

	public JPanel getPanelHeures() 
	{
		if(panelHeures == null)
		{
			panelHeures = new JPanel();
			panelHeures.setLayout(new FlowLayout());
			panelHeures.add(getCbHeure());
			panelHeures.add(getLblH());
			panelHeures.add(getCbMinute());
		}
		return panelHeures;
	}

	public JLabel getLblDate() 
	{
		if(lblDate == null)
		{
			lblDate = new JLabel("Date");
		}
		return lblDate;
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

	public JLabel getLblHeure() 
	{
		if(lblHeure == null)
		{
			lblHeure = new JLabel("Heure");
		}
		return lblHeure;
	}

	public JComboBox getCbHeure() 
	{
		if(cbHeure == null)
		{
			cbHeure = new JComboBox();
		}
		return cbHeure;
	}

	public JLabel getLblH() 
	{
		if(lblH == null)
		{
			lblH = new JLabel(" h ");
		}
		return lblH;
	}

	public JComboBox getCbMinute() 
	{
		if(cbMinute == null)
		{
			cbMinute = new JComboBox();
		}
		return cbMinute;
	}

	public TableAgendaVet getTableRdv() 
	{
		if(tableRdv == null)
		{
			try 
			{
				initialiseListeRdv();
				tableRdv = new TableAgendaVet(rdv);								
			} 
			catch (BLLException e) 
			{
				e.printStackTrace();
			}
			
			int h = this.getPreferredSize().height - 230;
			int w = this.getPreferredSize().width - 19;
			tableRdv.setPreferredScrollableViewportSize(new Dimension(w,h));
		}
		return tableRdv;
	}
	
    //endregion GET/SET
}

