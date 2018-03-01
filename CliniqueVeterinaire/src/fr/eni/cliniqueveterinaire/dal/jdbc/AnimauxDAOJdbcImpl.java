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
	
	private Connection cnx;

    //endregion DECLARATION

    //region CTOR

	public AnimauxDAOJdbcImpl() throws DALException 
	{
	}

    //endregion CTOR
    
    //region METHODS
    
	@Override
	public List<Animaux> SelectAnimaux(int CodeClient) throws DALException
	{
		List<Animaux> aRetourner = new ArrayList<Animaux>();
		
		try 
		{
			String rqtSelectAnimaux = "SELECT * FROM Animaux WHERE CodeClient = ? AND Archive = 0";
			PreparedStatement psSelectAnimaux = getCnx().prepareStatement(rqtSelectAnimaux);	
			psSelectAnimaux.setInt(1, CodeClient);
			ResultSet rsSelectAnimaux = psSelectAnimaux.executeQuery();
			
			if(rsSelectAnimaux.next())
			{
				Animaux tmp = new Animaux(
						rsSelectAnimaux.getInt("CodeAnimal"), 
						rsSelectAnimaux.getInt("CodeClient"), 
						rsSelectAnimaux.getString("Race"), 
						rsSelectAnimaux.getString("NomAnimal"), 
						rsSelectAnimaux.getString("Sexe"), 
						rsSelectAnimaux.getString("Couleur"), 
						rsSelectAnimaux.getString("Espece"), 
						rsSelectAnimaux.getString("Tatouage"), 
						rsSelectAnimaux.getString("Antecedents"), 
						rsSelectAnimaux.getBoolean("Archive"));
				aRetourner.add(tmp);
			}
			
			psSelectAnimaux.close();
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
		
		return aRetourner;
	}

	@Override
	public List<String> SelectEspeces() throws DALException 
	{
		List<String> aRetourner = new ArrayList<String>();
		
		
		try 
		{
			String rqtSelectEspeces = "SELECT DISTINCT Espece FROM Animaux";
			PreparedStatement psSelectEspeces = getCnx().prepareStatement(rqtSelectEspeces);
			ResultSet rsSelectEspeces = psSelectEspeces.executeQuery();
			
			if(rsSelectEspeces.next())
			{
				aRetourner.add(rsSelectEspeces.getString("Espece"));
			}
			
			rsSelectEspeces.close();
			
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}	
				
		return aRetourner;
	}
	
	@Override
	public Animaux SelectAnimal(int CodeAnimal) throws DALException 
	{
		Animaux aRetourner = null;
				
		try 
		{	
			String rqtSelectAnimal = "SELECT * FROM Animaux WHERE CodeAnimal = ? AND Archive = 0";
			PreparedStatement psSelectAnimal = getCnx().prepareStatement(rqtSelectAnimal);
			psSelectAnimal.setInt(1, CodeAnimal);
			ResultSet rsSelectAnimal = psSelectAnimal.executeQuery();
			
			if(rsSelectAnimal.next())
			{
				Animaux tmp = new Animaux(
						rsSelectAnimal.getInt("CodeAnimal"), 
						rsSelectAnimal.getInt("CodeClient"), 
						rsSelectAnimal.getString("Race"), 
						rsSelectAnimal.getString("NomAnimal"), 
						rsSelectAnimal.getString("Sexe"), 
						rsSelectAnimal.getString("Couleur"), 
						rsSelectAnimal.getString("Espece"), 
						rsSelectAnimal.getString("Tatouage"), 
						rsSelectAnimal.getString("Antecedents"), 
						rsSelectAnimal.getBoolean("Archive"));
				aRetourner = tmp;
			}	
			
			psSelectAnimal.close();			
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}

		return aRetourner;
	}

	@Override
	public int Ajouter(Animaux aAjouter) throws DALException 
	{
		int aRetourner;
		
		try 
		{
			String rqtAjouterAnimal = "	INSERT INTO Animaux(NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement psAjouterAnimal = getCnx().prepareStatement(rqtAjouterAnimal, Statement.RETURN_GENERATED_KEYS);
			psAjouterAnimal.setString(1, aAjouter.getNomAnimal());
			psAjouterAnimal.setString(2, aAjouter.getSexe());
			psAjouterAnimal.setString(3, aAjouter.getCouleur());
			psAjouterAnimal.setString(4, aAjouter.getRace());
			psAjouterAnimal.setString(5, aAjouter.getEspece());
			psAjouterAnimal.setInt(6, aAjouter.getCodeClient());
			psAjouterAnimal.setString(7, aAjouter.getTatouage());
			psAjouterAnimal.setString(8, aAjouter.getAntecedents());
			psAjouterAnimal.setBoolean(9, aAjouter.getArchive());
			
			int nbRows = psAjouterAnimal.executeUpdate();
			if(nbRows == 1)
            {
                ResultSet rs = psAjouterAnimal.getGeneratedKeys();

                if(rs.next())
                {
                    aAjouter.setCodeAnimal(rs.getInt(1));
                }
            }

			psAjouterAnimal.close();
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}

		return aAjouter.getCodeAnimal();
	}

	@Override
	public boolean Supprimer(int CodeAnimal) throws DALException 
	{
		boolean aRetourner = false;
		
		try 
		{
			String rqtSupprimerAnimal = "UPDATE Animaux SET Archive = 1 WHERE CodeAnimal = ?";
			PreparedStatement psSupprimerAnimal = getCnx().prepareStatement(rqtSupprimerAnimal);
			psSupprimerAnimal.setInt(1,  CodeAnimal);
			int nbRows = psSupprimerAnimal.executeUpdate();
			
			if(nbRows != 0)
			{
				aRetourner = true;
			}	
			
			psSupprimerAnimal.close();
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
		
		return aRetourner;
	}

	@Override
	public boolean Modifier(Animaux aModifier) throws DALException 
	{
		boolean aRetourner = false;
		
		try 
		{
			String rqtModifierAnimal = "UPDATE Animaux SET NomAnimal = ?, Sexe = ?, Couleur = ?, Race = ?, Espece = ?, CodeClient = ?, Tatouage = ?, Antecedents = ?, Archive = ? WHERE CodeAnimal = ?";
			PreparedStatement psModifierAnimal = getCnx().prepareStatement(rqtModifierAnimal);
			psModifierAnimal.setString(1, aModifier.getNomAnimal());
			psModifierAnimal.setString(2, aModifier.getSexe());
			psModifierAnimal.setString(3, aModifier.getCouleur());
			psModifierAnimal.setString(4, aModifier.getRace());
			psModifierAnimal.setString(5, aModifier.getEspece());
			psModifierAnimal.setInt(6, aModifier.getCodeClient());
			psModifierAnimal.setString(7, aModifier.getTatouage());
			psModifierAnimal.setString(8, aModifier.getAntecedents());
			psModifierAnimal.setBoolean(9, aModifier.getArchive());
			psModifierAnimal.setInt(10, aModifier.getCodeAnimal());
			
			int nbRows = psModifierAnimal.executeUpdate();
			
			if(nbRows != 0)
			{
				aRetourner = true;
			}	
			
			psModifierAnimal.close();
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
		
		return aRetourner;
	}
	
	public boolean VerifieSiExiste(int codeClient, String nomAnimal) throws DALException
	{
		boolean aRetourner = false;
		
		try 
		{
			String rqtVerifieSiExiste = "SELECT * FROM Animaux WHERE NomAnimal = '?' AND CodeClient = '?'";
			PreparedStatement psVerifieSiExiste = getCnx().prepareStatement(rqtVerifieSiExiste);
			psVerifieSiExiste.setString(1, nomAnimal);
			psVerifieSiExiste.setInt(2, codeClient);		
			ResultSet rsVerifieSiExiste = psVerifieSiExiste.executeQuery();
			
			if(rsVerifieSiExiste.next())
			{
				aRetourner = true;
			}
			
		} 
		catch (SQLException e) 
		{
			throw new DALException(e.getMessage());
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
