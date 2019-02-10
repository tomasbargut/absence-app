package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.User;

public class DataUser {

	public boolean save(User user) {
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmtUser = conn.prepareStatement(
				"INSERT INTO users (username, password, email) VALUES(?,?,?)",
				Statement.RETURN_GENERATED_KEYS
			);
			stmtUser.setString(1, user.getUsername());
			stmtUser.setString(2, user.getPassword());
			stmtUser.setString(3, user.getEmail());
			stmtUser.executeUpdate();
			ResultSet generatedKeys = stmtUser.getGeneratedKeys();
			generatedKeys.next();
			user.setUserID(generatedKeys.getInt(1));
		} catch (Exception e) {
			// TODO implementar logger
			return false;
		}
		return true;
	}

	public User get(int userID) {
		User user = null;
		try (Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE userID = ?");
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			user = null;
			if (rs != null) {
				user = new User(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return user;
	}

	public User getByUsername(String username) {
		User user = null;
		try (Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			user = null;
			if (rs != null && rs.next()) {
				user = new User(rs);
			}
		} catch (Exception e) {
			// TODO Implementar logger
		}
		return user;
	}

	public User getByEmail(String email) {
		User user = null;
		try (Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			user = null;
			if (rs != null && rs.next()) {
				user = new User(rs);
			}
		}catch (Exception e) {
			// TODO implementar logger
		}
		return user;
	}
}
