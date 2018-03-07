package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.PersonnelsDAO;

public class PersonnelsDAOJdbcImpl implements PersonnelsDAO
{
	private String rqtCheckConnec = "SELECT Personnels.CodePers,Nom,Prenom,Login,MotPasse, Roles.Libelle ,Archive FROM Personnels WHERE Login = ? AND MotPasse = ? AND Archive = 0";
	private String rqtSelectById = "SELECT Personnels.CodePers,Nom,Prenom,Login,MotPasse, Roles.Libelle  ,Archive FROM Personnels WHERE Personnels.CodePers = ? AND Archive = 0";
	private String rqtSelectByName = "SELECT Personnels.CodePers,Nom,Prenom,Login,MotPasse ,Archive FROM Personnels WHERE Nom = ? AND Archive = 0";
	private String rqtSelectAll = "SELECT Personnels.CodePers,Nom,Prenom,Login,MotPasse,Archive FROM Personnels WHERE Archive = 0 ORDER BY Nom";
	private String rqtInsert = "INSERT INTO Personnels VALUES (?,?,?,?,?)";
	private String rqtInsertPersonnelsRoles = "INSERT INTO Personnels_Roles VALUES (?,?)";
	private String rqtDelete = "UPDATE Personnels SET Archive = 1 WHERE CodePers = ?";
	private String rqtUpdate = "UPDATE Personnels SET Nom=?, Prenom=?, Login=?, MotPasse=?, Archive = ? WHERE CodePers = ?";
	private String rqtSelectRole = "SELECT Libelle From Roles";
	private String rqtVerifieSiExiste = "SELECT * FROM Personnels WHERE Nom = ?";
	private String rqtSelectVeterinaire = "SELECT Personnels.CodePers,Nom,Prenom,Login,MotPasse, Roles.Libelle, Archive FROM Personnels JOIN Personnels_Roles ON Personnels_Roles.CodePers = Personnels.CodePers JOIN Roles ON Roles.Libelle = Personnels_Roles.Libelle_Role WHERE libelle = 'vet' AND Archive = 0";
	private String rqtVerifieRdv = "SELECT * FROM Agendas WHERE CodeVeto = ? AND DateRdv > getDate()";
	private String rqtAjoutPersonnelsRole = "INSERT INTO Personnels_Roles VALUES (?,?)";
	private String rqtSelectRolePersonnels = "SELECT Libelle_Role FROM Personnels LEFT JOIN Personnels_Roles ON Personnels_Roles.CodePers = Personnels.CodePers LEFT JOIN Roles ON Personnels_Roles.Libelle_Role = Roles.Libelle WHERE Personnels.CodePers = ?";
	
	public PersonnelsDAOJdbcImpl()
	{
		
	}
	
	/* Créee par Yael LEBARON */
	public Personnels checkConnexion(String login,String mdp) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnels personnel = null;

			try {
				cnx = JdbcTools.getConnection();
			
				rqt = cnx.prepareStatement(rqtCheckConnec);
				rqt.setString(1, login);
				rqt.setString(2, mdp);
				
				rs = rqt.executeQuery();
				if (rs.next()) {
					personnel = new Personnels(
							rs.getInt("CodePers"),
							rs.getString("Nom"),
							rs.getString("Prenom"),
							rs.getString("Login"),
							rs.getString("MotPasse"),
							selectRolePersonnels(rs.getInt("CodePers")),
							rs.getBoolean("Archive")
					);
				}
			} catch (SQLException e) {
				throw new DALException("CheckConnexion failed ", e);
			} finally {
				try {
					if (rs != null){
						rs.close();
					}
					if (rqt != null){
						rqt.close();
					}
					if(cnx!=null){
						cnx.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return personnel;
	}

	/* Créee par Maxime GAUTIER */
	@Override
	public Personnels selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnels personnel = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectById);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			if (rs.next()){
				personnel = new Personnels(
						rs.getInt("CodePers"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Login"),
						rs.getString("MotPasse"),
						selectRolePersonnels(rs.getInt("CodePers")),
						rs.getBoolean("Archive")		
				);
			}
		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + id , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personnel;
	}

	/* Créee par Maxime GAUTIER */
	@Override
	public Personnels selectByName(String nomPersonnel) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnels personnel = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectByName);
			rqt.setString(1, nomPersonnel);
			rs = rqt.executeQuery();
			if (rs.next()){
				personnel = new Personnels(
						rs.getInt("CodePers"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Login"),
						rs.getString("MotPasse"),
						selectRolePersonnels(rs.getInt("CodePers")),
						rs.getBoolean("Archive")		
				);
			}
		} catch (SQLException e) {
			throw new DALException("selectByName failed - id = " + nomPersonnel , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personnel;
	}

	/* Créee par Maxime GAUTIER */
	@Override
	public List<Personnels> selectAll() throws DALException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Personnels> list = new ArrayList<Personnels>();

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(rqtSelectAll);;
			Personnels personnel = null;
			
			while (rs.next()){
				personnel = new Personnels(
						rs.getInt("CodePers"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Login"),
						rs.getString("MotPasse"),
						selectRolePersonnels(rs.getInt("CodePers")),
						rs.getBoolean("Archive")		
				);
				list.add(personnel);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/* Créee par Maxime GAUTIER */
	@Override
	public int insert(Personnels personnel) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, personnel.getNom());
			rqt.setString(2, personnel.getPrenom());
			rqt.setString(3, personnel.getLogin());
			rqt.setString(4, personnel.getMotPasse());
			rqt.setBoolean(5, personnel.getArchive());
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					personnel.setCodePers(rs.getInt(1));
				}
				insertPersonnelsRoles(rs.getInt(1), personnel.getListRole().get(0));
			}
			return personnel.getCodePers();
		} catch (SQLException e) {
			throw new DALException("Insert personnel failed - " + personnel, e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
		}
	}
	
	@Override
	public void insertPersonnelsRoles(int codePers, String role) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtInsertPersonnelsRoles);
			rqt.setString(1, role);
			rqt.setInt(2, codePers);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Insert PersonnelsRoles failed", e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
		}
	}

	/* Créee par Maxime GAUTIER */
	@Override
	public void update(Personnels personnel) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtUpdate);
			rqt.setString(1, personnel.getNom());
			rqt.setString(2, personnel.getPrenom());
			rqt.setString(3, personnel.getLogin());
			rqt.setString(4, personnel.getMotPasse());
			rqt.setBoolean(5, personnel.getArchive());
			rqt.setInt(6, personnel.getCodePers());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Update article failed - " + personnel, e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
		}
	}

	/* Créee par Maxime GAUTIER */
	@Override
	public void delete(Personnels personnel) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtDelete);
			rqt.setInt(1, personnel.getCodePers());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - " + personnel, e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
		}	
	}
	
	/* Créee par Maxime GAUTIER */
	public List<String> selectRole() throws DALException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(rqtSelectRole);
			
			while (rs.next()){
				list.add(rs.getString("Libelle"));
			}
		} catch (SQLException e) {
			throw new DALException("selectRole failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/* Créé par Erwin DUPUIS */
	public boolean verifieSiExiste(String nomPersonnel) throws DALException
	{
		Connection cnx = null;
		boolean aRetourner = false;
		PreparedStatement psVerifieSiExiste = null;
		ResultSet rsVerifieSiExiste = null;
		
		try 
		{
			cnx = JdbcTools.getConnection();		
			psVerifieSiExiste = cnx.prepareStatement(rqtVerifieSiExiste);
			psVerifieSiExiste.setString(1, nomPersonnel);		
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
			}
			catch(SQLException e)
			{
				throw new DALException(e.getMessage());
			}			
		}
		
		return aRetourner;
	}
	
	/* Créee par Maxime GAUTIER */
	public List<Personnels> selectVeterinaire() throws DALException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Personnels> personnels = new ArrayList<Personnels>();

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(rqtSelectVeterinaire);
			Personnels personnel = null;
			
			while (rs.next()){
				personnel = new Personnels(
						rs.getInt("CodePers"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Login"),
						rs.getString("MotPasse"),
						selectRolePersonnels(rs.getInt("CodePers")),
						rs.getBoolean("Archive")		
				);
				personnels.add(personnel);
			}
		} catch (SQLException e) {
			throw new DALException("selectVeterinaire failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personnels;
	}
	
	/* Créee par Maxime GAUTIER */
	public boolean selectRdvVeterinaire(int id) throws DALException
	{
		boolean aRetourner = false;
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		
		try 
		{
			cnx = JdbcTools.getConnection();		
			rqt = cnx.prepareStatement(rqtVerifieRdv);
			rqt.setInt(1, id);		
			rs = rqt.executeQuery();
			
			if(rs.next())
			{
				aRetourner = true;
			}			
		} catch (SQLException e) {
			throw new DALException("selectRdvVeterinaire failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aRetourner;
	}
	
	/* Créee par Maxime GAUTIER */
	@Override
	public boolean ajouterPersonnelsRole(int id, String role) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		boolean aRetourner = false;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtAjoutPersonnelsRole);
			rqt.setString(1, role);
			rqt.setInt(2, id);
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				aRetourner = true;
			}
			return aRetourner;
		} catch (SQLException e) {
			throw new DALException("AjouterPersonnelsRole failed - ", e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
		}
	}
	

	public List<String> selectRolePersonnels(int id) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<String> listRole = new ArrayList<String>();

		try {
			cnx = JdbcTools.getConnection();
		
			rqt = cnx.prepareStatement(rqtSelectRolePersonnels);
			rqt.setInt(1, id);

			
			rs = rqt.executeQuery();
			while (rs.next()){
				listRole.add(rs.getString("Libelle_Role"));
			}
		} catch (SQLException e) {
			throw new DALException("CheckConnexion failed ", e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listRole;
	}
	
}
