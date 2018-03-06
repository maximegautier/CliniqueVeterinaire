package fr.eni.cliniqueveterinaire.ihm.clients;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.dal.DALException;

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
	public JTextField txtNumTel;
	public JTextField txtEmail;
	public JTextField txtRemarque;
	public JTextField txtAssurance;
	public JLabel labNom;
	public JLabel labPrenom;
	public JLabel labAdresse;
	public JLabel labComplementAdresse;
	public JLabel labCodePostal;
	public JLabel labVille;
	public JDialog dialFenEchec;
	public JDialog dialFenReussite;

	//Méthode static de récupération d'une instance unique de la fenêtre 
	public static EcranAjoutClients getInstance(){
		if (EcranAjoutClients.instance == null){
			EcranAjoutClients.instance = new EcranAjoutClients();
		}
		return instance;
	}

	//Constructeur
	private EcranAjoutClients() {
		setSize(300,500);
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
			gbcPrincipal.insets = new Insets(5, 5, 10, 5);
			
			
			gbcPrincipal.gridx=0;
			gbcPrincipal.gridy=0;
			gbcPrincipal.gridwidth=2;
			panelPrincipal.add(getPanelButton(),gbcPrincipal);
			getPanelButton().setBorder(BorderFactory.createLineBorder(Color.black));
			getPanelButton().add(getbValider());
			getPanelButton().add(new JLabel("   "));
			getPanelButton().add(new JLabel("    "));
			getPanelButton().add(new JLabel(" "));
			getPanelButton().add(getbAnnuler());
			gbcPrincipal.gridx=0;
			gbcPrincipal.gridy=1;
			gbcPrincipal.gridwidth=2;
			panelPrincipal.add(getPanelFormulaire(),gbcPrincipal);
			getPanelFormulaire().setLayout(new GridBagLayout());
			getPanelFormulaire().setBorder(BorderFactory.createLineBorder(Color.black));
			GridBagConstraints gbcRecherche = new GridBagConstraints();	
			gbcRecherche.insets = new Insets(5, 5, 10, 5);
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
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=6;
			getPanelFormulaire().add(new JLabel("E-mail"),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=6;
			getPanelFormulaire().add(getTxtEmail(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=7;
			getPanelFormulaire().add(new JLabel("Numéro"),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=7;
			getPanelFormulaire().add(getTxtNumTel(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=8;
			getPanelFormulaire().add(new JLabel("Assurance"),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=8;
			getPanelFormulaire().add(getTxtAssurance(),gbcRecherche);
			gbcRecherche.gridx=0;
			gbcRecherche.gridy=9;
			getPanelFormulaire().add(new JLabel("Remarques"),gbcRecherche);
			gbcRecherche.gridx=1;
			gbcRecherche.gridy=9;
			getPanelFormulaire().add(getTxtRemarque(),gbcRecherche);
		}
		return panelPrincipal;
	}

//----------------------------------Getters / Setters / Accesseurs en fait -------------------------
	
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
					if(verifieSaisie()){
						int codeClient = -1;
						Clients newClient = new Clients(getTxtNom().getText(),getTxtPrenom().getText(),
								getTxtAdresse().getText(),getTxtComplementAdresse().getText(),
								getTxtCodePostal().getText(),getTxtVille().getText(),getTxtNumTel().getText(),
								getTxtAssurance().getText(),getTxtEmail().getText(),getTxtRemarque().getText(),
								false);
						try {
							codeClient = EcranClientsController.getInstance().validerNouveauClient(newClient);
						} catch (BLLException | DALException e1) {
							e1.printStackTrace();
						}
						getJDialogSaisieReussie().setVisible(true);
						setVisible(false);
						videChamps();
						try {
							EcranClients.getInstance().setCodeClient(codeClient);
							EcranClientsController.getInstance().startApp(EcranClients.getInstance());
						} catch (BLLException e1) {
							e1.printStackTrace();
						}
					}else{
						getJDialogErreurSaisie().setVisible(true);	
					}
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
					setVisible(false);
					videChamps();
				}
			});
		}
		return bAnnuler;
	}

	public JTextField getTxtNom() {
		if(txtNom == null){
			txtNom = new JTextField(10);
		}
		return txtNom;
	}

	public JTextField getTxtPrenom() {
		if(txtPrenom == null){
			txtPrenom = new JTextField(10);
		}
		return txtPrenom;
	}

	public JTextField getTxtAdresse() {
		if(txtAdresse == null){
			txtAdresse = new JTextField(10);
		}
		return txtAdresse;
	}

	public JTextField getTxtComplementAdresse() {
		if(txtComplementAdresse == null){
			txtComplementAdresse = new JTextField(10);
		}
		return txtComplementAdresse;
	}

	public JTextField getTxtCodePostal() {
		if(txtCodePostal == null){
			txtCodePostal = new JTextField(10);
		}
		return txtCodePostal;
	}

	public JTextField getTxtVille() {
		if(txtVille == null){
			txtVille = new JTextField(10);
		}
		return txtVille;
	}
	
	public JTextField getTxtNumTel() {
		if(this.txtNumTel == null){
			this.txtNumTel = new JTextField(10);
		}
		return txtNumTel;
	}

	public JTextField getTxtEmail() {
		if(this.txtEmail == null){
			this.txtEmail = new JTextField(10);
		}
		return txtEmail;
	}

	public JTextField getTxtRemarque() {
		if(this.txtRemarque == null){
			this.txtRemarque = new JTextField(10);
		}
		return txtRemarque;
	}
	
	public JTextField getTxtAssurance() {
		if(this.txtAssurance == null){
			this.txtAssurance = new JTextField(10);
		}
		return txtAssurance;
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
	
	public void setTxtNom(String txtNom) {
		this.txtNom.setText(txtNom);
	}

	public void setTxtPrenom(String txtPrenom) {
		this.txtPrenom.setText(txtPrenom);
	}

	public void setTxtAdresse(String txtAdresse) {
		this.txtAdresse.setText(txtAdresse);
	}

	public void setTxtComplementAdresse(String txtComplementAdresse) {
		this.txtComplementAdresse.setText(txtComplementAdresse);
	}

	public void setTxtCodePostal(String txtCodePostal) {
		this.txtCodePostal.setText(txtCodePostal);
	}

	public void setTxtVille(String txtVille) {
		this.txtVille.setText(txtVille);
	}
	
	public void videChamps(){
		this.setTxtNom("");
		this.setTxtPrenom("");
		this.setTxtAdresse("");
		this.setTxtComplementAdresse("");
		this.setTxtCodePostal("");
		this.setTxtVille("");
	}
	
	public boolean verifieSaisie(){
		boolean res = true;
		if(getTxtNom().getText().equals("") || getTxtPrenom().getText().equals("")){					
			res = false;
		}
		return res;
	}
	
	public JDialog getJDialogErreurSaisie(){
		if(this.dialFenEchec == null){
			dialFenEchec = new JDialog();
			dialFenEchec.setModal(true);
			dialFenEchec.setTitle("Attention");
			String retour = "Veuillez renseigner au moins le nom et le prénom.";
			JPanel lePanel = new JPanel();
			JButton bOk = new JButton("OK");
			bOk.setPreferredSize(new Dimension(100,30));
			GridBagConstraints gbc  = new GridBagConstraints();
			gbc.insets = new Insets(5,5,5,5);
			gbc.gridx=0;
			gbc.gridy=1;
			lePanel.add(new JLabel(retour));
			gbc.gridx=0;
			gbc.gridy=3;
			gbc.gridwidth = 2;
			lePanel.add(bOk);
			dialFenEchec.add(lePanel);
			dialFenEchec.setLocationRelativeTo(null);
			dialFenEchec.setSize(350, 150);
			bOk.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dialFenEchec.setVisible(false);
				}
			});
		}
		return dialFenEchec;
	}
	
	public JDialog getJDialogSaisieReussie(){
		if(this.dialFenReussite == null){
			dialFenReussite = new JDialog();
			dialFenReussite.setModal(true);
			dialFenReussite.setTitle("Information");
			String retour = "Nouveau client enregistré avec succès";
			JPanel lePanel = new JPanel();
			JButton bOk = new JButton("OK");
			bOk.setPreferredSize(new Dimension(100,30));
			GridBagConstraints gbc  = new GridBagConstraints();
			gbc.insets = new Insets(5,5,5,5);
			gbc.gridx=0;
			gbc.gridy=1;
			lePanel.add(new JLabel(retour));
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.gridwidth = 2;
			lePanel.add(bOk);
			dialFenReussite.add(lePanel);
			dialFenReussite.setLocationRelativeTo(null);
			dialFenReussite.setSize(300, 100);
			bOk.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dialFenReussite.setVisible(false);
				}
			});
		}
		return dialFenReussite;
	}
}
