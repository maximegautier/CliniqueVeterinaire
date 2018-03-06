package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Agendas;
import fr.eni.cliniqueveterinaire.bo.Personnels;
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
		Connection cnx = null;
		String rqtSelectParDate = "SELECT * FROM Agendas WHERE CAST(DateRdv as Date) = ? ORDER BY DateRdv";
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Agendas> aRetourner = new ArrayList<Agendas>();
		
		try 
		{
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectParDate);
			rqt.setTimestamp(1, new java.sql.Timestamp(jour.getTime()));
			rs = rqt.executeQuery();
			Agendas tmpRdv = null;
			
			while(rs.next())
			{
				tmpRdv = new Agendas(
						rs.getInt("CodeVeto"),
						rs.getInt("CodeAnimal"),
						new Date(rs.getTimestamp("DateRdv").getTime())	
				);
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
	public List<Agendas> selectParDateVeterinaire(Date jour, int codeVeterinaire) throws DALException
	{
		Connection cnx = null;
		String rqtSelectParDateVeterinaire = "SELECT * FROM Agendas WHERE CAST(DateRdv as Date) = ? AND CodeVeto = ? ORDER BY DateRdv";
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Agendas> aRetourner = new ArrayList<Agendas>();
		
		try 
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String reportDate = df.format(jour);			
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectParDateVeterinaire);
			rqt.setString(1, reportDate);
			rqt.setInt(2, codeVeterinaire);
			rs = rqt.executeQuery();
			Agendas tmpRdv = null;
			
			while(rs.next())
			{
				tmpRdv = new Agendas(
						rs.getInt("CodeVeto"),
						rs.getInt("CodeAnimal"),
						new Date(rs.getTimestamp("DateRdv").getTime())
				);
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
		
		try 
		{
			psAjouter = cnx.prepareStatement(rqtAjouter);
			psAjouter.setInt(1, aAjouter.getCodeVeto());
			psAjouter.setTimestamp(2, new Timestamp(aAjouter.getDateRdv().getTime()));
			psAjouter.setInt(3, aAjouter.getCodeAnimal());
			
			int nbRows = psAjouter.executeUpdate();
			if(nbRows == 1)
            {
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
		
		try
		{
			psSupprimer = cnx.prepareStatement(rqtSupprimer);
			psSupprimer.setInt(1, aSupprimer.getCodeVeto());
			psSupprimer.setTimestamp(2, new Timestamp(aSupprimer.getDateRdv().getTime()));
			psSupprimer.setInt(3, aSupprimer.getCodeAnimal());
			
			int nbRows = psSupprimer.executeUpdate();
			if(nbRows == 1)
	        {
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
	public boolean verifieSiExiste(Agendas aVerifier) throws DALException 
	{
		boolean aRetourner = false;
		Connection cnx = null;
		String rqtVerifieSiExiste = "SELECT * FROM Agendas WHERE DateRdv = ? AND CodeVeto = ? AND CodeAnimal = ?";
		PreparedStatement rqt = null;
		ResultSet rs = null;
		
		try 
		{
			cnx = JdbcTools.getConnection();		
			rqt = cnx.prepareStatement(rqtVerifieSiExiste);
			rqt.setTimestamp(1, new Timestamp(aVerifier.getDateRdv().getTime()));	
			rqt.setInt(2, aVerifier.getCodeVeto());
			rqt.setInt(3, aVerifier.getCodeAnimal());
			rs = rqt.executeQuery();
			
			if(rs.next())
			{
				aRetourner = true;
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
				rqt.close();
				rqt.close();
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
