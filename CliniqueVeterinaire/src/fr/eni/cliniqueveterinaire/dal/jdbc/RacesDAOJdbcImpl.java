package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Races;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.RacesDAO;

/* Créé par Erwin DUPUIS */
public class RacesDAOJdbcImpl implements RacesDAO
{
    //region DECLARATION


    //endregion DECLARATION

    //region CTOR

	public RacesDAOJdbcImpl() throws DALException
	{
	}

    //endregion CTOR
    
    //region METHODS

	@Override
	public List<Races> selectRaces(String espece) throws DALException 
	{
		List<Races> aRetourner = new ArrayList<Races>();
		Connection cnx = JdbcTools.getConnection();
		String rqtSelectRaces = "SELECT * FROM Races WHERE Espece = ?";
		PreparedStatement psSelectRaces = null;
		ResultSet rsSelectRaces = null;
		
		try 
		{			
			psSelectRaces = cnx.prepareStatement(rqtSelectRaces);
			psSelectRaces.setString(1, espece);
			rsSelectRaces = psSelectRaces.executeQuery();
			
			while(rsSelectRaces.next())
			{
				Races tmp = new Races(
						rsSelectRaces.getString("Race"),
						rsSelectRaces.getString("Espece"));
				
				aRetourner.add(tmp);
			}
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
		finally
		{
			try 
			{
				psSelectRaces.close();
				rsSelectRaces.close();
			}
			catch (SQLException e) 
			{
				throw new DALException(e.getMessage());
			}			
		}
		
		return aRetourner;
	}	
    
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
