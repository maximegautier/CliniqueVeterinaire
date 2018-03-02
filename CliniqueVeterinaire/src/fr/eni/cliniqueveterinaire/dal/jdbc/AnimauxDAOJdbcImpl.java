package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Animaux;
import fr.eni.cliniqueveterinaire.dal.AnimauxDAO;
import fr.eni.cliniqueveterinaire.dal.DALException;

/* Crée par Erwin DUPUIS */
public class AnimauxDAOJdbcImpl implements AnimauxDAO
{
    //region DECLARATION
	

    //endregion DECLARATION

    //region CTOR

	public AnimauxDAOJdbcImpl() throws DALException 
	{
	}

    //endregion CTOR
    
    //region METHODS
    
	@Override
	public List<Animaux> selectAnimaux(int CodeClient) throws DALException
	{
		Connection cnx = JdbcTools.getConnection();
		List<Animaux> aRetourner = new ArrayList<Animaux>();
		String rqtSelectAnimaux = "SELECT * FROM Animaux WHERE CodeClient = ? AND archive = 0";
		PreparedStatement psSelectAnimaux = null;
		ResultSet rsSelectAnimaux = null;
		
		try 
		{
			psSelectAnimaux = cnx.prepareStatement(rqtSelectAnimaux);	
			psSelectAnimaux.setInt(1, CodeClient);
			rsSelectAnimaux = psSelectAnimaux.executeQuery();
			
			while(rsSelectAnimaux.next())
			{
				Animaux tmp = new Animaux(
						rsSelectAnimaux.getInt("CodeAnimal"), 
						rsSelectAnimaux.getInt("CodeClient"), 
						rsSelectAnimaux.getString("Race"), 
						rsSelectAnimaux.getString("NomAnimal"), 
						rsSelectAnimaux.getString("sexe"), 
						rsSelectAnimaux.getString("Couleur"), 
						rsSelectAnimaux.getString("Espece"), 
						rsSelectAnimaux.getString("tatouage"), 
						rsSelectAnimaux.getString("Antecedents"), 
						rsSelectAnimaux.getBoolean("archive"));
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
				psSelectAnimaux.close();
				rsSelectAnimaux.close();
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
	public List<String> selectEspeces() throws DALException 
	{
		Connection cnx = JdbcTools.getConnection();
		List<String> aRetourner = new ArrayList<String>();
		String rqtSelectEspeces = "SELECT * FROM Especes";
		PreparedStatement psSelectEspeces = null;
		ResultSet rsSelectEspeces = null;
		
		try 
		{			
			psSelectEspeces = cnx.prepareStatement(rqtSelectEspeces);
			rsSelectEspeces = psSelectEspeces.executeQuery();
			
			while(rsSelectEspeces.next())
			{
				aRetourner.add(rsSelectEspeces.getString("Libelle"));
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
				psSelectEspeces.close();
				rsSelectEspeces.close();
				cnx.close();
			}
			catch(SQLException e)
			{
			throw new DALException(e.getMessage())		;		
			}
		}
				
		return aRetourner;
	}
	
	/*public String selectClientParAnimal(int codeAnimal) throws DALException
	{
		Connection cnx = JdbcTools.getConnection();
		Animaux aRetourner = null;
		String rqtSelectClientParAnimal = "SELECT C.NomClient + ' ' + C.PrenomClient as DisplayName FROM Animaux as A LEFT JOIN Clients as C on A.CodeClient = C.CodeClient WHERE A.CodeAnimal = 3";	
		PreparedStatement psSelectClientParAnimal = null;
		ResultSet rsSelectClientParAnimal = null;
		
		try
		{

		}
	}*/
	
	@Override
	public Animaux selectAnimal(int CodeAnimal) throws DALException 
	{
		Connection cnx = JdbcTools.getConnection();
		Animaux aRetourner = null;
		String rqtSelectAnimal = "SELECT * FROM Animaux WHERE CodeAnimal = ? AND archive = 0";	
		PreparedStatement psSelectAnimal = null;
		ResultSet rsSelectAnimal = null;
		
		try 
		{	
			psSelectAnimal = cnx.prepareStatement(rqtSelectAnimal);
			psSelectAnimal.setInt(1, CodeAnimal);
			rsSelectAnimal = psSelectAnimal.executeQuery();
			
			while(rsSelectAnimal.next())
			{
				Animaux tmp = new Animaux(
						rsSelectAnimal.getInt("CodeAnimal"), 
						rsSelectAnimal.getInt("CodeClient"), 
						rsSelectAnimal.getString("Race"), 
						rsSelectAnimal.getString("NomAnimal"), 
						rsSelectAnimal.getString("sexe"), 
						rsSelectAnimal.getString("Couleur"), 
						rsSelectAnimal.getString("Espece"), 
						rsSelectAnimal.getString("tatouage"), 
						rsSelectAnimal.getString("Antecedents"), 
						rsSelectAnimal.getBoolean("archive"));
				aRetourner = tmp;
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
				psSelectAnimal.close();
				rsSelectAnimal.close();
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
	public int ajouter(Animaux aAjouter) throws DALException 
	{
		int aRetourner;
		Connection cnx = JdbcTools.getConnection();
		String rqtAjouterAnimal = "	INSERT INTO Animaux(NomAnimal, sexe, Couleur, Race, Espece, CodeClient, tatouage, Antecedents, archive) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement psAjouterAnimal = null;
		ResultSet rs = null;
		
		try 
		{
			psAjouterAnimal = cnx.prepareStatement(rqtAjouterAnimal, Statement.RETURN_GENERATED_KEYS);
			psAjouterAnimal.setString(1, aAjouter.getNomAnimal());
			psAjouterAnimal.setString(2, aAjouter.getsexe());
			psAjouterAnimal.setString(3, aAjouter.getCouleur());
			psAjouterAnimal.setString(4, aAjouter.getRace());
			psAjouterAnimal.setString(5, aAjouter.getEspece());
			psAjouterAnimal.setInt(6, aAjouter.getCodeClient());
			psAjouterAnimal.setString(7, aAjouter.gettatouage());
			psAjouterAnimal.setString(8, aAjouter.getAntecedents());
			psAjouterAnimal.setBoolean(9, aAjouter.getarchive());
			
			int nbRows = psAjouterAnimal.executeUpdate();
			if(nbRows == 1)
            {
                rs = psAjouterAnimal.getGeneratedKeys();

                if(rs.next())
                {
                    aAjouter.setCodeAnimal(rs.getInt(1));
                }
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
				psAjouterAnimal.close();
				rs.close();
				cnx.close();
			}
			catch(SQLException e)
			{
				throw new DALException(e.getMessage());
			}
		}

		return aAjouter.getCodeAnimal();
	}

	@Override
	public boolean supprimer(int CodeAnimal) throws DALException 
	{
		boolean aRetourner = false;
		Connection cnx = JdbcTools.getConnection();
		String rqtSupprimerAnimal = "UPDATE Animaux SET archive = 1 WHERE CodeAnimal = ?";
		PreparedStatement psSupprimerAnimal = null;
		
		try 
		{
			psSupprimerAnimal = cnx.prepareStatement(rqtSupprimerAnimal);
			psSupprimerAnimal.setInt(1,  CodeAnimal);
			int nbRows = psSupprimerAnimal.executeUpdate();
			
			if(nbRows != 0)
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
				psSupprimerAnimal.close();
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
	public boolean modifier(Animaux aModifier) throws DALException 
	{
		boolean aRetourner = false;
		Connection cnx = JdbcTools.getConnection();
		String rqtModifierAnimal = "UPDATE Animaux SET NomAnimal = ?, sexe = ?, Couleur = ?, Race = ?, Espece = ?, CodeClient = ?, tatouage = ?, Antecedents = ?, archive = ? WHERE CodeAnimal = ?";
		PreparedStatement psModifierAnimal = null;
		
		try 
		{
			psModifierAnimal = cnx.prepareStatement(rqtModifierAnimal);
			psModifierAnimal.setString(1, aModifier.getNomAnimal());
			psModifierAnimal.setString(2, aModifier.getsexe());
			psModifierAnimal.setString(3, aModifier.getCouleur());
			psModifierAnimal.setString(4, aModifier.getRace());
			psModifierAnimal.setString(5, aModifier.getEspece());
			psModifierAnimal.setInt(6, aModifier.getCodeClient());
			psModifierAnimal.setString(7, aModifier.gettatouage());
			psModifierAnimal.setString(8, aModifier.getAntecedents());
			psModifierAnimal.setBoolean(9, aModifier.getarchive());
			psModifierAnimal.setInt(10, aModifier.getCodeAnimal());
			
			int nbRows = psModifierAnimal.executeUpdate();
			
			if(nbRows != 0)
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
				psModifierAnimal.close();
				cnx.close();
			}
			catch(SQLException e)
			{
				throw new DALException(e.getMessage());
			}
		}
		
		return aRetourner;
	}
	
	public boolean verifieSiExiste(int codeClient, String nomAnimal) throws DALException
	{
		boolean aRetourner = false;
		Connection cnx = JdbcTools.getConnection();
		String rqtVerifieSiExiste = "SELECT * FROM Animaux WHERE NomAnimal = ? AND CodeClient = ?";
		PreparedStatement psVerifieSiExiste = null;
		ResultSet rsVerifieSiExiste = null;
		
		try 
		{
			psVerifieSiExiste = cnx.prepareStatement(rqtVerifieSiExiste);
			psVerifieSiExiste.setString(1, nomAnimal);
			psVerifieSiExiste.setInt(2, codeClient);		
			rsVerifieSiExiste = psVerifieSiExiste.executeQuery();
			
			if(rsVerifieSiExiste.next())
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
				psVerifieSiExiste.close();
				rsVerifieSiExiste.close();
				cnx.close();
			}
			catch(SQLException e)
			{
				throw new DALException(e.getMessage());
			}
		}
		
		return aRetourner;
	}
	
	//************
	//UTILITAIRES
	//************	
	
    private boolean isEmptyOrNull(String toTest)
    {
        if(toTest != null && !toTest.trim().isEmpty())
            return false;
        else
            return true;
    }

    private boolean isNegativeInt(int toCheck)
    {
        if(toCheck >= 0)
            return false;
        else
            return true;
    }
    
    //endregion METHODS

    //region GET/SET


    //endregion GET/SET
}
