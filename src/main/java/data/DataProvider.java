package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Provider;
import entities.Service;

public class DataProvider {

	public DataProvider() {
	}

	public Provider get(int userID) throws SQLException{
		Provider provider = null;
		try (Connection conn = ConnectorBuilder.getConnector()){
			
			PreparedStatement stmt = conn.prepareStatement(
				"SELECT * FROM providers AS p INNER JOIN users AS u ON p.userID=u.userID  WHERE u.userID = ?"
			);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				provider = new Provider(rs);
			}
		}
		return provider;
	}

	public void save(Provider provider) throws SQLException{
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO providers(`userID`,`fullName`,telephone,`postalCode`,address,`birthDate`,`prestigeID`) VALUES(?,?,?,?,?,?,?)"
			);
			stmt.setInt(1, provider.getUserID());
			stmt.setString(2, provider.getName());
			stmt.setString(3, provider.getPhone());
			stmt.setString(4, provider.getPostal_code());
			stmt.setString(5, provider.getStreet());
			stmt.setString(6, provider.getBirthdate());
			stmt.setInt(7, provider.getPrestige());
			stmt.executeUpdate();
		}
	}

	public void update(Provider provider) throws SQLException{
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement(
				"UPDATE providers set  `fullName`=? ,telephone=? , `postalCode`=? ,address=? ,`birthDate`=? ,`prestigeID`=? WHERE `userID`=?"
			);
			stmt.setString(1, provider.getName());
			stmt.setString(2, provider.getPhone());
			stmt.setString(3, provider.getPostal_code());
			stmt.setString(4, provider.getStreet());
			stmt.setString(5, provider.getBirthdate());
			stmt.setInt(6, provider.getPrestige());
			stmt.setInt(7, provider.getUserID());
			stmt.executeUpdate();
		}
	}
}
