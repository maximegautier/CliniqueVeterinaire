package fr.eni.cliniqueveterinaire.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.dal.ClientsDAO;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.DAOFactory;
import fr.eni.cliniqueveterinaire.ihm.clients.EcranClients;

public class ClientsManager {
	
	private ClientsDAO clientsDAO; 
	private static ClientsManager instance;
	
	private ClientsManager() throws BLLException
	{
		clientsDAO = DAOFactory.getClientsDAO();
	}
	
	public Clients selectById(int id) throws BLLException{
		Clients aRetourner = null;
		
		if(id <= 0 ){
			throw new BLLException("(ClientsManager)SelectById : Le codeClient ne peut pas être null");
		}else
		{
			try 
			{
				aRetourner = clientsDAO.selectById(id);
			} 
			catch (DALException e) 
			{
				throw new BLLException(e.getMessage());
			}
		}		
		return aRetourner;
	}
	
	public Clients selectFirst() throws BLLException{
		Clients aRetourner = null;
		
		try 
		{
			aRetourner = clientsDAO.selectFirst();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
		
		return aRetourner;
	}
	
	public void deleteClient(int codeClient) throws BLLException, DALException {
		if(codeClient <= 0 ){
			throw new BLLException("(ClientsManager)deleteClient : Le codeClient ne peut pas être null");
		}else
		{
			if(clientsDAO.selectById(codeClient) == null){
				throw new BLLException("(ClientsManager)deleteClient : Le client n'éxiste pas (id incorrect)");
			}else{
				clientsDAO.deleteClient(codeClient);
			}			
		}		
	}
	
	
	public Clients clientSuivant(int codeClient) throws BLLException, DALException{
		Clients leClient = null;
		Clients clientPrecedent;
		List<Clients> listeClients = new ArrayList<Clients>();
		if(codeClient <= 0 ){
			throw new BLLException("(ClientsManager)clientSuivant : Le codeClient ne peut pas être null");
		}else
		{
			if(clientsDAO.selectById(codeClient) == null){
				throw new BLLException("(ClientsManager)clientSuivant : Le client n'éxiste pas (id incorrect)");
			}else{
				listeClients=clientsDAO.selectAll();
				if(listeClients.get(listeClients.size()-1).getCodeClient() == codeClient){
					leClient = listeClients.get(0);
				}else{
					clientPrecedent = listeClients.get(0);
					for(int i = 1; i < listeClients.size();i++){
						if(clientPrecedent.getCodeClient() == codeClient){
							leClient = listeClients.get(i);
						}
						clientPrecedent = listeClients.get(i);
					}
				}
			}			
		}
		return leClient;
	}
	
	public Clients clientPrecedent(int codeClient) throws BLLException, DALException{
		Clients leClient = null;
		Clients clientSuivant;
		List<Clients> listeClients = new ArrayList<Clients>();
		if(codeClient <= 0 ){
			throw new BLLException("(ClientsManager)clientPrecedent : Le codeClient ne peut pas être null");
		}else
		{
			if(clientsDAO.selectById(codeClient) == null){
				throw new BLLException("(ClientsManager)clientPrecedent : Le client n'éxiste pas (id incorrect)");
			}else{
				listeClients=clientsDAO.selectAll();
				clientSuivant = listeClients.get(listeClients.size()-1);
				
				if(listeClients.get(0).getCodeClient() == codeClient){
					leClient = listeClients.get(listeClients.size()-1);
				}else{
					for(int i = listeClients.size()-2; i >=0 ;i--){
						if(clientSuivant.getCodeClient() == codeClient){
							leClient = listeClients.get(i);
						}
						clientSuivant=listeClients.get(i);
					}					
				}
			}			
		}
		return leClient;
	}
	
	public List<Clients> selectAll() throws BLLException{		
		try 
		{
			return clientsDAO.selectAll();
		} 
		catch (DALException e) 
		{
			throw new BLLException(e.getMessage());
		}
	}

	public int ajouterClient(Clients leClient) throws BLLException, DALException {
		int codeClient = -1;
		if(leClient == null){
			throw new BLLException("(ClientsManager)ajouterClient : le client est null");
		}else{
			codeClient  = clientsDAO.addClient(leClient);
		}
		return codeClient; 
	}
	
	public void editClient(Clients leClient) throws DALException, BLLException{
		if(leClient == null || leClient.getCodeClient() <=0){
			throw new BLLException("(ClientsManager)ajouterClient : le client ni le codeClient ne peuvent etre null");
		}else{
			clientsDAO.editClient(leClient);
		}
	}
	
	public static ClientsManager getInstance() throws BLLException
	{
		if(ClientsManager.instance == null)
		{
			ClientsManager.instance = new ClientsManager();
		}
		return ClientsManager.instance;
	}
}
