package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Provider;

public class DataProvider {
	public Provider get_provider_by_id(int id) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();
		PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM providers WHERE user_id = ?"
				);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Provider provider = null;
		if(rs != null && rs.next()) {
			provider = new Provider(rs);
		}
		conn.close();
		return provider;
	}
	
	public Provider save_provider(Provider provider) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();
		
		PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO providers VALUES(?,?,?,?,?,?)");
		stmt.setInt(1, provider.getUser_id());
		stmt.setString(2, provider.getName());
		stmt.setString(3, provider.getPhone());
		stmt.setString(4, provider.getPostal_code());
		stmt.setString(5, provider.getStreet());
		stmt.setString(6, provider.getBirthdate());
		stmt.execute();
		conn.close();
		return provider;
	}
}
