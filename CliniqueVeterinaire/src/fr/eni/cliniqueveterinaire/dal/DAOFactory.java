package fr.eni.cliniqueveterinaire.dal;

import fr.eni.cliniqueveterinaire.dal.jdbc.*;

public class DAOFactory {
	
	public static ClientsDAO getClientsDAO()
	{
		return null;
       // return new ClientDAOJdbcImpl();
    }
	
	public static PersonnelsDAO getPersonnelsDAO()
	{
		PersonnelsDAO personnelsDAO = null;
		try {
			personnelsDAO=(PersonnelsDAO ) Class.forName("fr.eni.cliniqueveterinaire.dal.jdbc.PersonnelsDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personnelsDAO;
    }
}
