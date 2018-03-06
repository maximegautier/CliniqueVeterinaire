package fr.eni.cliniqueveterinaire.ihm.personnels;

import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.ihm.menu.EcranMenu;
import fr.eni.cliniqueveterinaire.log.LogFactory;

public class PanPersonnelsController extends JPanel{

	private final static Logger LOGGER = Logger.getLogger(LogFactory.class.getName());
	
	private static PanPersonnelsController instance;
	private EcranMenu fenMenu;
	private PanPersonnels panelPersonnels;

	private PanPersonnelsController(){
		
	}
	
	public static PanPersonnelsController getInstance(){
		if ( PanPersonnelsController.instance == null){
			PanPersonnelsController.instance = new PanPersonnelsController();
		}
		return PanPersonnelsController.instance;
	}
	
	public void ajouter() {
		DialogAdd jdAjouter = new DialogAdd();
	}
	
	public void supprimer(Personnels personnel) throws BLLException {
		PersonnelsManager.supprimer(personnel);
		rafraichirTable();
	}
	
	public void reinitialiser(Personnels personnel) throws BLLException{
		DialogReinit dialogReinit = new DialogReinit(fenMenu,personnel);
		rafraichirTable();
	}
	
	public void validerAjout(String nom, String prenom, String login, String role, String mdp)
	{
		Personnels personnel = new Personnels(nom,prenom,login,mdp,role,false);
		try {
			// Ajouter le nouveau personnel
			PersonnelsManager.ajouter(personnel);
			
			rafraichirTable();	
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();	
		}
	}
	
	public void validerReinit(Personnels personnel, String ancienMDP, String nouveauMDP) throws BLLException
	{
		PersonnelsManager.modificationMotPasse(personnel, ancienMDP, nouveauMDP);
		rafraichirTable();
	}
	
	public void rafraichirTable() throws BLLException{
		panelPersonnels.getInstance().getTablePersonnel().setData(PersonnelsManager.selectTousPersonnels());
	}
	
	public List<String> selectTousRoles()
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
	
	public Personnels selectPersonnel(int id) throws BLLException
	{
		return PersonnelsManager.selectTousPersonnels().get(id);
	}
	
	public List<Personnels> selectPersonnels() throws BLLException {
		return PersonnelsManager.selectTousPersonnels();
	}
	
}
