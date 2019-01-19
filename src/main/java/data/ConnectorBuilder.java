package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectorBuilder {
	
	public static Connection getConnector() throws Exception{
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/absence?useSSL=false","absence","absence");
		
		return conn;
	}
}
