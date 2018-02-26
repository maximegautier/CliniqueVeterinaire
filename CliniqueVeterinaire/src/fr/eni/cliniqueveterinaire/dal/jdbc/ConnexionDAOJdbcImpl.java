package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.cliniqueveterinaire.dal.DALException;

public class ConnexionDAOJdbcImpl {
	private Connection connection;
	private String rqtCheckConnec = "SELECT Role,Archive FROM Personnels WHERE Nom = ? AND MotPasse = ?";
	
	public ConnexionDAOJdbcImpl(){
		
	}
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			String urldb = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Clinique_veto";
			connection = DriverManager.getConnection(urldb, "sa", "Pa$$w0rd");
		}

		return connection;
	}
	
	public void closeConnection(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection=null;
		}
	}
	
	public boolean checkConnexion(Personnels personnel){
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean bRes = false;
		
		try{
			cnx = getConnection();
			rqt = cnx.prepareStatement(rqtCheckConnec);
			rqt.setString(1, personnel.getNom());
			rqt.setString(2, personnel.getMotPasse());
			
			rs = rqt.executeQuery();
			if (rs.next()) {
				bRes = true;
			}
		}catch(SQLException e){
			throw new DALException("checkConnexion failed ", e);
		}finally{
			try {
				
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		
		return bRes;
	}
}
