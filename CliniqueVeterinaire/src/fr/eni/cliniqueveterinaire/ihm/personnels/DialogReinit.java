package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

/* Créé par Erwin DUPUIS */
public class DialogReinit
{
    //region DECLARATION

	private JDialog jdReinit;
	private JPanel mainPanel;
	
	private JLabel lblPersonnel;
	private JTextField tfdPersonnel;
	private JLabel lblAncienMDP;
	private JTextField tfdAncienMDP;
	private JLabel lblNouveauMDP;
	private JTextField tfdNouveauMDP;
	private JButton btnAnnuler;
	private JButton btnValider;	

    //endregion DECLARATION

    //region CTOR

	public DialogReinit() throws DALException
	{
		jdReinit = getJdReinit();
		InitialiserJdReinit();		
	}

    //endregion CTOR
    
    //region METHODS
    
	public void InitialiserJdReinit()
	{
		jdReinit.setSize(350, 180);
		jdReinit.setResizable(false);
		jdReinit.setTitle("Réinitialiser le mot de passe");
		jdReinit.setModal(true);
		jdReinit.setLocationRelativeTo(null);
		
		mainPanel = getMainPanel();
		InitialiserMainPanel();
		
		jdReinit.setVisible(true);
	}
	
	public void InitialiserMainPanel()
	{
		mainPanel.setSize(jdReinit.getWidth(), jdReinit.getHeight());
        mainPanel.setOpaque(true);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints MainGBC = new GridBagConstraints();
        MainGBC.insets = new Insets(5, 5, 5, 5);
        jdReinit.setContentPane(mainPanel);
        
        //LIGNE 0
        
        	//COLONNE0
	        MainGBC.gridx =0;
	    	MainGBC.gridy =0;
	    	MainGBC.anchor = GridBagConstraints.WEST;
	    	mainPanel.add(getLblPersonnel(), MainGBC);
        	//COLONNE1
	    	MainGBC.gridx =1;
	    	MainGBC.gridy =0;
	    	MainGBC.anchor = GridBagConstraints.WEST;	    	
	    	mainPanel.add(getTfdPersonnel(), MainGBC);
        
        //LIGNE 1
        
        	//COLONNE0
	    	MainGBC.gridx =0;
	    	MainGBC.gridy =1;
	    	MainGBC.anchor = GridBagConstraints.WEST;
	    	mainPanel.add(getLblAncienMDP(), MainGBC);
        	//COLONNE1
	    	MainGBC.gridx =1;
	    	MainGBC.gridy =1;
	    	MainGBC.anchor = GridBagConstraints.WEST;
	    	mainPanel.add(getTfdAncienMDP(), MainGBC);
        
        //LIGNE 2
        
    		//COLONNE0
	    	MainGBC.gridx =0;
	    	MainGBC.gridy =2;
	    	MainGBC.anchor = GridBagConstraints.WEST;
	    	mainPanel.add(getLblNouveauMDP(), MainGBC);
    		//COLONNE1
	    	MainGBC.gridx =1;
	    	MainGBC.gridy =2;
	    	MainGBC.anchor = GridBagConstraints.WEST;
	    	mainPanel.add(getTfdNouveauMDP(), MainGBC);
        
        //LIGNE 3
        
    		//COLONNE0
	    	MainGBC.gridx =0;
	    	MainGBC.gridy =3;
	    	MainGBC.anchor = GridBagConstraints.EAST;
	    	mainPanel.add(getBtnAnnuler(), MainGBC);
    		//COLONNE1
	    	MainGBC.gridx =1;
	    	MainGBC.gridy =3;
	    	MainGBC.anchor = GridBagConstraints.EAST;
	    	mainPanel.add(getBtnValider(), MainGBC);
	}

    
    //endregion METHODS

    //region GET/SET

	public JDialog getJdReinit() throws DALException
	{
		if(jdReinit == null)
		{
			jdReinit = new JDialog(EcranMenu.getInstance());
		}
		
		return jdReinit;
	}
	
	public JPanel getMainPanel()
	{
		if(mainPanel == null)
		{
			mainPanel = new JPanel();
		}
		
		return mainPanel;
	}

	
	public JLabel getLblPersonnel() 
	{
		if(lblPersonnel == null)
		{
			lblPersonnel = new JLabel("Personnel : ");
		}
		return lblPersonnel;
	}
	

	public JTextField getTfdPersonnel() 
	{
		if(tfdPersonnel == null)
		{
			tfdPersonnel = new JTextField(15);
			tfdPersonnel.setEditable(false);
		}
		return tfdPersonnel;
	}
	

	public JLabel getLblAncienMDP() 
	{
		if(lblAncienMDP == null)
		{
			lblAncienMDP = new JLabel("Ancien MDP : ");
		}
		return lblAncienMDP;
	}
	

	public JTextField getTfdAncienMDP() 
	{
		if(tfdAncienMDP == null)
		{
			tfdAncienMDP = new JTextField(15);
		}
		return tfdAncienMDP;
	}
	

	public JLabel getLblNouveauMDP() 
	{
		if(lblNouveauMDP == null)
		{
			lblNouveauMDP = new JLabel("Nouveau MDP : ");
		}
		return lblNouveauMDP;
	}
	

	public JTextField getTfdNouveauMDP() 
	{
		if(tfdNouveauMDP == null)
		{
			tfdNouveauMDP = new JTextField(15);
		}
		return tfdNouveauMDP;
	}
	

	public JButton getBtnAnnuler() 
	{
		if(btnAnnuler == null)
		{
			btnAnnuler = new JButton("Annuler");
		}
		return btnAnnuler;
	}
	

	public JButton getBtnValider() 
	{
		if(btnValider == null)
		{
			btnValider = new JButton("Valider");
		}
		return btnValider;
	}
	
    //endregion GET/SET
}
