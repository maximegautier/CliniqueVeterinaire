package fr.eni.cliniqueveterinaire.dal;

import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;

/* Créé par Erwin DUPUIS */
public interface AgendasDAO 
{
	public List<Agendas> selectParDate(Date dateDebut, Date dateFin) throws DALException;
	
	public boolean ajouter(Agendas aAjouter) throws DALException;
	
	public boolean supprimer(Agendas aSupprimer) throws DALException;
}
