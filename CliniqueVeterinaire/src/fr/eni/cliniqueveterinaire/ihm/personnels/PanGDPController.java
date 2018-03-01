package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;

public class PanGDPController extends JPanel{

	private static PanGDPController instance;
	private EcranLogin fenLogin;
	private EcranMenu fenMenu;
	private PanGDP panelGDP;

	private PanGDPController(){
		
	}
	
	public static PanGDPController getInstance(){
		if ( PanGDPController.instance == null){
			PanGDPController.instance = new PanGDPController();
		}
		return PanGDPController.instance;
	}
	
	public void Ajouter() throws DALException{
		DialogAdd jdAjouter = new DialogAdd();
	}
	
	public void supprimer() throws BLLException{
		int NumLigne = 0;
	    try {
			NumLigne = panelGDP.getInstance().getTablePersonnel().getSelectedRow();
			if (NumLigne == -1)
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			} 
			else
			{
				Personnels personnel = PersonnelsManager.selectTousPersonnels().get(NumLigne);
				JOptionPane jop = new JOptionPane();			
				int option = jop.showConfirmDialog(null, "Etes-vous sur de vouloir supprimer " + personnel.getNom() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							
				if(option == JOptionPane.OK_OPTION){				
					PersonnelsManager.Supprimer(personnel);
					rafraichirTable();
				}
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage());
		}
	}
	
	public void reinitialiser() throws DALException{
		int NumLigne = panelGDP.getInstance().getTablePersonnel().getSelectedRow();
		if (NumLigne == -1)
		{
			JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} 
		else
		{
			Personnels personnel = PersonnelsManager.selectTousPersonnels().get(NumLigne);
			DialogReinit dialogReinit = new DialogReinit(fenMenu,personnel);
		}
	}
	
	public void validerAjout(String nom, String prenom, String role, String mdp)
	{
		Personnels personnel = new Personnels(nom,prenom,"Login",mdp,role,false);
		try {
			// Ajouter le nouveau personnel
			PersonnelsManager.Ajouter(personnel);
			
			rafraichirTable();
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();	
		}
	}
	
	public void validerReinit(Personnels personnel, String ancienMDP, String nouveauMDP) throws BLLException
	{
		PersonnelsManager.modificationMotPasse(personnel, ancienMDP, nouveauMDP);
		rafraichirTable();
	}

	
	public Vector<Vector> completerTableau() throws BLLException
	{
		List<Personnels> lPersonnels = null;
		try {
			lPersonnels = PersonnelsManager.selectTousPersonnels();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException(e.getMessage());
		}
		
		Vector<Vector> vecRow = new Vector<Vector>();
		
		for (Personnels tmp : lPersonnels)
		{
			Vector<String> tmpVct = new Vector<String>();
			tmpVct.addElement(tmp.getNom());
			tmpVct.addElement(tmp.getRole());
			tmpVct.addElement(tmp.getMotPasse());
			
			vecRow.addElement(tmpVct);
		}	
		return vecRow;
	}
	
	public Vector<String> getEntete()
	{
		Vector<String> entetes = new Vector<String>();
		entetes.addElement("Nom");
		entetes.addElement("Role");
		entetes.addElement("Mot de Passe");
		return entetes;
	}
	
	public void rafraichirTable() throws BLLException{
		while (panelGDP.getInstance().getDefTableModel().getRowCount() > 0)
		{
			panelGDP.getInstance().getDefTableModel().removeRow(0);
		}
		
		// Rafraichir la table
		panelGDP.getInstance().getDefTableModel().setDataVector(completerTableau(), getEntete());	
	}
	
	public List<String> remplirComboAjouter()
	{
		List<String> lRoles = null;;
		try {
			lRoles = PersonnelsManager.selectTousRoles();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lRoles;
	}
	
}
