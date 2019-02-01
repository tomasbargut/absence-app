package data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;

public class DataUser {

	public void save(User user) {
		try {
			Connection conn = ConnectorBuilder.getConnector();

			PreparedStatement stmtUser = conn.prepareStatement(
					"INSERT INTO users (username, password, email, lastlogin, accountStatus, salt) VALUES(?,?,?,?,?, ?)");
			stmtUser.setString(1, user.getUsername());
			stmtUser.setString(2, user.getPassword());
			stmtUser.setString(3, user.getEmail());
			stmtUser.setString(4, user.getLastLogin());
			stmtUser.setString(5, user.getAccountStatus());
			stmtUser.setString(6, user.getSalt());
			stmtUser.executeUpdate();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User get(int userID) {
		User user = null;
		try {
			Connection conn = ConnectorBuilder.getConnector();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE userID = ?");
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			user = null;
			if (rs != null) {
				user = new User(rs);
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return user;
	}

	public User getUserByUsername(String username) {
		User user = null;
		try {
			Connection conn = ConnectorBuilder.getConnector();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			user = null;
			if (rs != null && rs.next()) {
				user = new User(rs);
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	public User getUserByEmail(String email) {
		User user = null;
		try {
			Connection conn = ConnectorBuilder.getConnector();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			user = null;
			if (rs != null && rs.next()) {
				user = new User(rs);
			}
			conn.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
}
