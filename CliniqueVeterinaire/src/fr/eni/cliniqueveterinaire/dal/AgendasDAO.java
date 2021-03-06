package fr.eni.cliniqueveterinaire.dal;

import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;

/* Cr�� par Erwin DUPUIS */
public interface AgendasDAO 
{
	public List<Agendas> selectParDate(Date jour) throws DALException;
	
	public List<Agendas> selectParDateVeterinaire(Date jour, int codeVeterinaire) throws DALException;
	
	public boolean ajouter(Agendas aAjouter) throws DALException;
	
	public boolean supprimer(Agendas aSupprimer) throws DALException;
	
	public boolean supprimerParNom(int codeAnimal) throws DALException;
	
	public boolean verifieSiExiste(Agendas aVerifier) throws DALException;
	
}
