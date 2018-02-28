package fr.eni.cliniqueveterinaire.ihm.menu.gdp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.login.EcranLogin;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;
import fr.eni.cliniqueveterinaire.ihm.personnels.DialogAdd;
import fr.eni.cliniqueveterinaire.ihm.personnels.DialogReinit;

public class PanGDPController {

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
	
	public void Supprimer(){
		System.out.println("Supprimer");
	}
	
	public void Reinitialiser() throws DALException{
		System.out.println("Reinitialiser");
		DialogReinit dialogReinit = new DialogReinit();
		
	}
	
	public void Valider(String nom, String prenom, String role, String mdp)
	{
		Personnels personnel = new Personnels(nom,mdp,role,false);
		try {
			PersonnelsManager.getInstance().Ajouter(personnel);
			panelGDP.getInstance().getTablePersonnel().removeRowSelectionInterval(0,panelGDP.getInstance().getTablePersonnel().getRowCount()-1);
			//panelGDP.getInstance().getTablePersonnel().
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Vector<Vector> completerTableau() throws DALException
	{
		List<Personnels> lPersonnels = PersonnelsManager.getInstance().selectTousPersonnels();
		
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
	
	public List<String> remplirComboAjouter() throws DALException
	{
		List<String> lRoles = PersonnelsManager.getInstance().selectTousRoles();
		return lRoles;
	}
	
}
