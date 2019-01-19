package entities;

public class Province extends Country {
	private int provinceID;
	private String provName;
	public int getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}
	public String getName() {
		return provName;
	}
	public void setName(String name) {
		this.provName = name;
	}
	public Province(int countryID, String ctryName, String lang, int provinceID, String provName) {
		super(countryID, ctryName, lang);
		this.provinceID = provinceID;
		this.provName = provName;
	}
	
	
	
}
