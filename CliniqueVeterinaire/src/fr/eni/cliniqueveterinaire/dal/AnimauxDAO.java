package fr.eni.cliniqueveterinaire.dal;

import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Animaux;

/* Créé par Erwin DUPUIS */
public interface AnimauxDAO 
{
	public List<Animaux> selectAnimaux(int CodeClient) throws DALException;
	
	public List<String> selectEspeces() throws DALException;
	
	public Animaux selectAnimal(int CodeAnimal) throws DALException;
	
	public int ajouter(Animaux aAjouter) throws DALException;
	
	public boolean supprimer(int CodeAnimal) throws DALException;

	public boolean modifier(Animaux aModifier) throws DALException;
	
	public boolean verifieSiExiste(int codeClient, String nomAnimal) throws DALException;
}
