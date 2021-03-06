package fr.eni.cliniqueveterinaire.ihm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.ihm.agenda.PanAgenda;
import fr.eni.cliniqueveterinaire.ihm.agenda.PanPriseRdv;
import fr.eni.cliniqueveterinaire.ihm.clients.EcranClients;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLoginController;
import fr.eni.cliniqueveterinaire.ihm.personnels.PanPersonnels;

public class EcranMenu extends JFrame{
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mFichier, mRDV;
	private JMenuItem miDeconnexion, miFermer;
	private JMenuItem miRDV, miClient;
	private JMenuItem mAgenda, mGDP;
	
	public EcranMenu() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);		
		setSize(700, 500);
		setResizable(false);
		setTitle("Clinique Veterinaire");
		setIconImage(new ImageIcon("ressources/ico_veto.png").getImage());
		
		menuBar.add(getMFichier());
		
		switch (selectRolePersonnel(EcranLoginController.getCurrentPersonnel()))
		{
			case "adm" :
				menuBar.add(getMRDV());
				menuBar.add(getMAgenda());
				menuBar.add(getMGDP());
				setContentPane(PanPersonnels.getInstance());
				break;
			case "vet":
				menuBar.add(getMAgenda());
				setContentPane(PanAgenda.getInstance());
				break;
			case "sec":
				menuBar.add(getMRDV());
				setContentPane(PanPriseRdv.getInstance());
				break;
			case "ass":
				System.out.println("role assistant : pas de fenetre");
				break;
		}
		
		setJMenuBar(menuBar);
		
		this.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
		        int exit = JOptionPane.showConfirmDialog(null,"�tes-vous s�r de vouloir quitter?","Quitter?",JOptionPane.YES_NO_OPTION);
		        if (exit == JOptionPane.YES_OPTION) {
		        	EcranLoginController.getInstance().fermeture();
		        }		        
		  }
		} );
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public JMenu getMFichier(){
		if (mFichier == null) {
			mFichier = new JMenu("Fichier");
			mFichier.add(getMiDeconnexion());
			mFichier.add(getMiFermer());
		}
		return mFichier;
	}
	
	public JMenu getMRDV(){
		if (mRDV == null) {
			mRDV = new JMenu("Gestion des rendez-vous");
			mRDV.add(getMiRDV());
			mRDV.add(getMiClient());
		}
		return mRDV;
	}
	
	public JMenuItem getMAgenda(){
		if (mAgenda == null) {
			mAgenda = new JMenuItem("Agenda");
			mAgenda.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					setContentPane(PanAgenda.getInstance());
					repaint();
					validate();
				}
			});
		}
		return mAgenda;
	}
	
	public JMenuItem getMGDP(){
		if (mGDP == null) {
			mGDP = new JMenuItem("Gestion du personnel");
			mGDP.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					setContentPane(PanPersonnels.getInstance());
					repaint();
					revalidate();
				}
			});
		}
		return mGDP;
	}
	
	public JMenuItem getMiDeconnexion(){
		if (miDeconnexion == null) {
			miDeconnexion = new JMenuItem("Deconnexion");
			miDeconnexion.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					EcranLoginController.getInstance().deconnexion();
				}
			});
		}
		return miDeconnexion;
	}
	
	public JMenuItem getMiFermer(){
		if (miFermer == null) {
			miFermer = new JMenuItem("Fermer");
			miFermer.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					EcranLoginController.getInstance().fermeture();
				}
			});
		}
		return miFermer;
	}
	
	public JMenuItem getMiRDV(){
		if (miRDV == null) {
			miRDV = new JMenuItem("Prise des rendez-vous");
			miRDV.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					setContentPane(PanPriseRdv.getInstance());
					repaint();
					revalidate();
				}
			});
		}
		return miRDV;
	}
	
	public JMenuItem getMiClient(){
		if (miClient == null) {
			miClient = new JMenuItem("Gestion des clients");
			miClient.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						setContentPane(EcranClients.getInstance());
						repaint();
						revalidate();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
				}
			});
		}
		return miClient;
	}
	
	private String selectRolePersonnel(Personnels personnel) {
		boolean verifAdm = false;
		String role = "";
		for (String tmp : personnel.getListRole()) {
			if (tmp.equals("adm")) {
				verifAdm= true;
			}
			else if (tmp.equals("vet")) {
				role = tmp;
			}
			else if (tmp.equals("sec")) {
				role = tmp;
			}
			else if (tmp.equals("ass")) {
				role = tmp;
			}
		}
		if (verifAdm) {
			return "adm";
		} 
		else {
			return role;
		}
	}
		
}
