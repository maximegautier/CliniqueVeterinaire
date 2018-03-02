package fr.eni.cliniqueveterinaire.ihm.clients;

import java.awt.Color;
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
import javax.swing.JTextField;

public class EcranAjoutClients extends JFrame{
	private static EcranAjoutClients instance;
	
	//Composants Java Swing à intégrer à l'ihm
	public JPanel panelPrincipal;
	public JPanel panelButton;
	public JPanel panelFormulaire;
	public GridBagConstraints gbcPrincipal;
	public JButton bValider;
	public JButton bAnnuler;
	public JTextField txtNom;
	public JTextField txtPrenom;
	public JTextField txtAdresse;
	public JTextField txtComplementAdresse;
	public JTextField txtCodePostal;
	public JTextField txtVille;
	public JLabel labNom;
	public JLabel labPrenom;
	public JLabel labAdresse;
	public JLabel labComplementAdresse;
	public JLabel labCodePostal;
	public JLabel labVille;

	//Méthode static de récupération d'une instance unique de la fenêtre 
	public static EcranAjoutClients getInstance(){
		if (EcranAjoutClients.instance == null){
			EcranAjoutClients.instance = new EcranAjoutClients();
		}
		return instance;
	}

	//Constructeur
	private EcranAjoutClients() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setResizable(true);
		setTitle("Nouveau Client");
		setContentPane(ajoutClient());
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private JPanel ajoutClient(){
		if (panelPrincipal == null){
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new GridBagLayout());
			gbcPrincipal = new GridBagConstraints();	
			gbcPrincipal.insets = new Insets(5, 5, 15, 5);
			
			
			gbcPrincipal.gridx=0;
			gbcPrincipal.gridy=0;
			gbcPrincipal.gridwidth=2;
			panelPrincipal.add(getPanelButton(),gbcPrincipal);
			getPanelButton().setBorder(BorderFactory.createLineBorder(Color.black));
			getPanelButton().add(new JLabel("      "));
			getPanelButton().add(new JLabel("      "));
			getPanelButton().add(new JLabel("       "));
			getPanelButton().add(new JLabel("      "));
			getPanelButton().add(getbValider());
			getPanelButton().add(getbAnnuler());
			gbcPrincipal.gridx=0;
			gbcPrincipal.gridy=1;
			gbcPrincipal.gridwidth=2;
			panelPrincipal.add(getPanelFormulaire(),gbcPrincipal);
			getPanelFormulaire().setLayout(new GridBagLayout());
			getPanelFormulaire().setBorder(BorderFactory.createLineBorder(Color.black));
			GridBagConstraints gbcRecherche = new GridBagConstraints();	
			gbcRecherche.insets = new Insets(5, 5, 15, 5);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=0;
			getPanelFormulaire().add(getLabNom(),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=0;
			getPanelFormulaire().add(getTxtNom(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=1;
			getPanelFormulaire().add(getLabPrenom(),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=1;
			getPanelFormulaire().add(getTxtPrenom(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=2;
			getPanelFormulaire().add(getLabAdresse(),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=2;
			getPanelFormulaire().add(getTxtAdresse(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=3;
			getPanelFormulaire().add(getLabComplementAdresse(),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=3;
			getPanelFormulaire().add(getTxtComplementAdresse(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=4;
			getPanelFormulaire().add(getLabCodePostal(),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=4;
			getPanelFormulaire().add(getTxtCodePostal(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=5;
			getPanelFormulaire().add(getLabVille(),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=5;
			getPanelFormulaire().add(getTxtVille(),gbcRecherche);
		}
		return panelPrincipal;
	}

//----------------------------------Getters / Setters / Accesseurs en fait -------------------------*
	
	public JPanel getPanelPrincipal() {
		if(panelPrincipal == null){
			panelPrincipal = new JPanel();
		}
		return panelPrincipal;
	}

	public JPanel getPanelButton() {
		if(panelButton == null){
			panelButton = new JPanel();
		}
		return panelButton;
	}

	public JPanel getPanelFormulaire() {
		if(panelFormulaire == null){
			panelFormulaire = new JPanel();
		}
		return panelFormulaire;
	}

	public GridBagConstraints getGbcPrincipal() {
		if(gbcPrincipal == null){
			gbcPrincipal = new GridBagConstraints();
		}
		return gbcPrincipal;
	}

	public JButton getbValider() {
		if(bValider == null){
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
		if(bAnnuler == null){
			bAnnuler = new JButton("Annuler");
			bAnnuler.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//A remplir
				}
			});
			//bAnnuler.setPreferredSize(50,26);
		}
		return bAnnuler;
	}

	public JTextField getTxtNom() {
		if(txtNom == null){
			txtNom = new JTextField(15);
		}
		return txtNom;
	}

	public JTextField getTxtPrenom() {
		if(txtPrenom == null){
			txtPrenom = new JTextField(15);
		}
		return txtPrenom;
	}

	public JTextField getTxtAdresse() {
		if(txtAdresse == null){
			txtAdresse = new JTextField(15);
		}
		return txtAdresse;
	}

	public JTextField getTxtComplementAdresse() {
		if(txtComplementAdresse == null){
			txtComplementAdresse = new JTextField(15);
		}
		return txtComplementAdresse;
	}

	public JTextField getTxtCodePostal() {
		if(txtCodePostal == null){
			txtCodePostal = new JTextField(15);
		}
		return txtCodePostal;
	}

	public JTextField getTxtVille() {
		if(txtVille == null){
			txtVille = new JTextField(15);
		}
		return txtVille;
	}

	public JLabel getLabNom() {
		if(labNom == null){
			labNom = new JLabel("Nom");
		}
		return labNom;
	}

	public JLabel getLabPrenom() {
		if(labPrenom == null){
			labPrenom = new JLabel("Prénom");
		}
		return labPrenom;
	}

	public JLabel getLabAdresse() {
		if(labAdresse == null){
			labAdresse = new JLabel("Adresse");
		}
		return labAdresse;
	}

	public JLabel getLabComplementAdresse() {
		if(labComplementAdresse == null){
			labComplementAdresse = new JLabel("Complément");
		}
		return labComplementAdresse;
	}

	public JLabel getLabCodePostal() {
		if(labCodePostal == null){
			labCodePostal = new JLabel("Code Postal");
		}
		return labCodePostal;
	}

	public JLabel getLabVille() {
		if(labVille == null){
			labVille = new JLabel("Ville");
		}
		return labVille;
	}
}
