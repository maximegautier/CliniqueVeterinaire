package fr.eni.cliniqueveterinaire.ihm.animal;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Races;

/* Créé par Erwin DUPUIS */
public class EcranAnimal extends JFrame
{
    //region DECLARATION

	//Permet de déterminer si nous devons modifier ou ajouter l'animal (1 = Ajouter / 0 = Modifier)
	private boolean typeOperation;
	
	private int CodeClient;
	private int CodeAnimal;
	
    private JPanel mainPanel;
    
    private JPanel PanelHead;
    private JButton BtnValider;
    private JButton BtnAnnuler;
    private JLabel LblClient;
    
    private JPanel PanelFormulaire;
    private JLabel LblCode;
    private JTextField TFdCode;
    private JLabel LblNom;
    private JTextField TFdNom;
    private JLabel LblSexe;
    private JComboBox CbSexe;
    private JLabel LblCouleur;
    private JTextField TFdCouleur;
    private JLabel LblEspece;
    private JComboBox CbEspece;
    private JLabel LblRace;
    private JComboBox CbRace;
    private JLabel LblTatouage;
    private JTextField TFdTatouage;
    
    private List<Races> raceChat;
    private List<Races> raceChien;

    //endregion DECLARATION

    //region CTOR

    public EcranAnimal(int codeClient, int codeAnimal)
    {
    	this.typeOperation = false;    	
    	this.CodeClient = codeClient;
    	this.CodeAnimal = codeAnimal;
    	this.raceChat = new ArrayList<Races>();
    	this.raceChien = new ArrayList<Races>();
    	
	    this.setSize(640, 480);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setTitle("Animaux");
	    this.setLocationRelativeTo(null);
	    this.setupIHM();
	
	    this.setVisible(true);    	
	    
	    EcranAnimalController.getInstance().RemplirChamps();
    }

    public EcranAnimal(int codeClient)
    {
    	this.typeOperation = true;
    	this.CodeClient = codeClient;
    	this.raceChat = new ArrayList<Races>();
    	this.raceChien = new ArrayList<Races>();
    	
	    this.setSize(640, 480);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setTitle("Animaux");
	    this.setLocationRelativeTo(null);
	    this.setupIHM();
	
	    this.setVisible(true);    	
    }
    
    // A N'UTILISER QUE DANS LE CONTROLLER
    public EcranAnimal()
    {
    	this.raceChat = new ArrayList<Races>();
    	this.raceChien = new ArrayList<Races>();
    	
	    this.setSize(640, 480);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setTitle("Animaux");
	    this.setLocationRelativeTo(null);
	    this.setupIHM();
	
	    this.setVisible(true);    	
    }

    //endregion CTOR
    
    //region METHODS
    
    public void setupIHM()
    {
    	mainPanel = new JPanel();
        mainPanel.setSize(this.getWidth(), this.getHeight());
        mainPanel.setOpaque(true);
        mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints MainGBC = new GridBagConstraints();
        MainGBC.insets = new Insets(5, 5, 5, 5);
    	this.setContentPane(mainPanel);
    	
    	//LIGNE 0
    		
    		//COLONNE 0
	    	MainGBC.gridx =0;
	    	MainGBC.gridy =0;
	    	MainGBC.gridwidth = 2;
	    	MainGBC.fill = GridBagConstraints.HORIZONTAL;
	    	mainPanel.add(this.getPanelHead(), MainGBC);
	    	MainGBC.gridwidth = 0;
    	
    	//LIGNE 1
		
			//COLONNE 0
	    	MainGBC.fill = GridBagConstraints.HORIZONTAL;
	    	MainGBC.gridx =0;
	    	MainGBC.gridy =1;		    	
	    	mainPanel.add(this.getLblClient(), MainGBC);
    	
    	//LIGNE 2
		
			//COLONNE 0
	    	MainGBC.gridx =0;
	    	MainGBC.gridy =2;
	    	MainGBC.gridwidth =2;
	    	MainGBC.fill = GridBagConstraints.HORIZONTAL;
	    	InitPanelFormulaire();
	    	mainPanel.add(this.getPanelFormulaire(), MainGBC);
	    	MainGBC.gridwidth = 0;	    	
	    	
	    this.setContentPane(mainPanel);
    }
    
    public void InitPanelFormulaire()
    {
    	PanelFormulaire = new JPanel();
    	PanelFormulaire.setOpaque(true);
    	this.setContentPane(PanelFormulaire);
    	PanelFormulaire.setLayout(new GridBagLayout());
    	GridBagConstraints FormGBC = new GridBagConstraints();
    	FormGBC.insets = new Insets(5,5,5,5);
    	//this.setContentPane(PanelFormulaire);
    	
    	//LIGNE 0
    	
    		//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =0;
	    	PanelFormulaire.add(this.getLblCode(), FormGBC);
    		//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =0;
	    	PanelFormulaire.add(this.getTFdCode(), FormGBC);
    		//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =0;
    		//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =0;
    	
    	//LIGNE 1
    	
			//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =1;
	    	PanelFormulaire.add(this.getLblNom(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =1;
	    	PanelFormulaire.add(this.getTfdNom(), FormGBC);
			//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =1;
	    	PanelFormulaire.add(this.getLblSexe(), FormGBC);
			//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =1;
	    	PanelFormulaire.add(this.getCbSexe(), FormGBC);
    	
    	//LIGNE 2
    	
			//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =2;
	    	PanelFormulaire.add(this.getLblCouleur(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =2;
	    	PanelFormulaire.add(this.getTfdCouleur(), FormGBC);
			//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =2;
			//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =2;
    	
    	//LIGNE 3
    	
			//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =3;
	    	PanelFormulaire.add(this.getLblEspece(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =3;
	    	PanelFormulaire.add(this.getCbEspece(), FormGBC);
			//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =3;
	    	PanelFormulaire.add(this.getLblRace(), FormGBC);
			//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =3;
	    	PanelFormulaire.add(this.getCbRace(), FormGBC);
    	
    	//LIGNE 4
    	
			//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =4;
	    	PanelFormulaire.add(this.getLblTatouage(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =4;
	    	PanelFormulaire.add(this.getTfdTatouage(), FormGBC);
			//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =4;
			//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =4;
    }
    
    //endregion METHODS

    //region GET/SET
    
    public JPanel getPanelFormulaire()
    {
    	if(PanelFormulaire == null)
    	{
    		InitPanelFormulaire();
    	}
    	return PanelFormulaire;
    }
    
    public JPanel getPanelHead()
    {
    	if(PanelHead == null)
    	{
    		PanelHead = new JPanel();
    		PanelHead.setBorder(BorderFactory.createLineBorder(Color.black));
    		PanelHead.setSize(mainPanel.getWidth(), mainPanel.getHeight());
        	PanelHead.setOpaque(true);
        	PanelHead.setLayout(new FlowLayout(FlowLayout.RIGHT));
        	PanelHead.add(getBtnAnnuler());
        	PanelHead.add(getBtnValider());
    	}
    	return PanelHead;
    }

	public JButton getBtnValider() 
	{
		if(BtnValider == null)
		{
			BtnValider = new JButton("Valider");
			BtnValider.addActionListener(new ActionListener() 
			{			
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(typeOperation)
					{
						Animaux aAjouter = new Animaux(
								getCodeClient(),
								getCbRace().getSelectedItem().toString(), 
								getTfdNom().getText(), 
								getCbSexe().getSelectedItem().toString(),
								getTfdCouleur().getText(),
								getCbEspece().getSelectedItem().toString(),
								getTfdTatouage().getText(),
								null/* Antécedents */, 
								true);
						try 
						{
							EcranAnimalController.getInstance().Ajouter(aAjouter);
						} 
						catch (BLLException e1) 
						{
							e1.printStackTrace();
						}						
					}
					else
					{
						Animaux aModifier = new Animaux(
								getCodeClient(),
								getCbRace().getSelectedItem().toString(), 
								getTfdNom().getText(), 
								getCbSexe().getSelectedItem().toString(),
								getTfdCouleur().getText(),
								getCbEspece().getSelectedItem().toString(),
								getTfdTatouage().getText(),
								null/* Antécedents */, 
								true);
						try 
						{
							EcranAnimalController.getInstance().Modifier(aModifier);
						} 
						catch (BLLException e1) 
						{
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return BtnValider;
	}

	public JButton getBtnAnnuler() 
	{
		if(BtnAnnuler == null)
		{
			BtnAnnuler = new JButton("Annuler");
		}
		return BtnAnnuler;
	}

	public JLabel getLblClient() 
	{
		if(LblClient == null)
		{
			LblClient = new JLabel("Client : (concat du nom du client ICI)");
		}
		return LblClient;
	}

	public JLabel getLblCode() 
	{
		if(LblCode == null)
		{
			LblCode = new JLabel("Code : ");
		}
		return LblCode;
	}

	public JTextField getTFdCode() 
	{
		if(TFdCode == null)
		{
			TFdCode = new JTextField(30);
		}
		return TFdCode;
	}

	public JLabel getLblNom() 
	{
		if(LblNom == null)
		{
			LblNom = new JLabel("Nom : ");
		}
		return LblNom;
	}

	public JTextField getTfdNom() 
	{
		if(TFdNom == null)
		{
			TFdNom = new JTextField(30);
		}
		return TFdNom;
	}

	public JLabel getLblSexe() 
	{
		if(LblSexe == null)
		{
			LblSexe = new JLabel("Sexe : ");
		}
		return LblSexe;
	}

	public JComboBox getCbSexe() 
	{
		if(CbSexe == null)
        {
            List<String> sexe = Arrays.asList("M", "F");

            CbSexe = new JComboBox();
            CbSexe.setModel(new DefaultComboBoxModel(sexe.toArray()));
        }
		return CbSexe;
	}

	public JLabel getLblCouleur() 
	{
		if(LblCouleur == null)
		{
			LblCouleur = new JLabel("Couleur : ");
		}
		return LblCouleur;
	}

	public JTextField getTfdCouleur() 
	{
		if(TFdCouleur == null)
		{
			TFdCouleur = new JTextField(30);
		}
		return TFdCouleur;
	}

	public JLabel getLblEspece() 
	{
		if(LblEspece == null)
		{
			LblEspece = new JLabel("Espèce : ");
		}
		return LblEspece;
	}

	public JComboBox getCbEspece() 
	{
		if(CbEspece == null)
        {
            List<String> espece = Arrays.asList("Chat", "Chien");

            CbEspece = new JComboBox();
            CbEspece.setModel(new DefaultComboBoxModel(espece.toArray()));
        }
		return CbEspece;
	}

	public JLabel getLblRace() 
	{
		if(LblRace == null)
		{
			LblRace = new JLabel("Race : ");
		}
		return LblRace;
	}

	public JComboBox getCbRace() 
	{
		switch(CbEspece.getSelectedItem().toString())
		{
			case "Chat" :
				if(CbRace == null)
		        {
		            List<String> race = new ArrayList<String>();
		            
		            for(Races tmpItem : raceChat)
		            {
		            	race.add(tmpItem.getRace());
		            }
		
		            CbRace = new JComboBox();
		            CbRace.setModel(new DefaultComboBoxModel(race.toArray()));
		        }	
				break;
				
			case "Chien" : 
				if(CbRace == null)
		        {
		            List<String> race = new ArrayList<String>();
		            
		            for(Races tmpItem : raceChien)
		            {
		            	race.add(tmpItem.getRace());
		            }
		
		            CbRace = new JComboBox();
		            CbRace.setModel(new DefaultComboBoxModel(race.toArray()));
		        }	
				break;
		}
		return CbRace;
	}

	public JLabel getLblTatouage() 
	{
		if(LblTatouage == null)
		{
			LblTatouage = new JLabel("Tatouage : ");
		}
		return LblTatouage;
	}

	public JTextField getTfdTatouage() 
	{
		if(TFdTatouage == null)
		{
			TFdTatouage = new JTextField(30);
		}
		return TFdTatouage;
	}

	
	public int getCodeClient() 
	{
		return CodeClient;
	}


	public int getCodeAnimal() 
	{
		return CodeAnimal;
	}

    //endregion GET/SET
}
