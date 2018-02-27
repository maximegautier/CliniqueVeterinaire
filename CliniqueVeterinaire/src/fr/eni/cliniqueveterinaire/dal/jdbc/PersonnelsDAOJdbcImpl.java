package fr.eni.cliniqueveterinaire.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.cliniqueveterinaire.bo.Clients;
import fr.eni.cliniqueveterinaire.bo.Personnels;
import fr.eni.cliniqueveterinaire.dal.DALException;
import fr.eni.cliniqueveterinaire.dal.PersonnelsDAO;

public class PersonnelsDAOJdbcImpl implements PersonnelsDAO
{
	private String rqtCheckConnec = "SELECT Nom,MotPasse,Role,Archive FROM Personnels WHERE Nom = ? AND MotPasse = ? AND Archive = 0";
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
}
