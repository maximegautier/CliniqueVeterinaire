package fr.eni.cliniqueveterinaire.ihm.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.cliniqueveterinaire.dal.DALException;

public class EcranLogin extends JFrame{
	
	private static EcranLogin instance;
	
	public JPanel panelConnexion;
	public GridBagConstraints gbcConnexion;
	public JTextField tfLogin; 
	public JPasswordField tfPassword;
	public JButton bValider;
	public JLabel lError;
	
	public static EcranLogin getInstance(){
		if (EcranLogin.instance == null){
			EcranLogin.instance = new EcranLogin();
		}
		return instance;
	}

	private EcranLogin() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 180);
		setResizable(false);
		setTitle("Connexion");
		setContentPane(Connexion());
		setVisible(true);
		setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("ressources/ico_veto.png").getImage());
	}
	
	private JPanel Connexion(){
		if (panelConnexion == null){
			panelConnexion = new JPanel();
			panelConnexion.setLayout(new GridBagLayout());
			gbcConnexion = new GridBagConstraints();	
			gbcConnexion.insets = new Insets(5, 5, 15, 5);
			gbcConnexion.anchor = GridBagConstraints.WEST;
			
			// Login
			gbcConnexion.gridx=0;
			gbcConnexion.gridy=0;
			JLabel lLogin = new JLabel("Nom");
			panelConnexion.add(lLogin,gbcConnexion);
			gbcConnexion.gridx = 1;
			panelConnexion.add(getTxtLogin(),gbcConnexion);
			
			// Password
			gbcConnexion.gridx=0;
			gbcConnexion.gridy=1;
			JLabel lPassword = new JLabel("Mot de passe");
			panelConnexion.add(lPassword,gbcConnexion);
			gbcConnexion.gridx = 1;
			panelConnexion.add(getTxtPassword(),gbcConnexion);
			
			// Valider
			gbcConnexion.anchor = GridBagConstraints.CENTER;
			gbcConnexion.gridx=0;
			gbcConnexion.gridy=2;
			gbcConnexion.gridwidth = 2;
			panelConnexion.add(getbValider(),gbcConnexion);
			
			// Erreur mot de passe
			gbcConnexion.insets = new Insets(0, 0, 0, 0);
			gbcConnexion.gridx=0;
			gbcConnexion.gridy=3;
			panelConnexion.add(getlError(),gbcConnexion);
		}
		return panelConnexion;
	}
	
	public JTextField getTxtLogin(){
		if (tfLogin == null) {
			tfLogin = new JTextField("admin",15);
		}
		return tfLogin;
	}
	
	public JTextField getTxtPassword(){
		if (tfPassword == null) {
			tfPassword = new JPasswordField("admin",15);
		}
		return tfPassword;
	}
	
	public JLabel getlError(){
		if (lError == null) {
			lError = new JLabel("Mauvais mot de passe");
			lError.setVisible(false);
		}
		return lError;
	}
	
	public JButton getbValider(){
		if (bValider == null) {
			bValider = new JButton("Valider");
			bValider.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						EcranLoginController.getInstance().connexion(getTxtLogin().getText(),getTxtPassword().getText());
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return bValider;
	}
	
}
