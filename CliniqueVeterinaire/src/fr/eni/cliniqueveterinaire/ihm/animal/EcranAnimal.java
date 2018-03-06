package fr.eni.cliniqueveterinaire.ihm.animal;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Races;
import fr.eni.cliniqueveterinaire.ihm.Update;
import fr.eni.cliniqueveterinaire.ihm.agenda.PanPriseRdv;
import fr.eni.cliniqueveterinaire.log.LogFactory;

/* Créé par Erwin DUPUIS */
public class EcranAnimal extends JFrame implements Update
{
    //region DECLARATION

	//Permet de déterminer si nous devons modifier ou ajouter l'animal (1 = Ajouter / 0 = Modifier)
	private boolean typeOperation;
	
	private final static Logger LOGGER = Logger.getLogger(LogFactory.class.getName());
	
	private JFrame currentFrame;
	
	private int CodeClient;
	private int CodeAnimal;
	
    private JPanel mainPanel;
    private JPanel clientPanel;
    private JLabel lblClient;
    private JTextField tfdClient;
    private JPanel panelHead;
    private JButton btnValider;
    private JButton btnAnnuler;
    
    private JPanel panelFormulaire;
    private JLabel lblCode;
    private JLabel lblCodeAnimal;
    private JLabel lblNom;
    private JTextField tfdNom;
    private JLabel lblSexe;
    private JComboBox cbSexe;
    private JLabel lblCouleur;
    private JTextField tfdCouleur;
    private JLabel lblEspece;
    private JComboBox cbEspece;
    private JLabel lblRace;
    private JComboBox cbRace;
    private JLabel lblTatouage;
    private JTextField tfdTatouage;
    
    private List<Races> race;
    private Clients clientCourant;
    private Animaux animalCourant;
    
    public Update update;

    //endregion DECLARATION

    //region CTOR

    public EcranAnimal(int codeClient, int codeAnimal, Update update)
    {
    	getCurrentFrame();
    	
    	this.update = update;
    	
    	this.typeOperation = false;    	
    	this.CodeClient = 1;//codeClient;
    	this.CodeAnimal = 3;//codeAnimal;
    	this.race = new ArrayList<Races>();
    	
	    this.setSize(650, 340);
	    //this.setPreferredSize(new Dimension(640, 480));
	    this.setTitle("Animaux");
	    this.setLocationRelativeTo(null);   	
	    	    
	    try 
	    {
	    	clientCourant = EcranAnimalController.selectClientParCode(CodeClient);
			animalCourant = EcranAnimalController.selectAnimal(CodeAnimal);
		} 
	    catch (BLLException e) 
	    {
			LogFactory.getLog().createLog(Level.SEVERE, e.getMessage());
		}	    
	       
	    this.remplirChampsSiAnimal();	
	    
	    this.setupIHM();
		
	    this.setVisible(true); 
    }

    public EcranAnimal(int codeClient, Update update)
    {
    	getCurrentFrame();
    	
    	this.update = update;
    	
    	this.typeOperation = true;
    	this.CodeClient = codeClient;
    	this.race = new ArrayList<Races>();
    	
	    this.setSize(700, 340);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setTitle("Animaux");
	    this.setLocationRelativeTo(null);	    	    
	    
	    try 
	    {
	    	clientCourant = EcranAnimalController.selectClientParCode(CodeClient);    	
	    	this.getCbEspece().setSelectedItem("Chat");
	    	race = EcranAnimalController.selectRaces(getCbEspece().getSelectedItem().toString());
		} 
	    catch (BLLException e) 
	    {
	    	LogFactory.getLog().createLog(Level.SEVERE, e.getMessage());
		}
	    
	    this.setupIHM();
	
	    this.setVisible(true);  
    }
    
    // CTOR A N'UTILISER QUE DANS LE CONTROLLER
    public EcranAnimal()
    {
    	getCurrentFrame();
    	
    	this.race = new ArrayList<Races>();
    	
	    this.setSize(700, 340);
	    this.setResizable(false);
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
        mainPanel.setPreferredSize(new Dimension(this.getWidth()-15, this.getHeight()-200));
        mainPanel.setOpaque(true);
    	this.setContentPane(mainPanel);
    	
    	mainPanel.add(this.getPanelHead());
    	
    	mainPanel.add(this.getClientPanel());
	
    	initPanelFormulaire();
    	mainPanel.add(this.getPanelFormulaire());	    	
	    	
	    this.setContentPane(mainPanel);
    }
    
    public void initPanelHead()
    {
    	panelHead = new JPanel();
		panelHead.setBorder(BorderFactory.createLineBorder(Color.black));
		panelHead.setPreferredSize(new Dimension(595, 40));
    	panelHead.setOpaque(true);
    	panelHead.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	panelHead.add(getBtnAnnuler());
    	panelHead.add(getBtnValider());
    }
    
    public void initPanelFormulaire()
    {
    	panelFormulaire = new JPanel();
    	panelFormulaire.setOpaque(true);
    	this.setContentPane(panelFormulaire);
    	panelFormulaire.setLayout(new GridBagLayout());
    	GridBagConstraints FormGBC = new GridBagConstraints();
    	FormGBC.insets = new Insets(5,5,5,5);
    	
    	//LIGNE 0
    	
    		//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =0;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getLblCode(), FormGBC);
    		//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =0;
	    	panelFormulaire.add(this.getLblCodeAnimal(), FormGBC);
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
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getLblNom(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =1;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getTfdNom(), FormGBC);
			//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =1;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getLblSexe(), FormGBC);
			//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =1;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getCbSexe(), FormGBC);
    	
    	//LIGNE 2
    	
			//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =2;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getLblCouleur(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =2;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getTfdCouleur(), FormGBC);
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
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getLblEspece(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =3;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getCbEspece(), FormGBC);
			//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =3;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getLblRace(), FormGBC);
			//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =3;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getCbRace(), FormGBC);
    	
    	//LIGNE 4
    	
			//COLONNE 0
	    	FormGBC.gridx =0;
	    	FormGBC.gridy =4;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getLblTatouage(), FormGBC);
			//COLONNE 1
	    	FormGBC.gridx =1;
	    	FormGBC.gridy =4;
	    	FormGBC.anchor = GridBagConstraints.WEST;
	    	panelFormulaire.add(this.getTfdTatouage(), FormGBC);
			//COLONNE 2
	    	FormGBC.gridx =2;
	    	FormGBC.gridy =4;
			//COLONNE 3
	    	FormGBC.gridx =3;
	    	FormGBC.gridy =4;
    }
    
    public void initialiseClientPanel()
    {
    	clientPanel = new JPanel();
    	clientPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    	clientPanel.setBorder(BorderFactory.createTitledBorder("Client"));
    	clientPanel.setPreferredSize(new Dimension(this.getWidth()-100, this.getHeight()-285));
    	clientPanel.setOpaque(true);
    	
    	clientPanel.add(getTfdClient());    	
    }
    
	public void remplirChampsSiAnimal()
	{
		this.getLblCodeAnimal().setText(String.valueOf(animalCourant.getCodeAnimal()));
		this.getTfdNom().setText(animalCourant.getNomAnimal());
		this.getCbSexe().setSelectedItem(animalCourant.getsexe());
		if(animalCourant.getCouleur() != null)
		{
			this.getTfdCouleur().setText(animalCourant.getCouleur());
		}
		
		this.getCbEspece().setSelectedItem(animalCourant.getEspece());
		

		try 
		{
			race = EcranAnimalController.selectRaces(animalCourant.getEspece());
		} 
		catch (BLLException e) 
		{
			LogFactory.getLog().createLog(Level.SEVERE, e.getMessage());
		}

		this.getCbRace().setSelectedItem(animalCourant.getRace());
		
		if(animalCourant.gettatouage() != null)
		{
			this.getTfdTatouage().setText(animalCourant.gettatouage());
		}	
	}
	
	//FONCTION POUR METTRE A JOUR LA LISTE D'ANIMAL QUAND NOUVEL ANIMAL
	@Override
	public void updateAnimauxPanPriseRdv(List<Animaux> nvListeAnimaux) 
	{
		if(update != null)
		{
			update.updateAnimauxPanPriseRdv(nvListeAnimaux);
		}
	}
	
    //endregion METHODS

    //region GET/SET
    
    public JPanel getPanelFormulaire()
    {
    	if(panelFormulaire == null)
    	{
    		initPanelFormulaire();
    	}
    	return panelFormulaire;
    }
    
    public JPanel getPanelHead()
    {
    	if(panelHead == null)
    	{
    		initPanelHead();
    	}
    	return panelHead;
    }

	public JButton getBtnValider() 
	{
		if(btnValider == null)
		{
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() 
			{			
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(typeOperation)
					{
						Animaux aAjouter;
						try 
						{
							aAjouter = new Animaux(
									getCodeClient(),
									getCbRace().getSelectedItem().toString(), 
									getTfdNom().getText(), 
									getCbSexe().getSelectedItem().toString(),
									getTfdCouleur().getText(),
									getCbEspece().getSelectedItem().toString(),
									getTfdTatouage().getText(),
									null/* Antécedents */, 
									false);
							
							int codeAnimalAjoute = EcranAnimalController.ajouter(aAjouter);
							List<Animaux> nvListeAnimaux = EcranAnimalController.selectAnimaux(aAjouter.getCodeClient());
							updateAnimauxPanPriseRdv(nvListeAnimaux);
							JOptionPane.showMessageDialog(null, "Animal ajouter", "Succes", JOptionPane.INFORMATION_MESSAGE);
							LogFactory.getLog().createLog(Level.INFO, "Animal ajouté : "+codeAnimalAjoute);
							currentFrame.dispose();
						} 
						catch (BLLException e2) 
						{
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
							LogFactory.getLog().createLog(Level.SEVERE, e2.getMessage());
						}
					}
					else
					{
						try 
						{
							Animaux aModifier = new Animaux(
									getCodeAnimal(),
									getCodeClient(),
									getCbRace().getSelectedItem().toString(), 
									getTfdNom().getText(), 
									getCbSexe().getSelectedItem().toString(),
									getTfdCouleur().getText(),
									getCbEspece().getSelectedItem().toString(),
									getTfdTatouage().getText(),
									null/* Antécedents */, 
									false);
							
							EcranAnimalController.modifier(aModifier);
							JOptionPane.showMessageDialog(null, "Animal modifié", "Succes", JOptionPane.INFORMATION_MESSAGE);
							LogFactory.getLog().createLog(Level.INFO, "Animal modifié : "+aModifier.getCodeAnimal());
							currentFrame.dispose();
						} 
						catch (BLLException e1) 
						{
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
							LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
						}
					}
				}
			});
		}
		return btnValider;
	}

	public JButton getBtnAnnuler() 
	{
		if(btnAnnuler == null)
		{
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() 
			{				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					currentFrame.dispose();
				}
			});
		}
		return btnAnnuler;
	}

	public JLabel getLblClient() 
	{
		if(lblClient == null)
		{
			lblClient = new JLabel("Client : "+clientCourant.getNomClient());
		}
		return lblClient;
	}

	public JLabel getLblCode() 
	{
		if(lblCode == null)
		{
			lblCode = new JLabel("Code : ");
		}
		return lblCode;
	}

	public JLabel getLblCodeAnimal() 
	{
		if(lblCodeAnimal == null)
		{
			lblCodeAnimal = new JLabel("");
		}
		return lblCodeAnimal;
	}

	public JLabel getLblNom() 
	{
		if(lblNom == null)
		{
			lblNom = new JLabel("Nom : ");
		}
		return lblNom;
	}

	public JTextField getTfdNom() 
	{
		if(tfdNom == null)
		{
			tfdNom = new JTextField(30);
		}
		return tfdNom;
	}

	public JLabel getLblSexe() 
	{
		if(lblSexe == null)
		{
			lblSexe = new JLabel("Sexe : ");
		}
		return lblSexe;
	}

	public JComboBox getCbSexe() 
	{
		if(cbSexe == null)
        {
            List<String> sexe = Arrays.asList("M", "F");

            cbSexe = new JComboBox();
            cbSexe.setModel(new DefaultComboBoxModel(sexe.toArray()));
        }
		return cbSexe;
	}

	public JLabel getLblCouleur() 
	{
		if(lblCouleur == null)
		{
			lblCouleur = new JLabel("Couleur : ");
		}
		return lblCouleur;
	}

	public JTextField getTfdCouleur() 
	{
		if(tfdCouleur == null)
		{
			tfdCouleur = new JTextField(30);
		}
		return tfdCouleur;
	}

	public JLabel getLblEspece() 
	{
		if(lblEspece == null)
		{
			lblEspece = new JLabel("Espèce : ");
		}
		return lblEspece;
	}

	public JComboBox getCbEspece() 
	{
		if(cbEspece == null)
        {
            List<String> espece;
			try 
			{
				espece = EcranAnimalController.selectEspeces();
	            cbEspece = new JComboBox();	
	            cbEspece.setPreferredSize(new Dimension(120,22));
	            cbEspece.setModel(new DefaultComboBoxModel(espece.toArray()));
	            cbEspece.addActionListener(new ActionListener() 
	            {	       
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						try
						{
							race = EcranAnimalController.selectRaces(cbEspece.getSelectedItem().toString());
						} 
						catch (BLLException e1) 
						{
							LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
						}
						DefaultComboBoxModel model = new DefaultComboBoxModel( race.toArray() );
						getCbRace().setModel(model);												
					}
				});
			} 
			catch (BLLException e) 
			{
				LogFactory.getLog().createLog(Level.SEVERE, e.getMessage());
			}
        }
		return cbEspece;
	}

	public JLabel getLblRace() 
	{
		if(lblRace == null)
		{
			lblRace = new JLabel("Race : ");
		}
		return lblRace;
	}

	public JComboBox getCbRace() 
	{		
		if(cbRace == null)
		{
	        cbRace = new JComboBox();  
	        cbRace.setPreferredSize(new Dimension(120,22));
	        cbRace.setModel(new DefaultComboBoxModel(race.toArray()));			
		}

		return cbRace;
	}

	public JLabel getLblTatouage() 
	{
		if(lblTatouage == null)
		{
			lblTatouage = new JLabel("Tatouage : ");
		}
		return lblTatouage;
	}

	public JTextField getTfdTatouage() 
	{
		if(tfdTatouage == null)
		{
			tfdTatouage = new JTextField(30);
		}
		return tfdTatouage;
	}
	
	public int getCodeClient() 
	{
		return CodeClient;
	}

	public int getCodeAnimal() 
	{
		return CodeAnimal;
	}
	
	public List<Races> getRace() 
	{
		return race;
	}

	public void setRace(List<Races> race) 
	{
		this.race = race;
	}
	
	public JFrame getCurrentFrame()
	{
		if(currentFrame == null)
		{
			currentFrame = this;
		}
		
		return currentFrame;
	}

	public JPanel getClientPanel() 
	{
		if(clientPanel == null)
		{
			initialiseClientPanel();
		}
		return clientPanel;
	}

	public JTextField getTfdClient() 
	{
		if(tfdClient == null)
		{
			tfdClient = new JTextField(clientCourant.getNomClient(), 15);
			tfdClient.setEditable(false);
		}
		return tfdClient;
	}
		
    //endregion GET/SET
}
