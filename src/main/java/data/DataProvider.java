package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;

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
		try (Connection conn = ConnectorBuilder.getConnector()){
			
			PreparedStatement stmt = conn.prepareStatement(
				"SELECT * FROM providers AS p INNER JOIN users AS u ON p.userID=u.userID  WHERE u.userID = ?"
			);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				ArrayList<Service> services = new ArrayList<Service>();
				PreparedStatement stmtServices = conn.prepareStatement("select  serviceID from provisions where userID = ?");
				stmtServices.setInt(1, userID);
				ResultSet rsServices = stmtServices.executeQuery();
				while (rsServices.next()) {
					services.add(dataService.get(rsServices.getInt("serviceID")));
				}
				provider = new Provider(rs, services);
			}
		} catch (Exception e) {
			// TODO Implementar logger
			System.out.print(e.getMessage());
		}
		return provider;
	}

	public boolean save(Provider provider) {
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
			stmt.execute();
			if (!provider.getServices().isEmpty()) {
				PreparedStatement stmtService = conn.prepareStatement("INSERT INTO provisions VALUES(?,?)");
				for (Service service : provider.getServices()) {
					stmtService.setInt(1, provider.getUserID());
					stmtService.setInt(2, service.getServiceID());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
}
