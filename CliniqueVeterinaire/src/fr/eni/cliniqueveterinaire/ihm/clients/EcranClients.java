package fr.eni.cliniqueveterinaire.ihm.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import fr.eni.cliniqueveterinaire.ihm.login.EcranLoginController;


public class EcranClients extends JFrame{
	private static EcranClients instance;
	
	//Composants Java Swing à intégrer à l'ihm
	public JPanel panelPrincipal;
	public JPanel panelRecherche;
	public JPanel panelFormulaire;
	public JPanel panelButtonAnimal;
	public GridBagConstraints gbcPrincipal; 
	public GridBagConstraints gbcRecherche;
	public GridBagConstraints gbcFormulaire;
	public GridBagConstraints gbcButtonAnimaux;
	public JButton bRechercher;
	public JButton bAjouterClient;
	public JButton bSupprimerClient;
	public JButton bValider;
	public JButton bAnnuler;
	public JTextField txtNom;
	public JTextField txtPrenom;
	public JTextField txtCodeClient;
	public JTextField txtAdresse;
	public JTextField txtComplementAdresse;
	public JTextField txtCodePostal;
	public JTextField txtVille;
	public JTable tabAnimaux;
	public JScrollPane scrollPanel ;
	public JPanel panelTabAnimaux;
	public JButton bAjouterAnimal;
	public JButton bSupprimerAnimal;
	public JButton bEditerAnimal;
	

	//Méthode static de récupération d'une instance unique de la fenêtre 
	public static EcranClients getInstance(){
		if (EcranClients.instance == null){
			EcranClients.instance = new EcranClients();
		}
		return instance;
	}

	//Constructeur
	private EcranClients() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 500);
		setResizable(true);
		setTitle("Clients");
		setContentPane(initEcranClients());
		setVisible(true);
		setLocationRelativeTo(null);
	}
		
	private JPanel initBarreRecherche(){			
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=0;
		getPanelRecherche().add(getbRechercher());
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=1;
		getPanelRecherche().add(new JLabel("             "));
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=2;
		getPanelRecherche().add(new JLabel("             "));
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=3;
		getPanelRecherche().add(getbAjouterClient());
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=4;
		getPanelRecherche().add(getbSupprimerClient());
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=5;
		getPanelRecherche().add(new JLabel("                         "));
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=6;
		getPanelRecherche().add(getbValider());
		getGbcRecherche().gridx=0;
		getGbcRecherche().gridy=7;
		getPanelRecherche().add(getbAnnuler());
		
		return getPanelRecherche();
	}
	
	private JPanel initFormulaireClients(){
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=0;
		getPanelFormulaire().add(new JLabel("Code"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=0;
		getPanelFormulaire().add(getTxtCodeClient(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=1;
		getPanelFormulaire().add(new JLabel("Nom"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=1;
		getPanelFormulaire().add(getTxtNom(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=2;
		getPanelFormulaire().add(new JLabel("Prénom"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=2;
		getPanelFormulaire().add(getTxtPrenom(),getGbcFormulaire());			
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=3;
		getPanelFormulaire().add(new JLabel("Adresse"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=3;
		getPanelFormulaire().add(getTxtAdresse(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=4;
		getPanelFormulaire().add(new JLabel("Complement Ad."),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=4;
		getPanelFormulaire().add(getTxtComplementAdresse(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=5;
		getPanelFormulaire().add(new JLabel("Code Postal"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=5;
		getPanelFormulaire().add(getTxtCodePostal(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=6;
		getPanelFormulaire().add(new JLabel("Ville"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=6;
		getPanelFormulaire().add(getTxtVille(),getGbcFormulaire());
		
		return getPanelFormulaire();
	}
	
	private JPanel initSectionAnimaux(){
		getGbcButtonAnimaux().gridx=0;
		getGbcButtonAnimaux().gridy=0;
		getPanelButtonAnimaux().add(getbAjouterAnimal(),getGbcButtonAnimaux());
		getGbcButtonAnimaux().gridx=1;
		getGbcButtonAnimaux().gridy=0;
		getPanelButtonAnimaux().add(getbSupprimerAnimal(),getGbcButtonAnimaux());
		getGbcButtonAnimaux().gridx=2;
		getGbcButtonAnimaux().gridy=0;
		getPanelButtonAnimaux().add(getbEditerAnimal(),getGbcButtonAnimaux());
		
		return getPanelButtonAnimaux();
	}
	
	//Méthode de construction de l'ihm
	private JPanel initEcranClients(){
		//Section Barre de recherche
		getGbcPrincipal().gridx=0;
		getGbcPrincipal().gridy=0;
		getGbcPrincipal().gridwidth = 2;
		getPanelPrincipal().add(initBarreRecherche(),getGbcPrincipal());
			
		//Section Formulaire client
				
		getGbcPrincipal().gridx=0;
		getGbcPrincipal().gridy=1;
		getGbcPrincipal().gridwidth = 1;
		getPanelPrincipal().add(initFormulaireClients(),getGbcPrincipal());
			
		//Section tableau animaux
			
		getGbcPrincipal().gridx=1;
		getGbcPrincipal().gridy=1;
		getPanelPrincipal().add(getPanelTabAnimaux(),getGbcPrincipal());	
		getGbcPrincipal().gridx=1;
		getGbcPrincipal().gridy=2;
		getPanelPrincipal().add(initSectionAnimaux(),getGbcPrincipal());		
		
		return panelPrincipal;
	}
	
	
	//-----------------------------------getters / setters----------------------------------------
	
	public JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new GridBagLayout());
		}
		return panelPrincipal;
	}
	
	public JPanel getPanelRecherche() {
		if(panelRecherche == null){
			panelRecherche = new JPanel();
			panelRecherche.setLayout(new GridBagLayout());
			panelRecherche.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		return panelRecherche;
	}
	
	public JPanel getPanelFormulaire() {
		if(panelFormulaire == null){
			panelFormulaire = new JPanel();
			panelFormulaire.setLayout(new GridBagLayout());
		}
		return panelFormulaire;
	}
	
	public GridBagConstraints getGbcRecherche(){
		if(gbcRecherche == null){
			gbcRecherche = new GridBagConstraints();	
			gbcRecherche.insets = new Insets(5, 5, 15, 5);
		}
		return gbcRecherche;
	}
	
	public GridBagConstraints getGbcPrincipal() {
		if(gbcPrincipal == null){
			gbcPrincipal = new GridBagConstraints();	
			gbcPrincipal.insets = new Insets(5, 5, 15, 5);			
		}
		return gbcPrincipal;
	}
	
	public GridBagConstraints getGbcFormulaire(){
		if(gbcFormulaire == null){
			gbcFormulaire = new GridBagConstraints();	
			gbcFormulaire.insets = new Insets(5, 5, 10, 5);	
		}		
		return gbcFormulaire;
	}
	
	public GridBagConstraints getGbcButtonAnimaux(){
		if(gbcButtonAnimaux == null){
			gbcButtonAnimaux= new GridBagConstraints();
			gbcButtonAnimaux.insets = new Insets(0, 5, 15, 5);	
		}
		return gbcButtonAnimaux;
	}

	public JScrollPane getScrollPanel() {
		if (scrollPanel == null) {
			scrollPanel = new JScrollPane(getTabAnimaux(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanel.setPreferredSize(new Dimension(600,200));
		}
		return scrollPanel;
	}
	
	public JTable getTabAnimaux() {
		if (tabAnimaux == null) {
			tabAnimaux = new JTable(6,7);
			tabAnimaux.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabAnimaux.getColumnModel().getColumn(0).setPreferredWidth(100);
		    tabAnimaux.getColumnModel().getColumn(0).setHeaderValue("Numéro");
			tabAnimaux.getColumnModel().getColumn(1).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(1).setHeaderValue("Nom");
			tabAnimaux.getColumnModel().getColumn(2).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(2).setHeaderValue("Sexe");
			tabAnimaux.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(3).setHeaderValue("Couleur");
			tabAnimaux.getColumnModel().getColumn(4).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(4).setHeaderValue("Race");
			tabAnimaux.getColumnModel().getColumn(5).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(5).setHeaderValue("Espece");
			tabAnimaux.getColumnModel().getColumn(6).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(6).setHeaderValue("Tatouage");
			
			tabAnimaux.getTableHeader().resizeAndRepaint();
		}
		return tabAnimaux;
	}

	public JButton getbAjouterClient() {
		if (bAjouterClient == null) {
			bAjouterClient = new JButton("+");
			bAjouterClient.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bAjouterClient;
	}

	public JButton getbSupprimerClient() {
		if (bSupprimerClient == null) {
			bSupprimerClient = new JButton("Supprimer");
			bSupprimerClient.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bSupprimerClient;
	}

	public JButton getbValider() {
		if (bValider == null) {
			bValider = new JButton("Valider");
			bValider.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bValider;
	}

	public JButton getbAnnuler() {
		if (bAnnuler == null) {
			bAnnuler = new JButton("Annuler");
			bAnnuler.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bAnnuler;
	}

	public JButton getbRechercher() {
		if (bRechercher == null) {
			bRechercher = new JButton("Recherche");
			bRechercher.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bRechercher;
	}
	

	public JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField(15);			
		}
		return txtNom;
	}

	public JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField(15);			
		}
		return txtPrenom;
	}

	public JTextField getTxtCodeClient() {
		if (txtCodeClient == null) {
			txtCodeClient = new JTextField(15);			
		}
		return txtCodeClient;
	}

	public JTextField getTxtAdresse() {
		if (txtAdresse == null) {
			txtAdresse = new JTextField(15);			
		}
		return txtAdresse;
	}

	public JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField(15);			
		}
		return txtCodePostal;
	}

	public JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField(15);			
		}
		return txtVille;
	}
	
	public JButton getbAjouterAnimal() {
		if(bAjouterAnimal == null){
			bAjouterAnimal = new JButton("Ajouter");
			bAjouterAnimal.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bAjouterAnimal;
	}

	public JButton getbSupprimerAnimal() {
		if(bSupprimerAnimal == null){
			bSupprimerAnimal = new JButton("Supprimer");
			bSupprimerAnimal.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bSupprimerAnimal;
	}

	public JButton getbEditerAnimal() {
		if(bEditerAnimal == null){
			bEditerAnimal = new JButton("Editer");
			bEditerAnimal.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
		}
		return bEditerAnimal;
	}

	public JPanel getPanelTabAnimaux() {
		if(panelTabAnimaux == null){
			panelTabAnimaux = new JPanel();
			panelTabAnimaux.add(getScrollPanel());
		}
		return panelTabAnimaux;
	}
	
	public JPanel getPanelButtonAnimaux() {
		if(panelButtonAnimal == null){
			panelButtonAnimal = new JPanel();
			panelButtonAnimal.setLayout(new GridBagLayout());
		}
		return panelButtonAnimal;
	}
	
	public JTextField getTxtComplementAdresse() {
		if(txtComplementAdresse == null){
			txtComplementAdresse = new JTextField(15);
		}
		return txtComplementAdresse;
	}
}
