package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.dal.AgendasDAO;
import fr.eni.cliniqueveterinaire.dal.DALException;

/* Cr�� par Erwin DUPUIS */
public class AgendasDAOJdbcImpl implements AgendasDAO
{
    //region DECLARATION

	private Connection cnx;

    //endregion DECLARATION

    //region CTOR

	public AgendasDAOJdbcImpl()
	{		
	}

    //endregion CTOR
    
    //region METHODS
    
	@Override
	public List<Agendas> selectParDate(Date dateDebut, Date dateFin) throws DALException 
	{		
		List<Agendas> aRetourner = new ArrayList<Agendas>();
		String rqtSelectParDate = "SELECT * FROM Agendas WHERE DateRdv BETWEEN ? AND ?";
		PreparedStatement psSelectParDate = null;
		ResultSet rsSelectParDate = null;
		
		try 
		{
			psSelectParDate = getCnx().prepareStatement(rqtSelectParDate);
			psSelectParDate.setDate(1, (java.sql.Date) dateDebut);
			psSelectParDate.setDate(2, (java.sql.Date) dateFin);
			
			rsSelectParDate = psSelectParDate.executeQuery();
			
			if(rsSelectParDate.next())
			{
				Agendas tmpRdv = new Agendas(
						rsSelectParDate.getInt("CodeVeto"),
						rsSelectParDate.getInt("CodeAnimal"),
						rsSelectParDate.getDate("DateRdv"));
				
				aRetourner.add(tmpRdv);
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
				psSelectParDate.close();
				rsSelectParDate.close();
			}
			catch(SQLException e)
			{
				throw new DALException(e.getMessage());
			}
		}
		
		return aRetourner;
	}

	@Override
	public boolean ajouter(Agendas aAjouter) throws DALException 
	{	
		boolean aRetourner = false;
		String rqtAjouter = "INSERT INTO Agendas(CodeVeto, DateRdv, CodeAnimal) VALUES (?,?,?)";
		PreparedStatement psAjouter = null;
		ResultSet rs = null;
		
		try 
		{
			psAjouter = getCnx().prepareStatement(rqtAjouter);
			psAjouter.setInt(1, aAjouter.getCodeVeto());
			psAjouter.setDate(2, (java.sql.Date) aAjouter.getDateRdv());
			psAjouter.setInt(3, aAjouter.getCodeAnimal());
			
			int nbRows = psAjouter.executeUpdate();
			if(nbRows == 1)
            {
                rs = psAjouter.getGeneratedKeys();

                aRetourner = true;
            }

			psAjouter.close();
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
		finally
		{
			try
			{
				psAjouter.close();
				rs.close();
			}
			catch(SQLException e)
			{
				throw new DALException(e.getMessage());
			}
		}
		
		return aRetourner;
	}

	@Override
	public boolean supprimer(Agendas aSupprimer) throws DALException 
	{
		boolean aRetourner = false;
		String rqtSupprimer = "DELETE FROM Agendas WHERE CodeVeto = ? AND DateRdv = ? AND CodeAnimal = ?";
		PreparedStatement psSupprimer = null;
		ResultSet rs = null;
		
		try
		{
			psSupprimer = getCnx().prepareStatement(rqtSupprimer);
			psSupprimer.setInt(1, aSupprimer.getCodeVeto());
			psSupprimer.setDate(2, (java.sql.Date) aSupprimer.getDateRdv());
			psSupprimer.setInt(3, aSupprimer.getCodeAnimal());
			
			int nbRows = psSupprimer.executeUpdate();
			if(nbRows == 1)
	        {
	            rs = psSupprimer.getGeneratedKeys();
	
	            aRetourner = true;
	        }
	
			psSupprimer.close();
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
		finally
		{
			try
			{
				psSupprimer.close();
				rs.close();				
			}
			catch(SQLException e)
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
