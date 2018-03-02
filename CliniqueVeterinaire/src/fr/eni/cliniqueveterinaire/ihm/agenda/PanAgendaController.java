package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bll.AgendasManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.ClientsDAO;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;

public class PanAgendaController 
{
    //region DECLARATION
	
	private static ClientsDAO clientsDAO;

    //endregion DECLARATION

    //region CTOR


    //endregion CTOR
    
    //region METHODS
    
	public static List<Agendas> remplirTableau(Date jour) throws BLLException{
		return AgendasManager.selectParDate(jour);
	}
	
	public static List<String> remplirComboVeterinaire() throws BLLException{
		List<Personnels> lPersonnel = PersonnelsManager.selectTousVeterinaires();
		
		List<String> lNomPersonnel = new ArrayList<String>();
		
		for (Personnels tmp : lPersonnel)
		{
			lNomPersonnel.add(tmp.getDisplayName());
		}
		
		return lNomPersonnel;
	}
	
	public static List<Clients> selectClients() throws BLLException
	{
		return ClientsManager.getInstance().selectAll();
	}
	
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
