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
	
}
