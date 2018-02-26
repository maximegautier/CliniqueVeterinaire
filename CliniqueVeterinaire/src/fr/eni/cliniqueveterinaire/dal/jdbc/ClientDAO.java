package fr.eni.cliniqueveterinaire.dal.jdbc;


import fr.eni.cliniqueveterinaire.bo.Client;
import java.util.List;

public interface ClientDAO {
	
	public Client selectById(int id);
	
	public Client selectByName(String nomClient);
	
	public list<Client> selectAll();
	
	public void addClient(Client leClient);
	
	public void editClient(Client leClient);
}
