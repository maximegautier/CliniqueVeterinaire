package fr.eni.cliniqueveterinaire.dal;

import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.dal.jdbc.AgendasDAOJdbcImpl;
import fr.eni.cliniqueveterinaire.dal.jdbc.AnimauxDAOJdbcImpl;
import fr.eni.cliniqueveterinaire.dal.jdbc.ClientsDAOJdbcImpl;
import fr.eni.cliniqueveterinaire.dal.jdbc.PersonnelsDAOJdbcImpl;
import fr.eni.cliniqueveterinaire.dal.jdbc.RacesDAOJdbcImpl;

public class DAOFactory 
{
	private static ClientsDAO clientsDAO;
	public static ClientsDAO getClientsDAO()
	{
		if(clientsDAO == null)
		{
			clientsDAO = new ClientsDAOJdbcImpl();
		}
		return clientsDAO;
    }
	
	/* Crée par Maxime GAUTIER */
	private static PersonnelsDAO personnelsDAO;
	public static PersonnelsDAO getPersonnelsDAO()
	{
		if(personnelsDAO == null)
		{
			personnelsDAO = new PersonnelsDAOJdbcImpl();
		}
		return personnelsDAO;
    }
	
	/* Créé par Erwin DUPUIS */
	private static AnimauxDAO animauxDAO;
	public static AnimauxDAO getAnimauxDAO() throws DALException
	{
		if(animauxDAO == null)
		{
			animauxDAO = new AnimauxDAOJdbcImpl();
		}
		return animauxDAO;
	}
	
	/* Créé par Erwin DUPUIS */
	private static RacesDAO racesDAO;
	public static RacesDAO getRacesDAO() throws DALException
	{
		if(racesDAO == null)
		{
			racesDAO = new RacesDAOJdbcImpl();
		}
		return racesDAO;
	}
	
	/* Créé par Erwin DUPUIS */
	private static AgendasDAO agendasDAO;
	public static AgendasDAO getAgendasDAO() 
	{
		if(agendasDAO == null)
		{
			agendasDAO = new AgendasDAOJdbcImpl();
		}
		return agendasDAO;
	}
}
