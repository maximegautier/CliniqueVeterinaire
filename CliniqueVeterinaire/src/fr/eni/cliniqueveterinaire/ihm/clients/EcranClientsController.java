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
		remplirChamps();
		remplirTable(fenClient.getCodeClient());
		fenClient.setVisible(true);
	}
	
	public void remplirChamps() throws BLLException{
		Clients leClient = null;
		
		leClient = ClientsManager.getInstance().selectFirst();
		
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
	
	public void remplirTable(int codeClient) throws NumberFormatException, BLLException{
		List<Animaux> listeAnimaux = new ArrayList();
		String[] titresCol = new String[7];
		
		listeAnimaux = AnimauxManager.selectAnimaux(3);//fenClient.getCodeClient());
		
		titresCol[0] = "Numéro";
		titresCol[1] = "Nom";
		titresCol[2] = "Sexe";
		titresCol[3] = "Couleur";
		titresCol[4] = "Race";
		titresCol[5] = "Espece";
		titresCol[6] = "Tatouage";
		
		fenClient.setModele(new ModeleTableAnimauxClients(listeAnimaux, titresCol));
	}
	
	public void clickClientsRecherche(){
		EcranRechercheClients.getInstance();
	}
	
	public void clickClientsAjouter(){
		EcranAjoutClients.getInstance();
	}
	
	public void clickClientsSupprime(int codeClient) throws BLLException, DALException{
		ClientsManager.getInstance().deleteClient(codeClient);
	}
	
	public int clickClientsSuivant(int codeClient) throws BLLException, DALException{
		int id = ClientsManager.getInstance().clientSuivant(codeClient).getCodeClient();
		actualiseChamps(id);
		return id;
	}
	
	public int clickClientsPrecedent(int codeClient) throws BLLException, DALException{
		int id = ClientsManager.getInstance().clientPrecedent(codeClient).getCodeClient();
		actualiseChamps(id);
		return id;
	}
	
	public void clickAjouterAnimaux(){
		new EcranAnimal();
	}
	
	public void clickSupprimerAnimaux(int codeAnimal) throws BLLException{
		AnimauxManager.supprimer(codeAnimal);
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
