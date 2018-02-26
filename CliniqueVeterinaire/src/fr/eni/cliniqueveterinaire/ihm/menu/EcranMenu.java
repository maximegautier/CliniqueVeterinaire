package fr.eni.cliniqueveterinaire.ihm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.eni.cliniqueveterinaire.ihm.menu.gdp.PanGDP;

public class EcranMenu extends JFrame{
	
	private static EcranMenu instance;
	PanGDP GDP = PanGDP.getInstance();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mFichier, mRDV, mAgenda, mGDP;
	private JMenuItem miDeconnexion, miFermer;
	private JMenuItem miRDV, miClient;
	
	public static EcranMenu getInstance(){
		if (EcranMenu.instance == null){
			EcranMenu.instance = new EcranMenu();
		}
		return instance;
	}
	
	public EcranMenu() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(700, 500);
		setResizable(false);
		setTitle("Clinique Veterinaire");
		
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
					EcranMenuController.getInstance().Deconnexion();
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
					EcranMenuController.getInstance().Fermer();
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
	
}
