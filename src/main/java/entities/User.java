package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class User {
	private int userID;
	private String username;
	private String password;
	private String email;
	private String lastLogin;
	private String accountStatus;
	private String salt;
	
	public User() {};
	
	public User(ResultSet rs) throws SQLException {
		this.setUserID(rs.getInt("userID"));
		this.setUsername(rs.getString("username"));
		this.setLastLogin(rs.getString("lastLogin"));
		this.setAccountStatus(rs.getString("accountStatus"));
		this.setEmail(rs.getString("email"));
		this.setPassword(rs.getString("password"));
	}

	public User(int userID, String username, String password, String email, Profile profile) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public User(int userID, String username, String password, String email) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public User(HttpServletRequest request) {
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
	private void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	private void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public String getSalt() {
		return salt;
	}
}
