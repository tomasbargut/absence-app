package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int userID;
	private String username;
	private String password;
	private String email;
	private Profile profile;
	

	public User() {};
	
	public User(ResultSet rs) throws SQLException {
		this.setUserID(rs.getInt("userID"));
		this.setUsername(rs.getString("username"));
		this.setEmail(rs.getString("email"));
		this.setPassword(rs.getString("password"));
	}
	
	public User(int userID, String username, String password, String email, Profile profile) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
	}
	
	public User(int userID, String username, String password, String email) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public int getUserID() {
		return userID;

	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
}
