package fr.eni.cliniqueveterinaire.dal;

import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Animaux;

/* Créé par Erwin DUPUIS */
public interface AnimauxDAO 
{
	public List<Animaux> SelectAnimaux(int CodeClient) throws DALException;
	
	public List<String> SelectEspeces() throws DALException;
	
	public Animaux SelectAnimal(int CodeAnimal) throws DALException;
	
	public int Ajouter(Animaux aAjouter) throws DALException;
	
	public boolean Supprimer(int CodeAnimal) throws DALException;

	public boolean Modifier(Animaux aModifier) throws DALException;
	
	public boolean VerifieSiExiste(int codeClient, String nomAnimal) throws DALException;
}
