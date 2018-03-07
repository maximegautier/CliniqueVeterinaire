package fr.eni.cliniqueveterinaire.ihm.clients;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;

import fr.eni.cliniqueveterinaire.bll.AgendasManager;
import fr.eni.cliniqueveterinaire.bll.AnimauxManager;
import fr.eni.cliniqueveterinaire.bll.BLLException;
import fr.eni.cliniqueveterinaire.bll.ClientsManager;
import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.ihm.Update;
import fr.eni.cliniqueveterinaire.ihm.animal.EcranAnimal;
import fr.eni.cliniqueveterinaire.ihm.animal.EcranAnimalController;

public class EcranClientsController implements Update{
	private static EcranClientsController instance;
	private EcranClients fenClient;
	private EcranAjoutClients fenAjoutclient;
	private EcranRechercheClients fenRechercheClient;
	public Update update;
	
	private EcranClientsController(){
		update = this;
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
		fenClient.getTxtEmail().setText(leClient.getEmail());
		fenClient.getTxtNumTel().setText(leClient.getNumTel());
		fenClient.getTxtAssurance().setText(leClient.getAssurance());
		fenClient.getTxtRemarque().setText(leClient.getRemarque());	
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
		fenClient.getTxtEmail().setText(leClient.getEmail());
		fenClient.getTxtNumTel().setText(leClient.getNumTel());
		fenClient.getTxtAssurance().setText(leClient.getAssurance());
		fenClient.getTxtRemarque().setText(leClient.getRemarque());
	}
	
	public void clickClientsRecherche(){
		EcranRechercheClients.getInstance().setVisible(true);
	}
	
	public void clickClientsAjouter(){
		EcranAjoutClients.getInstance().setVisible(true);
	}
	
	public void clickClientsSupprime(int codeClient) throws BLLException, DALException{
		Clients leClient = ClientsManager.getInstance().selectById(codeClient);
		leClient.setArchive(true);
		supprimeAnimaux(codeClient);
		ClientsManager.getInstance().editClient(leClient);
	}
	
	public void supprimeAnimaux(int codeClient) throws BLLException{
		List<Animaux> liste = AnimauxManager.selectAnimaux(codeClient);
		for(Animaux anim : liste){
			AgendasManager.supprimerParNom(anim.getCodeAnimal());
			anim.setarchive(true);
			AnimauxManager.modifier(anim);
		}
	}
	
	public void clickSupprimerAnimaux(int codeClient,int codeAnimal) throws BLLException{
		Animaux lanimal = AnimauxManager.selectAnimal(codeAnimal);
		AgendasManager.supprimerParNom(lanimal.getCodeAnimal());
		lanimal.setarchive(true);
		AnimauxManager.modifier(lanimal);
		actualiseTab(codeClient);
	}
	
	public int clickClientsSuivant(int codeClient) throws BLLException, DALException{
		int id = ClientsManager.getInstance().clientSuivant(codeClient).getCodeClient();
		actualiseChamps(id);
		actualiseTab(id);
		return id;
	}
	
	public void clickRechercheClients(String recherche) throws BLLException{
		if(!recherche.equals("")){
			boolean isEmpty = true;
			List<Clients> listeClients = ClientsManager.getInstance().selectAll();
			List<String> liste = new ArrayList();
			for(Clients cli : listeClients){
				if(cli.getNomClient().toLowerCase().contains(recherche.toLowerCase()) || 
					cli.getPrenomClient().toLowerCase().contains(recherche.toLowerCase())){
					String client = ""+cli.getCodeClient()+" - "+cli.getNomClient()+" "+cli.getPrenomClient();
					client += cli.getCodePostal() == null ? "" : cli.getCodePostal();
					client += cli.getVille() == null ? "" : cli.getVille();
					liste.add(client);
					ajouteListe(liste);
					isEmpty = false;
				}
			}
			if(!isEmpty){
				EcranRechercheClients.getInstance().getListeClients().addMouseListener(new MouseAdapter() {
				    public void mouseClicked(MouseEvent evt) {
				        JList list = (JList)evt.getSource();
				        if (evt.getClickCount() == 2) {
				            int index = list.locationToIndex(evt.getPoint());
				            EcranRechercheClients.getInstance().setVisible(false);
				            try {
								clickDoubleRecherche(index);
							} catch (BLLException e) {
								e.printStackTrace();
							}
				        } 
				    }
				});
			}
		}
	}
	
	public void clickDoubleRecherche(int index) throws BLLException{
		String monClient = (String)EcranRechercheClients.getInstance().getListeClients().getSelectedValue();
		int codeClient = Integer.parseInt(monClient.substring(0,monClient.indexOf(" -")));
		actualiseChamps(codeClient);
		actualiseTab(codeClient);
		ajouteListe(new ArrayList<String>());
		EcranRechercheClients.getInstance().getTxtRecherche().setText("");
	}
	
	public void ajouteListe(List<String> liste){
		Vector<String> vecteur = new Vector();
		for(String cli : liste){
			vecteur.add(cli);
		}
		EcranRechercheClients.getInstance().getListeClients().setListData(vecteur);
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
	
	public void clickAjouterAnimaux(int codeClient){
		new EcranAnimal(codeClient,update);
	}
	
	public void clickEditerAnimaux(int codeClient,int codeAnimal){
		new EcranAnimal(1,codeAnimal,update);
	}
	
	public int validerNouveauClient(Clients newClient) throws BLLException, DALException{
		return ClientsManager.getInstance().ajouterClient(newClient);
	}
		
	public void clickClientsModifier(Clients leClient) throws DALException, BLLException{
		ClientsManager.getInstance().editClient(leClient);
	}
	
	public void clickAnnuler(int codeClient) throws BLLException{
		actualiseChamps(codeClient);		
	}
	
	public static EcranClientsController getInstance()
	{
		if(EcranClientsController.instance == null)
		{
			EcranClientsController.instance = new EcranClientsController();
		}
		return EcranClientsController.instance;
	}

	@Override
	public void updatePanPriseRdv() {
	}
	
}
