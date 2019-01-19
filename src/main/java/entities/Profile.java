package entities;

import java.time.LocalDateTime;

public class Profile {
	private String firstName;
	private String lastName;
	private String mainTel;
	private String altTel;
	private int postalCode;
	private String street;
	private int streetNumber;
	private boolean streetNumberIsBis;
	private String gender;
	private LocalDateTime lastLoginTime;
	private int account_status;
	private LocalDateTime birthDate;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMainTel() {
		return mainTel;
	}
	public void setMainTel(String mainTel) {
		this.mainTel = mainTel;
	}
	public String getAltTel() {
		return altTel;
	}
	public void setAltTel(String altTel) {
		this.altTel = altTel;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public boolean getStreetNumberIsBis() {
		return streetNumberIsBis;
	}
	public void setStreetNumberIsBis(boolean streetNumberIsBis) {
		this.streetNumberIsBis = streetNumberIsBis;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getAccount_status() {
		return account_status;
	}
	public void setAccount_status(int account_status) {
		this.account_status = account_status;
	}
	public LocalDateTime getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	public Profile(String firstName, String lastName, String mainTel, String altTel, int postalCode, String street,
			int streetNumber, boolean streetNumberIsBis, String gender, LocalDateTime lastLoginTime, int account_status,
			LocalDateTime birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mainTel = mainTel;
		this.altTel = altTel;
		this.postalCode = postalCode;
		this.street = street;
		this.streetNumber = streetNumber;
		this.streetNumberIsBis = streetNumberIsBis;
		this.gender = gender;
		this.lastLoginTime = lastLoginTime;
		this.account_status = account_status;
		this.birthDate = birthDate;
	}
	
	public Profile() {}

	
	

}
