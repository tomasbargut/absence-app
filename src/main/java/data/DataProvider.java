package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entities.Provider;
import entities.Service;
import entities.User;
import data.DataService;

public class DataProvider {
	private DataService dataService;

	public DataProvider() {
		dataService = new DataService();
	}

	public Provider get(int userID) {
		Provider provider = null;
		try {
			Connection conn = ConnectorBuilder.getConnector();
			PreparedStatement stmt = conn.prepareStatement(
				"SELECT * FROM providers AS p INNER JOIN users AS u ON p.userID=u.userID  WHERE userID = ?"
			);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				ArrayList<Service> services = new ArrayList<Service>();
				PreparedStatement stmtServices = conn.prepareStatement("select  serviceID provisions where userID = ?");
				stmtServices.setInt(1, userID);
				ResultSet rsServices = stmtServices.executeQuery();
				while (rs.next()) {
					services.add(dataService.get(rsServices.getInt("serviceID")));
				}
				provider = new Provider(rs, services);
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provider;
	}

	public void save(Provider provider) {
		try {
			Connection conn = ConnectorBuilder.getConnector();

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO providers VALUES(?,?,?,?,?,?)");
			stmt.setInt(1, provider.getUserID());
			stmt.setString(2, provider.getName());
			stmt.setString(3, provider.getPhone());
			stmt.setString(4, provider.getPostal_code());
			stmt.setString(5, provider.getStreet());
			stmt.setString(6, provider.getBirthdate());
			stmt.execute();
			conn.close();

			if (!provider.getServices().isEmpty()) {
				PreparedStatement stmtService = conn.prepareStatement("INSERT INTO provisions VALUES(?,?)");
				for (Service service : provider.getServices()) {
					stmtService.setInt(1, provider.getUserID());
					stmtService.setInt(2, service.getServiceID());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
