package fr.eni.cliniqueveterinaire.ihm.agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import fr.eni.cliniqueveterinaire.bll.AgendasManager;
import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.bll.PersonnelsManager;
import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Animaux;
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
    
	public static List<Agendas> remplirTableau(Date jour, int codeVet) throws BLLException{
		return AgendasManager.selectParDateVeterinaire(jour, codeVet);
	}
	
	public static List<Personnels> selectVeterinaires() throws BLLException
	{
		List<Personnels> lPersonnel = PersonnelsManager.selectTousVeterinaires();
		
		return lPersonnel;
	}
	
	public static List<Agendas> selectAgendasParDateVeterinaire(Date jour, int codeVeterinaire) throws BLLException
	{
		return AgendasManager.selectParDateVeterinaire(jour, codeVeterinaire);
	}
	
	public static List<Clients> selectClients() throws BLLException
	{
		return ClientsManager.getInstance().selectAll();
	}
	
	public static List<Animaux> selectAnimauxClient(int codeClient) throws BLLException 
	{
		return AnimauxManager.selectAnimaux(codeClient);
	}	
	
	public static void ouvrirDossier(Animaux animal) throws BLLException
	{
			Clients client = ClientsManager.getInstance().selectById(animal.getCodeClient());
			EcranDossier ecranDossier = new EcranDossier(animal,client);
		
	}
	
	public static void ajouterRdv(Agendas aAjouter) throws BLLException
	{
		AgendasManager.ajouter(aAjouter);
	}
	
	public static void supprimerRdv(Agendas aSupprimer) throws BLLException
	{
		AgendasManager.supprimer(aSupprimer);
	}
	
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
