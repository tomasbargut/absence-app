package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class Provider extends User{
	private String fullname;
	private String telephone;
	private String postalCode;
	private String address;
	private String birthDate;
	private int prestige;
	private String providerType;
	private ArrayList<Service> services;

	public Provider(ResultSet rs, ArrayList<Service> services) throws SQLException {
		super(rs);
		this.prestige = rs.getInt("prestigeID");
		this.providerType = rs.getString("providerType");
		this.fullname = rs.getString("fullName");
		this.telephone = rs.getString("telephone");
		this.postalCode = rs.getString("postalCode");
		this.address = rs.getString("address");
		this.birthDate = rs.getString("birthDate");
		this.services = services;
	}

	public Provider(HttpServletRequest request) {
		
		this.fullname = request.getParameter("fullname");
		this.telephone = request.getParameter("telephone");
		this.postalCode = request.getParameter("postalCode");
		this.address = request.getParameter("street");
		this.birthDate = request.getParameter("birthDate");
	}

	/**
	 * @param prestige the prestige to set
	 */
	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	/**
	 * @return the providerType
	 */
	public String getProviderType() {
		return providerType;
	}

	/**
	 * @param providerType the providerType to set
	 */
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	/**
	 * @return the prestige
	 */
	public int getPrestige() {
		return prestige;
	}
	
	public String getName() {
		return fullname;
	}
	public void setName(String name) {
		this.fullname = name;
	}
	public String getPhone() {
		return telephone;
	}
	public void setPhone(String phone) {
		this.telephone = phone;
	}
	public String getPostal_code() {
		return postalCode;
	}
	public void setPostal_code(String postal_code) {
		this.postalCode = postal_code;
	}
	public String getStreet() {
		return address;
	}
	public void setStreet(String street) {
		this.address = street;
	}
	public String getBirthdate() {
		return birthDate;
	}
	public void setBirthdate(String birthdate) {
		this.birthDate = birthdate;
	}


	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the services
	 */
	public ArrayList<Service> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}
}
