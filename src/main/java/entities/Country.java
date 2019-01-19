package entities;

public class Country {
	private int countryID;
	private String ctryName;
	private String lang;
	public int getCountryID() {
		return countryID;
	}
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	public String getName() {
		return ctryName;
	}
	public void setName(String name) {
		this.ctryName = name;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Country(int countryID, String ctryName, String lang) {
		super();
		this.countryID = countryID;
		this.ctryName = ctryName;
		this.lang = lang;
	}
	
	
	

}
