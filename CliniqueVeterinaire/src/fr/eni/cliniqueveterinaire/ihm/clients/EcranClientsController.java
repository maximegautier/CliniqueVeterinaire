package fr.eni.cliniqueveterinaire.ihm.clients;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.animal.EcranAnimal;
import fr.eni.cliniqueveterinaire.ihm.animal.EcranAnimalController;

public class EcranClientsController {
	private static EcranClientsController instance;
	private EcranClients fenClient;
	private EcranAjoutClients fenAjoutclient;
	private EcranRechercheClients fenRechercheClient;
	
	private EcranClientsController(){
		
	}
	
	public void startApp(EcranClients fenCli) throws BLLException
	{
		fenClient = fenCli;
		remplirChamps(fenCli.getCodeClient());
		actualiseTab(fenClient.getCodeClient());
		fenClient.setVisible(true);
	}
	
	public void remplirChamps(int codeClient) throws BLLException{
		Clients leClient = ClientsManager.getInstance().selectById(codeClient);
		
		fenClient.getTxtCodeClient().setText(""+leClient.getCodeClient());
		fenClient.getTxtNom().setText(leClient.getNomClient());
		fenClient.getTxtPrenom().setText(leClient.getPrenomClient());
		fenClient.getTxtAdresse().setText(leClient.getAdresse1());
		fenClient.getTxtComplementAdresse().setText(leClient.getAdresse2());
		fenClient.getTxtCodePostal().setText(leClient.getCodePostal());
		fenClient.getTxtVille().setText(leClient.getVille());		
	}

	public void actualiseChamps(int codeClient) throws BLLException {
		Clients leClient = null;
		
		leClient = ClientsManager.getInstance().selectById(codeClient);
		
		fenClient.getTxtCodeClient().setText(""+codeClient);
		fenClient.getTxtNom().setText(leClient.getNomClient());
		fenClient.getTxtPrenom().setText(leClient.getPrenomClient());
		fenClient.getTxtAdresse().setText(leClient.getAdresse1());
		fenClient.getTxtComplementAdresse().setText(leClient.getAdresse2());
		fenClient.getTxtCodePostal().setText(leClient.getCodePostal());
		fenClient.getTxtVille().setText(leClient.getVille());
	}
	
	public void clickClientsRecherche(){
		EcranRechercheClients.getInstance().setVisible(true);
	}
	
	public void clickClientsAjouter(){
		EcranAjoutClients.getInstance().setVisible(true);
	}
	
	public void clickClientsSupprime(int codeClient) throws BLLException, DALException{
		ClientsManager.getInstance().deleteClient(codeClient);
	}
	
	public int clickClientsSuivant(int codeClient) throws BLLException, DALException{
		int id = ClientsManager.getInstance().clientSuivant(codeClient).getCodeClient();
		actualiseChamps(id);
		actualiseTab(id);
		return id;
	}
	
	public void actualiseTab(int codeClient) throws BLLException{
		int i =0;
		List<Animaux> listeAnimaux = AnimauxManager.selectAnimaux(codeClient);
		((ModeleTableAnimauxClients) (fenClient.getTabAnimaux().getModel())).setDataChanged(listeAnimaux);
		for(Animaux anim : listeAnimaux){
			System.out.println(anim.toString());
		}
	}
	
	public int clickClientsPrecedent(int codeClient) throws BLLException, DALException{
		int id = ClientsManager.getInstance().clientPrecedent(codeClient).getCodeClient();
		actualiseChamps(id);
		actualiseTab(id);
		return id;
	}
	
	public void clickAjouterAnimaux(){
		new EcranAnimal();
	}
	
	public int validerNouveauClient(Clients newClient) throws BLLException, DALException{
		return ClientsManager.getInstance().ajouterClient(newClient);
	}
	
	public void clickSupprimerAnimaux(int codeAnimal) throws BLLException{
		AnimauxManager.supprimer(codeAnimal);
	}
	
	public void clickClientsModifier(Clients leClient) throws DALException, BLLException{
		ClientsManager.getInstance().editClient(leClient);
	}
	
	public static EcranClientsController getInstance()
	{
		if(EcranClientsController.instance == null)
		{
			EcranClientsController.instance = new EcranClientsController();
		}
		return EcranClientsController.instance;
	}
	
}
