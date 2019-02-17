package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorBuilder {

	public static Connection getConnector() throws Exception {
		try {
			Connection conn = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Absence?useSSL=false","teddie","teddie");
			System.out.println("Connected to teddies DB.");	

			return conn;
			
		} catch (SQLException sqlex) {
			System.out.println("Failed to connect to teddies DB, trying with fernandos DB.");

			try {
				Connection conn = null;
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Absence?useSSL=false","admin","absence-admin");
				System.out.println("Connected to fernandos DB.");	
				
				return conn;

			} catch (Exception ex) {
				System.out.println("Failed to connect to fernandos DB.");
				throw ex;
			}
		}
	}
}
