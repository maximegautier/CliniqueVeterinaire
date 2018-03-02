package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.dal.AgendasDAO;
import fr.eni.cliniqueveterinaire.dal.DALException;

/* Créé par Erwin DUPUIS */
public class AgendasDAOJdbcImpl implements AgendasDAO
{
    //region DECLARATION


    //endregion DECLARATION

    //region CTOR

	public AgendasDAOJdbcImpl()
	{		
	}

    //endregion CTOR
    
    //region METHODS
    
	@Override
	public List<Agendas> selectParDate(Date jour) throws DALException 
	{		
		List<Agendas> aRetourner = new ArrayList<Agendas>();
		Connection cnx = JdbcTools.getConnection();
		String rqtSelectParDate = "SELECT * FROM Agendas WHERE DateRdv = ?";
		PreparedStatement rqt = null;
		ResultSet rs = null;
		
		try 
		{
			rqt = cnx.prepareStatement(rqtSelectParDate);
			rqt.setTimestamp(1, new java.sql.Timestamp(jour.getTime()));
			rs = rqt.executeQuery();
			Agendas tmpRdv = null;
			
			while(rs.next())
			{
				tmpRdv = new Agendas(
						rs.getInt("CodeVeto"),
						rs.getInt("CodeAnimal"),
						rs.getDate("DateRdv"));
			}
			aRetourner.add(tmpRdv);
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
		finally
		{
			try
			{
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
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
		Connection cnx = JdbcTools.getConnection();
		String rqtAjouter = "INSERT INTO Agendas(CodeVeto, DateRdv, CodeAnimal) VALUES (?,?,?)";
		PreparedStatement psAjouter = null;
		ResultSet rs = null;
		
		try 
		{
			psAjouter = cnx.prepareStatement(rqtAjouter);
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
				cnx.close();
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
		Connection cnx = JdbcTools.getConnection();
		String rqtSupprimer = "DELETE FROM Agendas WHERE CodeVeto = ? AND DateRdv = ? AND CodeAnimal = ?";
		PreparedStatement psSupprimer = null;
		ResultSet rs = null;
		
		try
		{
			psSupprimer = cnx.prepareStatement(rqtSupprimer);
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
				cnx.close();
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


    //endregion GET/SET
}
