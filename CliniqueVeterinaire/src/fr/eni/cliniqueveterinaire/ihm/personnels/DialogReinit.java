package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bo.Personnels;

/* Créé par Erwin DUPUIS */
public class DialogReinit extends JDialog
{
    //region DECLARATION

	private JPanel mainPanel;
	private JLabel lblPersonnel;
	private JTextField tfdPersonnel;
	private JLabel lblAncienMDP;
	private JPasswordField tfdAncienMDP;
	private JLabel lblNouveauMDP;
	private JPasswordField tfdNouveauMDP;
	private JButton btnAnnuler;
	private JButton btnValider;
	private Personnels currentPersonnel;

    //endregion DECLARATION

    //region CTOR

	public DialogReinit(JFrame parent, Personnels personnel)
	{
		super(parent,"Réinitialiser le mot de passe",true);
		this.currentPersonnel = personnel;
		InitialiserJdReinit();
	}

    //endregion CTOR
    
    //region METHODS
    
	public void InitialiserJdReinit()
	{
		setSize(350, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		
		mainPanel = getMainPanel();
		InitialiserMainPanel();
		
		setVisible(true);
	}
	
	public void InitialiserMainPanel()
	{
		mainPanel.setSize(getWidth(), getHeight());
        mainPanel.setOpaque(true);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints MainGBC = new GridBagConstraints();
        MainGBC.insets = new Insets(5, 5, 5, 5);
        setContentPane(mainPanel);
        
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
			tfdPersonnel = new JTextField(currentPersonnel.getDisplayName());
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
	

	public JPasswordField getTfdAncienMDP() 
	{
		if(tfdAncienMDP == null)
		{
			tfdAncienMDP = new JPasswordField(15);
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
	

	public JPasswordField getTfdNouveauMDP() 
	{
		if(tfdNouveauMDP == null)
		{
			tfdNouveauMDP = new JPasswordField(15);
		}
		return tfdNouveauMDP;
	}
	

	public JButton getBtnAnnuler() 
	{
		if(btnAnnuler == null)
		{
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			});
		}
		return btnAnnuler;
	}
	

	public JButton getBtnValider() 
	{
		if(btnValider == null)
		{
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try {
						PanPersonnelsController.getInstance().validerReinit(currentPersonnel, getTfdAncienMDP().getText(), getTfdNouveauMDP().getText());
						JOptionPane.showMessageDialog(null, "Mot de passe changé", "Succes", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return btnValider;
	}
	
    //endregion GET/SET
}
