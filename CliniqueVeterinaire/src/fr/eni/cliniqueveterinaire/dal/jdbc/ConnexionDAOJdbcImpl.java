package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;

public class ConnexionDAOJdbcImpl {
	private Connection connection;
	private String rqtCheckConnec = "SELECT Role,Archive FROM Personnels WHERE Nom = ? AND MotPasse = ?";
	
	public ConnexionDAOJdbcImpl(){
		
	}
	
	public boolean checkConnexion(String nom,String mdp) throws DALException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean bRes = false;
		
		try{
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(rqtCheckConnec);
			rqt.setString(1, nom);
			rqt.setString(2, mdp);
			
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
				throw new DALException("Close requête failed",e);
			}
			try {
				cnx.close();
			} catch (SQLException e) {
				throw new DALException("Close connexion failed",e);
			}
		}
		
		return bRes;
	}
}
