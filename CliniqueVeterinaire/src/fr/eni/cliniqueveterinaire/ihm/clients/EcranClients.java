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
	public GridBagConstraints gbcPrincipal; 
	public JButton bRechercher;
	public JButton bAjouterClient;
	public JButton bSupprimerClient;
	public JButton bValider;
	public JButton bAnnuler;
	public JTextField txtNom;
	public JTextField txtPrenom;
	public JTextField txtCodeClient;
	public JTextField txtAdresse;
	public JTextField txtCodePostal;
	public JTextField txtVille;
	public JTable tabAnimaux;
	public JScrollPane scrollPanel ;
	public JPanel panelTabAnimaux;
	public JButton bAjouterAnimal;
	public JButton bSupprimerAnimal;
	public JButton bEditerAnimal;
	public JPanel panelButtonAnimal;

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
		setContentPane(listeClients());
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	//Méthode de construction de l'ihm
	private JPanel listeClients(){
		if (panelPrincipal == null){
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new GridBagLayout());
			gbcPrincipal = new GridBagConstraints();	
			gbcPrincipal.insets = new Insets(5, 5, 15, 5);
			GridBagConstraints gbcRecherche = new GridBagConstraints();	
			gbcRecherche.insets = new Insets(5, 5, 15, 5);
			panelRecherche = new JPanel();
			panelRecherche.setLayout(new GridBagLayout());
			panelRecherche.setBorder(BorderFactory.createLineBorder(Color.black));
			//gbcRecherche.fill = GridBagConstraints.HORIZONTAL;
			panelFormulaire = new JPanel();
			
			GridBagConstraints gbcFormulaire = new GridBagConstraints();	
			gbcFormulaire.insets = new Insets(5, 5, 10, 5);
			panelFormulaire.setLayout(new GridBagLayout());
			
			
			// Section barre de contrôle
			
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=0;
			panelRecherche.add(getbRechercher());
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=1;
			panelRecherche.add(new JLabel("             "));
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=2;
			panelRecherche.add(new JLabel("             "));
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=3;
			panelRecherche.add(getbAjouterClient());
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=4;
			panelRecherche.add(getbSupprimerClient());
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=5;
			panelRecherche.add(new JLabel("                         "));
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=6;
			panelRecherche.add(getbValider());
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=7;
			panelRecherche.add(getbAnnuler());
			gbcPrincipal.gridx=0;
			gbcPrincipal.gridy=0;
			gbcPrincipal.gridwidth = 2;
			panelPrincipal.add(getPanelRecherche(),gbcPrincipal);
			
			//Section formulaire Clients
			
			gbcFormulaire.gridx=0;
			gbcFormulaire.gridy=0;
			panelFormulaire.add(new JLabel("Code"),gbcFormulaire);
			gbcFormulaire.gridx=1;
			gbcFormulaire.gridy=0;
			panelFormulaire.add(txtCodeClient = new JTextField(15),gbcFormulaire);
			gbcFormulaire.gridx=0;
			gbcFormulaire.gridy=1;
			panelFormulaire.add(new JLabel("Nom"),gbcFormulaire);
			gbcFormulaire.gridx=1;
			gbcFormulaire.gridy=1;
			panelFormulaire.add(txtNom = new JTextField(15),gbcFormulaire);
			gbcFormulaire.gridx=0;
			gbcFormulaire.gridy=2;
			panelFormulaire.add(new JLabel("Prénom"),gbcFormulaire);
			gbcFormulaire.gridx=1;
			gbcFormulaire.gridy=2;
			panelFormulaire.add(txtPrenom = new JTextField(15),gbcFormulaire);			
			gbcFormulaire.gridx=0;
			gbcFormulaire.gridy=3;
			panelFormulaire.add(new JLabel("Adresse"),gbcFormulaire);
			gbcFormulaire.gridx=1;
			gbcFormulaire.gridy=3;
			panelFormulaire.add(txtAdresse = new JTextField(15),gbcFormulaire);
			gbcFormulaire.gridx=0;
			gbcFormulaire.gridy=4;
			panelFormulaire.add(new JLabel("Code Postal"),gbcFormulaire);
			gbcFormulaire.gridx=1;
			gbcFormulaire.gridy=4;
			panelFormulaire.add(txtCodePostal = new JTextField(15),gbcFormulaire);
			gbcFormulaire.gridx=0;
			gbcFormulaire.gridy=5;
			panelFormulaire.add(new JLabel("Ville"),gbcFormulaire);
			gbcFormulaire.gridx=1;
			gbcFormulaire.gridy=5;
			panelFormulaire.add(txtVille = new JTextField(15),gbcFormulaire);
					
			gbcPrincipal.gridx=0;
			gbcPrincipal.gridy=1;
			gbcPrincipal.gridwidth = 1;
			panelPrincipal.add(getPanelFormulaire(),gbcPrincipal);
			
			
			//Section tableau animaux
			
			gbcPrincipal.gridx=1;
			gbcPrincipal.gridy=1;
			panelPrincipal.add(getPanelTabAnimaux(),gbcPrincipal);	
			getPanelTabAnimaux().add(getScrollPanel());
			gbcPrincipal.gridx=1;
			gbcPrincipal.gridy=2;
			panelPrincipal.add(getPanelButtonAnimal(),gbcPrincipal);
			getPanelButtonAnimal().setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(0, 5, 15, 5);
			constraints.gridx=0;
			constraints.gridy=0;
			getPanelButtonAnimal().add(getbAjouterAnimal(),constraints);
			constraints.gridx=1;
			constraints.gridy=0;
			getPanelButtonAnimal().add(getbSupprimerAnimal(),constraints);
			constraints.gridx=2;
			constraints.gridy=0;
			getPanelButtonAnimal().add(getbEditerAnimal(),constraints);			
			
		}
		return panelPrincipal;
	}
	
	
	//-----------------------------------getters / setters----------------------------------------
	

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
	
	public JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
		}
		return panelPrincipal;
	}

	public GridBagConstraints getGbcPrincipal() {
		return gbcPrincipal;
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


	public JPanel getPanelRecherche() {
		return panelRecherche;
	}
	
	public JPanel getPanelFormulaire() {
		return panelFormulaire;
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
		}
		return panelTabAnimaux;
	}
	
	public JPanel getPanelButtonAnimal() {
		if(panelButtonAnimal == null){
			panelButtonAnimal = new JPanel();
		}
		return panelButtonAnimal;
	}
}
