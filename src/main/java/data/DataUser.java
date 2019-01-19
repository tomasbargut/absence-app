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
					"INSERT INTO users (username, password, email) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS
				);
		stmtUser.setString(1, user.getUsername());
		stmtUser.setString(2, user.getPassword());
		stmtUser.setString(3, user.getEmail());
		stmtUser.executeUpdate();
		ResultSet rs = stmtUser.getGeneratedKeys();
		rs.next();
		user.setUserID(rs.getInt(1));
		conn.close();
		return user;
	}
	/*void adc() {
		PreparedStatement stmtProfile = conn.prepareStatement(
				"INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
			);
		Profile profile = user.getProfile();
		stmtProfile.setInt(1, nextID); 
		stmtProfile.setString(2, profile.getFirstName());
		stmtProfile.setString(3, profile.getLastName());
		stmtProfile.setString(4, profile.getMainTel());
		stmtProfile.setString(5, profile.getAltTel());
		stmtProfile.setInt(6, profile.getPostalCode());
		stmtProfile.setString(7, profile.getStreet());
		stmtProfile.setInt(8, profile.getStreetNumber());
		stmtProfile.setBoolean(9, profile.getStreetNumberIsBis());
		stmtProfile.setString(10, profile.getGender());
		stmtProfile.setObject(11, profile.getLastLoginTime());
		stmtProfile.setInt(12, profile.getAccount_status());
		stmtProfile.setObject(13, profile.getBirthDate());
	}*/

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

