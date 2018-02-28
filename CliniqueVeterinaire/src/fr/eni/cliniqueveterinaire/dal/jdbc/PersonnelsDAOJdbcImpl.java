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
	private String rqtCheckConnec = "SELECT Nom,MotPasse,Role,Archive FROM Personnels WHERE Nom = ? AND MotPasse = ? AND Archive = 0";
	private String rqtSelectById = "SELECT Nom,MotPasse,Role,Archive FROM Personnels WHERE CodePers = ? AND Archive = 0";
	private String rqtSelectByName = "SELECT Nom,MotPasse,Role,Archive FROM Personnels WHERE Nom = ? AND Archive = 0";
	private String rqtSelectAll = "SELECT Nom,MotPasse,Role,Archive FROM Personnels WHERE Archive = 0";
	private String rqtInsert = "INSERT INTO Personnels VALUES (?,?,?,?)";
	private String rqtDelete = "UPDATE Personnels SET Archive = 1 WHERE CodePers = ?";
	private String rqtUpdate = "UPDATE Personnels SET Nom=?, MotPasse=?, Role=?, Archive = ? WHERE CodePers = ?";
	private String rqtSelectRole = "SELECT DISTINCT Role From Personnels";
	
	public PersonnelsDAOJdbcImpl()
	{
		
	}
	
	public Personnels checkConnexion(String nom,String mdp) throws DALException
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnels personnel = null;

			try {
				cnx = JdbcTools.getConnection();
			
				rqt = cnx.prepareStatement(rqtCheckConnec);
				rqt.setString(1, nom);
				rqt.setString(2, mdp);
				
				rs = rqt.executeQuery();
				if (rs.next()) {
					personnel = new Personnels(
							rs.getString("Nom"),
							rs.getString("MotPasse"),
							rs.getString("Role"),
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
						rs.getString("Nom"),
						rs.getString("MotPasse"),
						rs.getString("Role"),
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

	@Override
	public Personnels selectByName(String nomClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnels personnel = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectByName);
			rqt.setString(1, nomClient);
			rs = rqt.executeQuery();
			if (rs.next()){
				personnel = new Personnels(
						rs.getString("Nom"),
						rs.getString("MotPasse"),
						rs.getString("Role"),
						rs.getBoolean("Archive")		
				);
			}
		} catch (SQLException e) {
			throw new DALException("selectByName failed - id = " + nomClient , e);
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

	@Override
	public List<Personnels> selectAll() throws DALException {
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
						rs.getString("Nom"),
						rs.getString("MotPasse"),
						rs.getString("Role"),
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

	@Override
	public int insert(Personnels personnel) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, personnel.getNom());
			rqt.setString(2, personnel.getMotPasse());
			rqt.setString(3, personnel.getRole());
			rqt.setBoolean(4, personnel.getArchive());
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					personnel.setCodePers(rs.getInt(1));
				}
			}
			return personnel.getCodePers();
		} catch (SQLException e) {
			throw new DALException("Insert article failed - " + personnel, e);
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
	public void update(Personnels personnel) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtUpdate);
			rqt.setString(1, personnel.getNom());
			rqt.setString(2, personnel.getMotPasse());
			rqt.setString(3, personnel.getRole());
			rqt.setBoolean(4, personnel.getArchive());
			rqt.setInt(5, personnel.getCodePers());
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
	
	public List<String> selectRole() throws DALException
	{
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();

		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(rqtSelectRole);;
			
			while (rs.next()){
				list.add(rs.getString("Role"));
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
	
}
