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
import fr.eni.cliniqueveterinaire.ihm.animal.EcranAnimal;

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
	
	public static List<Personnels> remplirComboVeterinaire() throws BLLException
	{
		List<Personnels> lPersonnel = PersonnelsManager.selectTousVeterinaires();
		
		return lPersonnel;
	}
	
	public static List<Clients> selectClients() throws BLLException
	{
		return ClientsManager.getInstance().selectAll();
	}
	
	public static void ouvrirAjoutAnimal(int codeClient)
	{
		EcranAnimal ecranAnimal = new EcranAnimal(codeClient);
	}
	
	public static void ouvrirDossier()
	{
		EcranDossier ecranDossier = new EcranDossier();
	}
	
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
