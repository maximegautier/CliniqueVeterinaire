package fr.eni.cliniqueveterinaire.dal;

import java.util.List;
import fr.eni.cliniqueveterinaire.bo.Personnels;

public interface PersonnelsDAO {

	public Personnels checkConnexion(String nom,String mdp) throws DALException;
	
	public Personnels selectById(int id) throws DALException;
	
	public Personnels selectByName(String nomClient) throws DALException;
	
	public List<Personnels> selectAll() throws DALException;
	
	public int insert(Personnels Personnels) throws DALException;
	
	public void update(Personnels Personnels) throws DALException;
	
	public void delete(Personnels Personnels) throws DALException;
	
	public List<String> selectRole() throws DALException;
	
	public boolean verifieSiExiste(String nomPersonnel) throws DALException;
	
	public List<Personnels> selectVeterinaire() throws DALException;

	public void insertPersonnelsRoles(int codePers, String role) throws DALException;
	
	public boolean selectRdvVeterinaire(int id) throws DALException;

	boolean ajouterPersonnelsRole(int id, String role) throws DALException;
	
}
