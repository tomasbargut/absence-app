package entities;

public class City extends Province {
	private int postalCode;
	private String ctyName;
	
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getName() {
		return ctyName;
	}
	public void setName(String name) {
		this.ctyName = name;
	}
	public City(int countryID, String ctryName, String lang, int provinceID, String provName, int postalCode,
			String ctyName) {
		super(countryID, ctryName, lang, provinceID, provName);
		this.postalCode = postalCode;
		this.ctyName = ctyName;
	}
	
	
	

	

	
	
}
