package fr.eni.cliniqueveterinaire.ihm.clients;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import fr.eni.cliniqueveterinaire.bll.BLLException;

public class EcranRechercheClients extends JFrame{
	
	private static EcranRechercheClients instance ;

	
	//Composants Java Swing à intégrer à l'ihm
		public JPanel panelPrincipal;
		public JPanel panelRecherche;
		public JPanel panelListe;
		public GridBagConstraints gbcPrincipal;
		public JButton bRechercher;
		public JTextField txtRecherche;
		public JList listeClients;
		public ListModel<String> maListe;
		public String[] laListe;
		public int tailleListe ;
		public JScrollPane scrollPanel ;
	
	//Méthode static de récupération d'une instance unique de la fenêtre 
		public static EcranRechercheClients getInstance(){
			if (EcranRechercheClients.instance == null){
				EcranRechercheClients.instance = new EcranRechercheClients();
			}
			return instance;
		}

		//Constructeur
		private EcranRechercheClients() {
			setSize(550, 300);
			setResizable(true);
			setTitle("Recherche Client");
			setContentPane(rechercheClient());
			setVisible(true);
			setLocationRelativeTo(null);
		}
		
		private JPanel rechercheClient(){
			if (panelPrincipal == null){
				panelPrincipal = new JPanel();
				panelPrincipal.setLayout(new GridBagLayout());
				gbcPrincipal = new GridBagConstraints();	
				gbcPrincipal.insets = new Insets(5, 5, 15, 5);
				
				
				gbcPrincipal.gridx=0;
				gbcPrincipal.gridy=0;
				gbcPrincipal.gridwidth=2;
				panelPrincipal.add(getPanelRecherche(),gbcPrincipal);
				panelPrincipal.setBorder(new EmptyBorder(1, 1, 1, 1));
				getPanelRecherche().setBorder(BorderFactory.createLineBorder(Color.black));
				getPanelRecherche().add(new JLabel("                       "));
				getPanelRecherche().add(getTxtRecherche());
				getPanelRecherche().add(new JLabel("          "));
				getPanelRecherche().add(getbRechercher());
				getPanelRecherche().add(new JLabel("                       "));
				gbcPrincipal.gridx=0;
				gbcPrincipal.gridy=1;
				gbcPrincipal.gridwidth=2;
				panelPrincipal.add(getPanelListe(),gbcPrincipal);
				getPanelListe().setLayout(new GridBagLayout());
				getPanelListe().setBorder(BorderFactory.createLineBorder(Color.black));
				GridBagConstraints gbcRecherche = new GridBagConstraints();	
				//gbcRecherche.fill = GridBagConstraints.HORIZONTAL;
				gbcRecherche.insets = new Insets(5, 5, 15, 5);
				gbcRecherche.gridx=0;
				gbcRecherche.gridy=0;
				getPanelListe().add(getScrollPanel(),gbcRecherche);
				
			}
			return panelPrincipal;
		}

		
	//--------------------------------------Accesseurs ------------------------------------
		
		public JPanel getPanelPrincipal() {
			if(panelPrincipal == null){
				panelPrincipal = new JPanel();
			}
			return panelPrincipal;
		}

		public JPanel getPanelRecherche() {
			if(panelRecherche == null){
				panelRecherche = new JPanel();
			}
			return panelRecherche;
		}

		public JPanel getPanelListe() {
			if(panelListe == null){
				panelListe = new JPanel();
			}
			return panelListe;
		}

		public GridBagConstraints getGbcPrincipal() {
			if(gbcPrincipal == null){
				gbcPrincipal = new GridBagConstraints();
			}
			return gbcPrincipal;
		}

		public JButton getbRechercher() {
			if(bRechercher == null){
				bRechercher = new JButton("Rechercher");
				bRechercher.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							EcranClientsController.getInstance().clickRechercheClients(getTxtRecherche().getText());
						} catch (BLLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			return bRechercher;
		}

		public JTextField getTxtRecherche() {
			if(txtRecherche == null){
				txtRecherche = new JTextField(15);
			}
			return txtRecherche;
		}

		public JList getListeClients() {
			tailleListe = 9;
			laListe = new String[tailleListe];
			if(listeClients == null){
				listeClients = new JList(laListe);
			}
			return listeClients;
		}
		
		public JScrollPane getScrollPanel() {
			if (scrollPanel == null) {
				scrollPanel = new JScrollPane(getListeClients(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPanel.setPreferredSize(new Dimension(400,150));
			}
			return scrollPanel;
		}
		
}
