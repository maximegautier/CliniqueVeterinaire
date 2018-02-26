package fr.eni.cliniqueveterinaire.dal;

import fr.eni.cliniqueveterinaire.dal.jdbc.*;

public class DAOFactory {
	public static ClientDAO getArticleDAO(){
        return new ClientDAOJdbcImpl();
    }
}
