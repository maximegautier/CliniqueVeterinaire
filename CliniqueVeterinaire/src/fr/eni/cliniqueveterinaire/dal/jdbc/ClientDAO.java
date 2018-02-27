package fr.eni.cliniqueveterinaire.dal.jdbc;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Clients;
import java.util.List;

public interface ClientDAO {
	
	public Clients selectById(int id);
	
	public Clients selectByName(String nomClient);
	
	public List<Clients> selectAll();
	
	public void addClient(Clients leClient);
	
	public void editClient(Clients leClient);
}
