package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Provider {
	private int user_id;
	private String name;
	private String phone;
	private String postal_code;
	private String street;
	private String birthdate;
	private Boolean pendingNotifications;
	
	public Provider(ResultSet rs) throws SQLException {
		this.user_id = rs.getInt("user_id");
		this.name = rs.getString("name");
		this.phone = rs.getString("phone");
		this.postal_code = rs.getString("postal_code");
		this.street = rs.getString("street");
		this.birthdate = rs.getString("birth_date");
	}
	
	public Provider(HttpServletRequest request){
		this.user_id = ((User)request.getSession().getAttribute("user")).getUserID();
		this.name = request.getParameter("name");
		this.phone = request.getParameter("phone");
		this.postal_code = request.getParameter("postal_code");
		this.street = request.getParameter("street");
		this.birthdate = request.getParameter("birth_date");
	}
	
	public Provider(int user_id, String name, String phone, String postal_code, String street, String birthdate) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.phone = phone;
		this.postal_code = postal_code;
		this.street = street;
		this.birthdate = birthdate;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Boolean getPendingNotifications() {
		return pendingNotifications;
	}

	public void setPendingNotifications(Boolean pendingNotifications) {
		this.pendingNotifications = pendingNotifications;
	}
}
