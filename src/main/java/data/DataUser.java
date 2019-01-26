package data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import entities.User;
import entities.Profile;

public class DataUser {

	//Query to set a new user on DB
	public User newUser(User user) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();
				
		PreparedStatement stmtUser = conn.prepareStatement(
					"INSERT INTO users (userID,username, password, email) VALUES(?,?,?,?)"
				);
		stmtUser.setInt(1, user.getUserID());
		stmtUser.setString(2, user.getUsername());
		stmtUser.setString(3, user.getPassword());
		stmtUser.setString(4, user.getEmail());
		stmtUser.executeUpdate();
		conn.close();
		return user;
	}

	//Queries to DB returning ResultSets (only user)
	public User getUserByID(int userID) throws Exception{
		Connection conn = ConnectorBuilder.getConnector();
		PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM users WHERE userID = ?"
				);
		stmt.setInt(1, userID);
		ResultSet rs = stmt.executeQuery();
		User user = null;
		if(rs != null) {
			user = new User(rs);
		}
		conn.close();
		return user;

	}
	public User getUserByUsername(String username) throws Exception{
		Connection conn = ConnectorBuilder.getConnector();
		PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM users WHERE username = ?"
				);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		User user = null;
		if(rs != null && rs.next()) {
			user = new User(rs);
		}
		conn.close();
		return user;

	}
	public User getUserByEmail(String email) throws Exception{
		Connection conn = ConnectorBuilder.getConnector();
		PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM users WHERE email = ?"
				);
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		User user = null;
		if(rs != null && rs.next()) {
			user = new User(rs);
		}
		conn.close();
		return user;
	}
	
	//Query to DB returning ResultSet (only profile)
	public Profile getProfileByID(int userID) throws Exception{
		Connection conn = ConnectorBuilder.getConnector();
		PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM profiles WHERE userID = ?"
				);
		stmt.setInt(1, userID);
		ResultSet rs = stmt.executeQuery();
		conn.close();
		if(rs != null && rs.next()) {
			Profile profile = buildProfile(rs);
			return profile;
		}
		return null;

	}
	
	//Parses ResultSets to new instance of User entity and returns it
	
	
	//Parses ResultSets to new instance of User-Profile entity and returns it
	
	//Parses ResultSets to new instance of Profile and returns it
	private Profile buildProfile(ResultSet rs) throws SQLException {
		Profile profile = new Profile();
		profile.setFirstName(rs.getString("firstName"));
		profile.setLastName(rs.getString("lastname"));
		profile.setMainTel(rs.getString("mainTel"));
		profile.setAltTel(rs.getString("altTel"));
		profile.setStreet(rs.getString("street"));
		profile.setStreetNumber(rs.getInt("streetNumber"));
		profile.setStreetNumberIsBis(rs.getBoolean("steetNumberIsBis"));
		profile.setGender(rs.getString("gender"));
		profile.setLastLoginTime(rs.getObject("lastLoginTime", LocalDateTime.class));
		profile.setAccount_status(rs.getInt("account_status"));
		profile.setBirthDate(rs.getObject("birthDate", LocalDateTime.class));
		return profile;
		
	}
}

