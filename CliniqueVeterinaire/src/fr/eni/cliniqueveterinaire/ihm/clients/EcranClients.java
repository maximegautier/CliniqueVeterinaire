package fr.eni.cliniqueveterinaire.ihm.clients;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.Update;
import fr.eni.cliniqueveterinaire.log.LogFactory;


public class EcranClients extends JPanel implements Update{
	private static EcranClients instance;
	
	//Composants Java Swing � int�grer � l'ihm
	private ModeleTableAnimauxClients modele;
	private JPanel panelPrincipal;
	private JPanel panelRecherche;
	private JPanel panelFormulaire;
	private JPanel panelButtonAnimal;
	private GridBagConstraints gbcPrincipal; 
	private GridBagConstraints gbcRecherche;
	private GridBagConstraints gbcFormulaire;
	private GridBagConstraints gbcButtonAnimaux;
	private JButton bRechercher;
	private JButton bAjouterClient;
	private JButton bSupprimerClient;
	private JButton bValider;
	private JButton bAnnuler;
	private JButton bClientSuivant;
	private JButton bClientPrecedent;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtCodeClient;
	private JTextField txtAdresse;
	private JTextField txtComplementAdresse;
	private JTextField txtCodePostal;
	private JTextField txtVille;
	private JTextField txtNumTel;
	private JTextField txtEmail;
	private JTextField txtRemarque;
	private JTextField txtAssurance;
	private JTable tabAnimaux;
	private JScrollPane scrollPanel ;
	private JPanel panelTabAnimaux;
	private JButton bAjouterAnimal;
	private JButton bSupprimerAnimal;
	private JButton bEditerAnimal;
	private JDialog dialFen;
	private JDialog dialFenEchec;
	private JDialog dialFenSucces;
	private JDialog dialFenSupprimer;
	
	private int codeClient = 1;
	private int codeAnimal = 1;

	//M�thode static de r�cup�ration d'une instance unique de la fen�tre 
	public static EcranClients getInstance() throws BLLException{
		if (EcranClients.instance == null){
			EcranClients.instance = new EcranClients();
		}
		return instance;
	}

	//Constructeur
	private EcranClients() throws BLLException {
		setPreferredSize(new Dimension(700,500));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);	
		gbc.gridx=0;
		gbc.gridx=0;
		add(initEcranClients(),gbc);
		setVisible(true);
		
		codeClient = ClientsManager.getInstance().selectFirst().getCodeClient();
		EcranClientsController.getInstance().startApp(this);
	}
		
	private JPanel initBarreRecherche(){			
		getPanelRecherche().setPreferredSize(new Dimension(this.getPreferredSize().width -15,getbRechercher().getPreferredSize().height+12));
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
		getPanelFormulaire().setPreferredSize(new Dimension(this.getPreferredSize().width/2 -100,this.getPreferredSize().height-(getPanelRecherche().getPreferredSize().height+80)));
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
		getPanelFormulaire().add(new JLabel("Pr�nom"),getGbcFormulaire());
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
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=7;
		getPanelFormulaire().add(new JLabel("E-mail"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=7;
		getPanelFormulaire().add(getTxtEmail(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=8;
		getPanelFormulaire().add(new JLabel("Num�ro"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=8;
		getPanelFormulaire().add(getTxtNumTel(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=9;
		getPanelFormulaire().add(new JLabel("Assurance"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=9;
		getPanelFormulaire().add(getTxtAssurance(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=10;
		getPanelFormulaire().add(new JLabel("Remarques"),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=10;
		getPanelFormulaire().add(getTxtRemarque(),getGbcFormulaire());
		getGbcFormulaire().gridx=0;
		getGbcFormulaire().gridy=11;
		getPanelFormulaire().add(getbClientPrecedent(),getGbcFormulaire());
		getGbcFormulaire().gridx=1;
		getGbcFormulaire().gridy=11;
		getPanelFormulaire().add(getbClientSuivant(),getGbcFormulaire());
			
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
	
	//M�thode de construction de l'ihm
	private JPanel initEcranClients() throws BLLException{
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
		
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel lePanel = new JPanel();
		lePanel.setLayout(new GridBagLayout());
		gbc.gridx=0;
		gbc.gridy=0;
		lePanel.add(getPanelTabAnimaux(),gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		lePanel.add(initSectionAnimaux(),gbc);
	
		getGbcPrincipal().gridx=1;
		getGbcPrincipal().gridy=1;
		getGbcPrincipal().gridheight=2;
		getPanelPrincipal().add(lePanel,getGbcPrincipal());	
		
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
			panelFormulaire.setBorder(BorderFactory.createLineBorder(Color.black));
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
			gbcPrincipal.insets = new Insets(5, 5, 5, 5);			
		}
		return gbcPrincipal;
	}
	
	public GridBagConstraints getGbcFormulaire(){
		if(gbcFormulaire == null){
			gbcFormulaire = new GridBagConstraints();	
			gbcFormulaire.insets = new Insets(5, 5, 5, 5);	
		}		
		return gbcFormulaire;
	}
	
	public GridBagConstraints getGbcButtonAnimaux(){
		if(gbcButtonAnimaux == null){
			gbcButtonAnimaux= new GridBagConstraints();
			gbcButtonAnimaux.insets = new Insets(0, 5, 2, 5);	
		}
		return gbcButtonAnimaux;
	}

	public JScrollPane getScrollPanel() throws BLLException {
		if (scrollPanel == null) {
			scrollPanel = new JScrollPane(getTabAnimaux(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanel.setPreferredSize(new Dimension(400,200));
		}
		return scrollPanel;
	}
	
	public JTable getTabAnimaux() throws BLLException {
		if (tabAnimaux == null) {
			tabAnimaux = new JTable(new ModeleTableAnimauxClients(AnimauxManager.selectAnimaux(codeClient)));
			tabAnimaux.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabAnimaux.getColumnModel().getColumn(0).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(1).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(2).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(4).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(5).setPreferredWidth(100);
			tabAnimaux.getColumnModel().getColumn(6).setPreferredWidth(100);
						
			tabAnimaux.getTableHeader().resizeAndRepaint();
		}
		return tabAnimaux;
	}

	public JButton getbAjouterClient() {
		if (bAjouterClient == null) {
			bAjouterClient = new JButton("Ajouter",new ImageIcon("ressources/icons8-ajouter-un-utilisateur-homme-16.png"));
			bAjouterClient.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					EcranClientsController.getInstance().clickClientsAjouter();
				}
			});
		}
		return bAjouterClient;
	}

	public JButton getbSupprimerClient() {
		if (bSupprimerClient == null) {
			bSupprimerClient = new JButton("Supprimer",new ImageIcon("ressources/icons8-supprimer-l'utilisateur-homme-16.png"));
			bSupprimerClient.setPreferredSize(new Dimension(100,50));
			bSupprimerClient.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int leCodeClient = getCodeClient();
						setCodeClient(EcranClientsController.getInstance().clickClientsPrecedent(getCodeClient()));
						EcranClientsController.getInstance().clickClientsSupprime(leCodeClient);
						LogFactory.getLog().createLog(Level.INFO, "Client supprim�");
					} catch (BLLException | DALException e1) {
						LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
					}
				}
			});
		}
		return bSupprimerClient;
	}

	public JButton getbValider() {
		if (bValider == null) {
			bValider = new JButton("Valider",new ImageIcon("ressources/icons8-coche-filled-16.png"));
			bValider.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					getJDialogModification().setVisible(true);
				}
			});
		}
		return bValider;
	}

	public JButton getbAnnuler() {
		if (bAnnuler == null) {
			bAnnuler = new JButton("Annuler",new ImageIcon("ressources/icons8-rejouer-16.png"));
			bAnnuler.setPreferredSize(new Dimension(bAnnuler.getPreferredSize().width+50,bAnnuler.getPreferredSize().height));
			bAnnuler.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						EcranClientsController.getInstance().clickAnnuler(getCodeClient());
					} catch (BLLException e1) {
						LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
					}
				}
			});
		}
		return bAnnuler;
	}

	public JButton getbRechercher() {
		if (bRechercher == null) {
			bRechercher = new JButton("Recherche",new ImageIcon("ressources/icons8-trouver-l'utilisateur-homme-16.png"));
			bRechercher.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					EcranClientsController.getInstance().clickClientsRecherche();
				}
			});
		}
		return bRechercher;
	}
	

	public JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField(10);			
		}
		return txtNom;
	}

	public JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField(10);			
		}
		return txtPrenom;
	}

	public JTextField getTxtCodeClient() {
		if (txtCodeClient == null) {
			txtCodeClient = new JTextField(10);			
		}
		return txtCodeClient;
	}

	public JTextField getTxtAdresse() {
		if (txtAdresse == null) {
			txtAdresse = new JTextField(10);			
		}
		return txtAdresse;
	}

	public JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField(10);			
		}
		return txtCodePostal;
	}

	public JTextField getTxtVille() {
		if (txtVille == null) {
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

	public JButton getbAjouterAnimal() {
		if(bAjouterAnimal == null){
			bAjouterAnimal = new JButton("Ajouter",new ImageIcon("ressources/icons8-plus-16.png"));
			bAjouterAnimal.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					EcranClientsController.getInstance().clickAjouterAnimaux(getCodeClient());
				}
			});
		}
		return bAjouterAnimal;
	}

	public JButton getbSupprimerAnimal() {
		if(bSupprimerAnimal == null){
			bSupprimerAnimal = new JButton("Supprimer",new ImageIcon("ressources/icons8-moins-16.png"));
			bSupprimerAnimal.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int indiceLigne = getTabAnimaux().getSelectedRow();
						if(indiceLigne != -1){
							EcranClientsController.getInstance().clickSupprimerAnimaux(getCodeClient(),Integer.parseInt((String) getTabAnimaux().getValueAt(indiceLigne, 0)));
							LogFactory.getLog().createLog(Level.INFO, "Animal supprim�");
						}else{
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne du tableau", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (BLLException e1) {
						LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
					}
				}
			});
		}
		return bSupprimerAnimal;
	}

	public JButton getbEditerAnimal() {
		if(bEditerAnimal == null){
			bEditerAnimal = new JButton("Editer",new ImageIcon("ressources/icons8-modifier-16.png"));
			bEditerAnimal.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int indiceLigne = getTabAnimaux().getSelectedRow();
						if(indiceLigne != -1){
							EcranClientsController.getInstance().clickEditerAnimaux(getCodeClient(),Integer.parseInt((String) getTabAnimaux().getValueAt(indiceLigne, 0)));
							LogFactory.getLog().createLog(Level.INFO, "Animal modifi�");
						}else{
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne du tableau", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (NumberFormatException | BLLException e1) {
						LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
					}					
				}
			});
		}
		return bEditerAnimal;
	}

	public JPanel getPanelTabAnimaux() throws BLLException {
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
			txtComplementAdresse = new JTextField(10);
		}
		return txtComplementAdresse;
	}
	
	public JButton getbClientSuivant() {
		if(bClientSuivant == null){
			bClientSuivant = new JButton("",new ImageIcon("ressources/icons8-droite-16.png"));
			bClientSuivant.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
				try {
						try {
							codeClient = EcranClientsController.getInstance().clickClientsSuivant(getCodeClient());
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (BLLException e1) {
						LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
					}
				}
			});
		}
		return bClientSuivant;
	}

	public JButton getbClientPrecedent() {
		if(bClientPrecedent == null){
			bClientPrecedent = new JButton("",new ImageIcon("ressources/icons8-gauche-16.png"));
			bClientPrecedent.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						codeClient = EcranClientsController.getInstance().clickClientsPrecedent(getCodeClient());
					} catch (BLLException e1) {
						LogFactory.getLog().createLog(Level.SEVERE, e1.getMessage());
					}
				}
			});
		}
		return bClientPrecedent;
	}
	
	public int getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	
	public ModeleTableAnimauxClients getModele() {
		if(modele == null){
			modele = new ModeleTableAnimauxClients(new ArrayList<Animaux>());
		}
		return modele;
	}

	public void setModele(ModeleTableAnimauxClients modele) {
		this.modele = modele;
	}
	
	public JDialog getJDialogModification(){
		if(this.dialFen == null){
			dialFen= new JDialog();
			dialFen.setModal(true);
			dialFen.setTitle("Attention");
			String retour = "Etes vous s�r de vouloir faire �a ? Toutes les";
			String retour2 = "modifications apport�es au formulaire seront enregistr�es";
			JPanel lePanel = new JPanel();
			JButton bValider = new JButton("Valider");
			JButton bAnnuler = new JButton("Annuler");			
			bValider.setPreferredSize(new Dimension(100,30));	
			bAnnuler.setPreferredSize(new Dimension(100,30));
			GridBagConstraints gbc  = new GridBagConstraints();
			gbc.insets = new Insets(5,5,5,5);
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridwidth = 2;
			lePanel.add(new JLabel(retour));
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.gridwidth = 2;
			lePanel.add(new JLabel(retour2));
			gbc.gridx=0;
			gbc.gridy=3;
			gbc.gridwidth = 1;
			lePanel.add(bValider);
			gbc.gridx=1;
			gbc.gridy=3;
			lePanel.add(bAnnuler);
			dialFen.add(lePanel);
			dialFen.setLocationRelativeTo(null);
			dialFen.setSize(400, 150);
			bValider.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(verifieSaisie()){
						Clients modifClient = new Clients(getCodeClient(),getTxtNom().getText(),
								getTxtPrenom().getText(),getTxtAdresse().getText(),
								getTxtComplementAdresse().getText(),getTxtCodePostal().getText(),
								getTxtVille().getText(),getTxtNumTel().getText(),getTxtAssurance().getText(),
								getTxtEmail().getText(),getTxtRemarque().getText(),false);
						try {
							EcranClientsController.getInstance().clickClientsModifier(modifClient);
						} catch (BLLException e1) {
							e1.printStackTrace();
						}
						dialFen.setVisible(false);
						JOptionPane.showMessageDialog(null, "Saisie r�ussie", "Information", JOptionPane.INFORMATION_MESSAGE);
					}else{
						dialFen.setVisible(false);
						JOptionPane.showMessageDialog(null, "Veuillez renseigner au moins le nom et le pr�nom", "Attention", JOptionPane.INFORMATION_MESSAGE);
						videChamps();
						try {
							EcranClientsController.getInstance().remplirChamps(getCodeClient());
						} catch (BLLException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			});
			bAnnuler.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					videChamps();
					try {
						EcranClientsController.getInstance().remplirChamps(getCodeClient());
					} catch (BLLException e1) {
						
					}
					dialFen.setVisible(false);
				}
			});
		}
		return dialFen;
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
			String retour = "Veuillez renseigner au moins le nom et le pr�nom.";
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
	
	public JDialog getJDialogErreurSuppression(){
		if(this.dialFenSupprimer == null){
			dialFenSupprimer = new JDialog();
			dialFenSupprimer.setModal(true);
			dialFenSupprimer.setTitle("Attention");
			String retour = "Veuillez selectionner au une ligne du tableau";
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
			dialFenSupprimer.add(lePanel);
			dialFenSupprimer.setLocationRelativeTo(null);
			dialFenSupprimer.setSize(350, 150);
			bOk.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dialFenSupprimer.setVisible(false);
				}
			});
		}
		return dialFenSupprimer;
	}
	
	public JDialog getJDialogSaisieReussie(){
		if(this.dialFenSucces == null){
			dialFenSucces = new JDialog();
			dialFenSucces.setModal(true);
			dialFenSucces.setTitle("Information");
			String retour = "Nouveau client modifi� avec succ�s";
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
			dialFenSucces.add(lePanel);
			dialFenSucces.setLocationRelativeTo(null);
			dialFenSucces.setSize(300, 100);
			bOk.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dialFenSucces.setVisible(false);
				}
			});
		}
		return dialFenSucces;
	}
	
	public void videChamps(){
		this.getTxtNom().setText("");
		this.getTxtPrenom().setText("");
		this.getTxtAdresse().setText("");
		this.getTxtComplementAdresse().setText("");
		this.getTxtCodePostal().setText("");
		this.getTxtVille().setText("");
		this.getTxtEmail().setText("");
		this.getTxtAssurance().setText("");
		this.getTxtRemarque().setText("");
		this.getTxtNumTel().setText("");
	}

	@Override
	public void updatePanPriseRdv() {
	}
}
