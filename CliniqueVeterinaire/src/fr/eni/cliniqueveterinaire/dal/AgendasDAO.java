package fr.eni.cliniqueveterinaire.dal;

import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;

public interface AgendasDAO 
{
	public List<Agendas> SelectParDate(Date dateDebut, Date dateFin) throws DALException;
	
	public boolean Ajouter(Agendas aAjouter) throws DALException;
	
	public boolean Supprimer(Agendas aSupprimer) throws DALException;
}
