package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorBuilder {

	public static Connection getConnector() throws Exception {
		try {
			Connection conn = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/absence?useSSL=false","teddie","teddie");
		
			return conn;
			
		} catch (SQLException sqlex) {
			System.out.println("Failed to connect to teddies DB, trying with fernandos DB.");
			try {
				Connection conn = null;
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/absence?useSSL=false","admin","absence-admin");

				return conn;

			} catch (Exception ex) {
				throw ex;
			}
		}
	}
}
