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

/* Cr�� par Erwin DUPUIS */
public class RacesDAOJdbcImpl implements RacesDAO
{
    //region DECLARATION

	private Connection cnx;

    //endregion DECLARATION

    //region CTOR

	public RacesDAOJdbcImpl() throws DALException
	{
	}

    //endregion CTOR
    
    //region METHODS
    
	@Override
	public List<Races> SelectRacesChat() throws DALException 
	{
		List<Races> aRetourner = new ArrayList<Races>();
		String rqtSelectRacesChat = "SELECT * FROM Races WHERE Espece = 'Chat'";
		PreparedStatement psSelectRacesChat = null;
		ResultSet rsSelectRacesChat = null;
		
		try 
		{
			psSelectRacesChat = getCnx().prepareStatement(rqtSelectRacesChat);
			rsSelectRacesChat = psSelectRacesChat.executeQuery();
			
			if(rsSelectRacesChat.next())
			{
				Races tmp = new Races(
						rsSelectRacesChat.getString("Race"),
						rsSelectRacesChat.getString("Espece"));
				
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
				psSelectRacesChat.close();
				rsSelectRacesChat.close();
			} 
			catch (SQLException e) 
			{
				throw new DALException(e.getMessage());
			}
		}
		
		return aRetourner;
	}

	@Override
	public List<Races> SelectRacesChien() throws DALException 
	{
		List<Races> aRetourner = new ArrayList<Races>();
		String rqtSelectRacesChien = "SELECT * FROM Races WHERE Espece = 'Chien'";
		PreparedStatement psSelectRacesChien = null;
		ResultSet rsSelectRacesChien = null;
		
		try 
		{			
			psSelectRacesChien = cnx.prepareStatement(rqtSelectRacesChien);
			rsSelectRacesChien = psSelectRacesChien.executeQuery();
			
			if(rsSelectRacesChien.next())
			{
				Races tmp = new Races(
						rsSelectRacesChien.getString("Race"),
						rsSelectRacesChien.getString("Espece"));
				
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
				psSelectRacesChien.close();
				rsSelectRacesChien.close();
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

	public Connection getCnx() throws DALException
	{
		if(cnx == null)
		{
			cnx = JdbcTools.getConnection();
		}
		return cnx;
	}

    //endregion GET/SET
}
