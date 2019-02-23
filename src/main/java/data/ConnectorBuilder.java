package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorBuilder {

	public static Connection getConnector() throws SQLException{
		try {
			Connection conn = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Absence?useSSL=false", "teddie", "teddie");
			System.out.println("Connected to teddies DB.");

			return conn;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
}
