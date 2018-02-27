package fr.eni.cliniqueveterinaire.dal;

import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Races;

/* Crée par Erwin DUPUIS */
public interface RacesDAO 
{
    public List<Races> SelectRacesChat() throws DALException;
    
    public List<Races> SelectRacesChien() throws DALException;
}
