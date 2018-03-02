package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class PanPriseRdv extends JPanel
{
    //region DECLARATION

	private static PanPriseRdv instance;
	
	private JPanel panelPrincipal;
	private JPanel panelListe;
	
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
	private JDatePicker dpDate;
	private JLabel lblHeure;
	private JComboBox cbHeure;
	private JLabel lblH;
	private JComboBox cbMinute;

    //endregion DECLARATION

    //region CTOR

	private PanPriseRdv()
	{	
		getPanelPrincipal();
	}

    //endregion CTOR
    
    //region METHODS
    
    public void initialisePanelPrincipal()
    {	
		this.setLayout(new GridBagLayout());
    	GridBagConstraints gbcPrincipal = new GridBagConstraints();
    	gbcPrincipal.insets = new Insets(5, 5, 5, 5);
		
		//LIGNE 0
			
			//COLONNE0
	    	gbcPrincipal.gridx =0;
	    	gbcPrincipal.gridy =0;
	    	this.add(getPanelPour(), gbcPrincipal);
			//COLONNE1
	    	gbcPrincipal.gridx =0;
	    	gbcPrincipal.gridy =1;
	    	this.add(getPanelPar(), gbcPrincipal);
			//COLONNE2
	    	gbcPrincipal.gridx =0;
	    	gbcPrincipal.gridy =2;
	    	this.add(getPanelQuand(), gbcPrincipal);
	    	
		//LIGNE 1
			
			//COLONNE0
	    	gbcPrincipal.gridx =1;
	    	gbcPrincipal.gridy =0;
	    	gbcPrincipal.gridwidth = 3;
	    	this.add(getPanelListe(), gbcPrincipal);
			//COLONNE1
	    	gbcPrincipal.gridx =1;
	    	gbcPrincipal.gridy =1;
			//COLONNE2
	    	gbcPrincipal.gridx =1;
	    	gbcPrincipal.gridy =2;
	    
		//LIGNE 2
			
			//COLONNE0
	    	gbcPrincipal.gridx =2;
	    	gbcPrincipal.gridy =0;
			//COLONNE1
	    	gbcPrincipal.gridx =2;
	    	gbcPrincipal.gridy =1;
	    	gbcPrincipal.gridwidth = 1;
	    	gbcPrincipal.anchor = GridBagConstraints.EAST;
	    	this.add(getBtnSupprimer(), gbcPrincipal);
			//COLONNE2
	    	gbcPrincipal.gridx =2;
	    	gbcPrincipal.gridy =2;
	    	gbcPrincipal.anchor = GridBagConstraints.EAST;
	    	this.add(getBtnValider(), gbcPrincipal);
	    	
	    	
    }
    
    public void initialisePanelPour()
    {
    	panelPour = new JPanel();
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
	    	gbcPanelPour.gridx =0;
	    	gbcPanelPour.gridy =1;
	    	
		//LIGNE 1
			
			//COLONNE0
	    	gbcPanelPour.gridx =1;
	    	gbcPanelPour.gridy =0;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getCbClient(), gbcPanelPour);
			//COLONNE1
	    	gbcPanelPour.gridx =1;
	    	gbcPanelPour.gridy =1;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getBtnAjoutClient(), gbcPanelPour);
	    
		//LIGNE 2
			
			//COLONNE0
	    	gbcPanelPour.gridx =2;
	    	gbcPanelPour.gridy =0;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getLblAnimal(), gbcPanelPour);
			//COLONNE1
	    	gbcPanelPour.gridx =2;
	    	gbcPanelPour.gridy =1;
	    	
		//LIGNE 3
			
			//COLONNE0
	    	gbcPanelPour.gridx =3;
	    	gbcPanelPour.gridy =0;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getCbAnimal(), gbcPanelPour);
			//COLONNE1
	    	gbcPanelPour.gridx =3;
	    	gbcPanelPour.gridy =1;
	    	gbcPanelPour.anchor = GridBagConstraints.WEST;
	    	panelPour.add(getBtnAjoutAnimal(), gbcPanelPour);
    	
    }
    
    public void initialisePanelPar()
    {
    	panelPar = new JPanel();
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
	    	gbcPanelPar.gridx =1;
	    	gbcPanelPar.gridy =0;
	    	gbcPanelPar.anchor = GridBagConstraints.WEST;
	    	panelPar.add(getCbVeterinaire(), gbcPanelPar);
    	
    }
    
    public void initialisePanelQuand()
    {
    	panelQuand = new JPanel();
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
	    	gbcPanelQuand.gridx =1;
	    	gbcPanelQuand.gridy =0;
	    	gbcPanelQuand.anchor = GridBagConstraints.WEST;
    		panelQuand.add((Component) getDpDate(), gbcPanelQuand);
	    
		//LIGNE 2
			
			//COLONNE0
	    	gbcPanelQuand.gridx =2;
	    	gbcPanelQuand.gridy =0;
	    	gbcPanelQuand.anchor = GridBagConstraints.WEST;
    		panelQuand.add(getLblHeure(), gbcPanelQuand);
	    	
		//LIGNE 3
			
			//COLONNE0
	    	gbcPanelQuand.gridx =2;
	    	gbcPanelQuand.gridy =0;
	    	gbcPanelQuand.anchor = GridBagConstraints.WEST;
    		panelQuand.add(getPanelHeures(), gbcPanelQuand);
    	
    }

    public void initialisePanelListe()
    {
    	panelListe = new JPanel();
    	panelListe.setLayout(new GridBagLayout());
    	GridBagConstraints gbcPanelListe = new GridBagConstraints();
    	gbcPanelListe.insets = new Insets(5,5,5,5);
    	
		//LIGNE 0
		
			//COLONNE0
    		gbcPanelListe.gridx =0;
    		gbcPanelListe.gridy =0;
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
		return cbClient;
	}

	
	public JButton getBtnAjoutClient() 
	{
		if(btnAjoutClient == null)
		{
			btnAjoutClient = new JButton("+");
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
		return cbAnimal;
	}

	
	public JButton getBtnAjoutAnimal() 
	{
		if(btnAjoutAnimal == null)
		{
			btnAjoutAnimal = new JButton("+");
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

	public JDatePicker getDpDate() 
	{
		if(dpDate == null)
		{
			UtilDateModel model = new UtilDateModel();
			int annee = Calendar.getInstance().get(Calendar.YEAR);
			int mois = Calendar.getInstance().get(Calendar.MONTH);
			int jour = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			model.setDate(annee, mois, jour);
			model.setSelected(true);
			JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
			dpDate = new JDatePickerImpl(datePanel, null);
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
		return cbMinute;
	}

	
	
    //endregion GET/SET
}
