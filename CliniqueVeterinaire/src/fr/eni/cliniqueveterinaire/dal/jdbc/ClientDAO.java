package fr.eni.cliniqueveterinaire.dal.jdbc;


import fr.eni.cliniqueveterinaire.bo.Clients;
import java.util.List;

public interface ClientDAO {
	
	public Clients selectById(int id);
	
	public Clients selectByName(String nomClient);
	
	public list<Clients> selectAll();
	
	public void addClient(Clients leClient);
	
	public void editClient(Clients leClient);
}
