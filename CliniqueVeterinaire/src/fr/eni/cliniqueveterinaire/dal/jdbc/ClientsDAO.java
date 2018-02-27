package fr.eni.cliniqueveterinaire.dal.jdbc;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.dal.DALException;

import java.util.List;

public interface ClientsDAO {
	
	public Clients selectById(int id);
	
	public Clients selectByName(String nomClient);
	
	public List<Clients> selectAll();
	
	public void addClient(Clients leClient)throws DALException;
	
	public void editClient(Clients leClient)throws DALException;
	
	public void deleteClient(Clients leClient) throws DALException;
}
