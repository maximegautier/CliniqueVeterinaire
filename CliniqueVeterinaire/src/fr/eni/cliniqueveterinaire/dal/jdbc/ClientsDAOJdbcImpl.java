package fr.eni.cliniqueveterinaire.dal.jdbc;
import fr.eni.cliniqueveterinaire.bo.*;
import fr.eni.cliniqueveterinaire.dal.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAOJdbcImpl implements ClientsDAO {

	private Connection connection;
	
	private String rqtSelectById = "SELECT CodeClient,NomClient,PrenomClient,Adresse1,Adresse2,"
						+ "CodePostal,Ville,NumTel,Assurance,Email,Remarque,Archive FROM Clients " 
						+ "WHERE CodeClient = ?";
	private String rqtSelectAll = "SELECT CodeClient,NomClient,PrenomClient,Adresse1,Adresse2,"
						+ "CodePostal,Ville,NumTel,Assurance,Email,Remarque,Archive FROM Clients";
	private String rqtSelectByName = "SELECT CodeClient,NomClient,PrenomClient,Adresse1,Adresse2,"
						+ "CodePostal,Ville,NumTel,Assurance,Email,Remarque,Archive FROM Clients "
						+ "WHERE NomClient = ?";
	private String rqtAddClient = "INSERT INTO Clients (NomClient,PrenomClient,Adresse1,"
						+ "Adresse2,CodePostal,Ville,NumTel,Assurance,Email,Remarque,Archive)"
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String rqtEditClient = "UPDATE Clients SET NomClient = ?,PrenomClient = ?,"
						+ "Adresse1 = ?,Adresse2 = ?,CodePostal = ?,Ville = ?,NumTel = ?,Assurance = ?,"
						+ "Email = ?,Remarque = ?,Archive = ? WHERE CodeClient = ?";
	private String rqtDeleteClient = "DELETE FROM Clients WHERE CodeClient = ?";
	private String rqtSelectFirst = "SELECT TOP(1) CodeClient,NomClient,PrenomClient,Adresse1,Adresse2,"
						+ "CodePostal,Ville,NumTel,Assurance,Email,Remarque,Archive FROM Clients ";
	

	
	public ClientsDAOJdbcImpl(){
		
	}

	@Override
	public Clients selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Clients leClient = null;
		
		try{
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectById);
			rqt.setInt(1, id);
			
			rs = rqt.executeQuery();
			if (rs.next()) {
				leClient = new Clients(id, rs.getString("NomClient"),rs.getString("PrenomClient"),
						rs.getString("Adresse1"),rs.getString("Adresse2"),rs.getString("CodePostal"),
						rs.getString("Ville"),rs.getString("NumTel"),rs.getString("Assurance"),
						rs.getString("Email"),rs.getString("Remarque"),rs.getBoolean("Archive"));
			}
		}catch(SQLException e){
			throw new DALException("selectById failed - Id = " + id, e);
		}finally{
			try {
				
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Connexion close failed = ", e);
			}
		}
		
		return leClient;
	}
	
	public Clients selectFirst() throws DALException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Clients leClient = null;
		
		try{
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectFirst);
			
			rs = rqt.executeQuery();
			if (rs.next()) {
				leClient = new Clients(rs.getInt("CodeClient"),rs.getString("NomClient"),rs.getString("PrenomClient"),
						rs.getString("Adresse1"),rs.getString("Adresse2"),rs.getString("CodePostal"),
						rs.getString("Ville"),rs.getString("NumTel"),rs.getString("Assurance"),
						rs.getString("Email"),rs.getString("Remarque"),rs.getBoolean("Archive"));
			}
		}catch(SQLException e){
			throw new DALException("selectFirst failed ", e);
		}finally{
			try {
				
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Connexion close failed = ", e);
			}
		}
		
		return leClient;
	}

	@Override
	public Clients selectByName(String nomClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Clients leClient = null;
		
		try{
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectByName);
			rqt.setString(1, "'"+nomClient+"'");
			
			rs = rqt.executeQuery();
			if(rs.next()){
				leClient = new Clients(rs.getInt("CodeClient"),rs.getString("NomClient"),rs.getString("PrenomClient"),
						rs.getString("Adresse1"),rs.getString("Adresse2"),rs.getString("CodePostal"),
						rs.getString("Ville"),rs.getString("NumTel"),rs.getString("Assurance"),
						rs.getString("Email"),rs.getString("Remarque"),rs.getBoolean("Archive"));
			}
		}catch(SQLException e){
			throw new DALException("selectByName failed - Name = " + nomClient, e);
		}finally{
			try {
				
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Connexion close failed = ", e);
			}
		}
			
		return leClient;
	}

	@Override
	public List<Clients> selectAll() throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Clients> lesClient = new ArrayList<Clients>();
		
		try{
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtSelectAll);
			
			rs = rqt.executeQuery();
			int i =0;
			while(rs.next()){
				lesClient.add(new Clients(rs.getInt("CodeClient"),rs.getString("NomClient"),rs.getString("PrenomClient"),
						rs.getString("Adresse1"),rs.getString("Adresse2"),rs.getString("CodePostal"),
						rs.getString("Ville"),rs.getString("NumTel"),rs.getString("Assurance"),
						rs.getString("Email"),rs.getString("Remarque"),rs.getBoolean("Archive")));
				i++;
			}
		}catch(SQLException e){
			throw new DALException("selectAll failed ", e);
		}finally{
			try {
				
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Connexion close failed = ", e);
			}
		}
			
		return lesClient;
	}

	@Override
	public void addClient(Clients leClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx .prepareStatement(rqtAddClient , Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, leClient.getNomClient());
			rqt.setString(2, leClient.getPrenomClient());
			rqt.setString(3, leClient.getAdresse1());
			rqt.setString(4, leClient.getAdresse2());
			rqt.setString(5, leClient.getCodePostal());
			rqt.setString(6, leClient.getVille());
			rqt.setString(7,leClient.getNumTel());
			rqt.setString(8,leClient.getAssurance());
			rqt.setString(9,leClient.getEmail());
			rqt.setString(10,leClient.getNumTel());
			rqt.setString(11,leClient.getRemarque());
			if(leClient.isArchive()){
				rqt.setBoolean(11,true);
			}else{
				rqt.setBoolean(11,false);
			}
						
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					leClient.setCodeClient(nbRows);
				}
			}
		} catch (SQLException e) {
			throw new DALException("AddClient failed - ", e);
		}finally {
			try {
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Close connexion failed",e);
			}
		}
		
	}

	@Override
	public void editClient(Clients leClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx .prepareStatement(rqtEditClient , Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, leClient.getNomClient());
			rqt.setString(2, leClient.getPrenomClient());
			rqt.setString(3, leClient.getAdresse1());
			rqt.setString(4, leClient.getAdresse2());
			rqt.setString(5, leClient.getCodePostal());
			rqt.setString(6, leClient.getVille());
			rqt.setString(7,leClient.getNumTel());
			rqt.setString(8,leClient.getAssurance());
			rqt.setString(9,leClient.getEmail());
			rqt.setString(10,leClient.getRemarque());
			if(leClient.isArchive()){
				rqt.setBoolean(11,true);
			}else{
				rqt.setBoolean(11,false);
			}
			rqt.setInt(12,leClient.getCodeClient());
						
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("EditClient failed - " +leClient.getCodeClient(), e);
		}finally {
			try {
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Close connexion failed",e);
			}
		}
	}

	@Override
	public void deleteClient(int codeClient) throws DALException {
		PreparedStatement rqt = null;
		Connection cnx = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtDeleteClient);
			rqt.setInt(1, codeClient);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete client failed - codeClient =" + codeClient, e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Close connexion failed",e);
			}
		}
	}
	
	

}

