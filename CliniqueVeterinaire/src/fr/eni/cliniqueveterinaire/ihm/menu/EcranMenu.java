package fr.eni.cliniqueveterinaire.ihm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.agenda.PanAgenda;
import fr.eni.cliniqueveterinaire.ihm.personnels.PanGDP;

public class EcranMenu extends JFrame{
	
	private static EcranMenu instance;
	PanGDP GDP = PanGDP.getInstance();
	PanAgenda panAgenda = PanAgenda.getInstance();
	
	private int width = 700;
	private int height = 500;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mFichier, mRDV, mAgenda, mGDP;
	private JMenuItem miDeconnexion, miFermer;
	private JMenuItem miRDV, miClient;
	
	public static EcranMenu getInstance() throws DALException{
		if (EcranMenu.instance == null){
			EcranMenu.instance = new EcranMenu();
		}
		return instance;
	}
	
	public EcranMenu() throws DALException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setSize(width, height);
		setResizable(false);
		setTitle("Clinique Veterinaire");
		setIconImage(new ImageIcon("ressources/ico_veto.png").getImage());
		
		menuBar.add(getMFichier());
		menuBar.add(getMRDV());
		menuBar.add(getMAgenda());
		menuBar.add(getMGDP());
		
		setJMenuBar(menuBar);
		
		setContentPane(GDP.getPanGDP());

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
	
	public JMenu getMAgenda(){
		if (mAgenda == null) {
			mAgenda = new JMenu("Agenda");
		}
		return mAgenda;
	}
	
	public JMenu getMGDP(){
		if (mGDP == null) {
			mGDP = new JMenu("Gestion du personnel");
		}
		return mGDP;
	}
	
	public JMenuItem getMiDeconnexion(){
		if (miDeconnexion == null) {
			miDeconnexion = new JMenuItem("Deconnexion");
			miDeconnexion.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						EcranMenuController.getInstance().Deconnexion();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					try {
						EcranMenuController.getInstance().Fermer();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return miFermer;
	}
	
	public JMenuItem getMiRDV(){
		if (miRDV == null) {
			miRDV = new JMenuItem("Prise des rendez-vous");
		}
		return miRDV;
	}
	
	public JMenuItem getMiClient(){
		if (miClient == null) {
			miClient = new JMenuItem("Gestion des clients");
		}
		return miClient;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
	
}
