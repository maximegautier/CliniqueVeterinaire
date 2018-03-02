package fr.eni.cliniqueveterinaire.dal;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Clients;

public interface ClientsDAO {
	
	public Clients selectById(int id) throws DALException;
	
	public Clients selectByName(String nomClient) throws DALException;
	
	public List<Clients> selectAll() throws DALException;
	
	public void addClient(Clients leClient) throws DALException;
	
	public void editClient(Clients leClient) throws DALException;

	void deleteClient(int codeClient) throws DALException;
	
	public Clients selectFirst() throws DALException;
}
