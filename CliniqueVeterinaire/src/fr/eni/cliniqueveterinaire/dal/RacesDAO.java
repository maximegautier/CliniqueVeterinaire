package fr.eni.cliniqueveterinaire.dal;

import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Races;

/* Cr�e par Erwin DUPUIS */
public interface RacesDAO 
{    
    public List<Races> selectRaces(String espece) throws DALException;
}
