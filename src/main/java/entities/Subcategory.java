package entities;

public class Subcategory extends Category {
	private int subcategoryID;
	private String desc;
	private String name;
	public int getSubcategoryID() {
		return subcategoryID;
	}
	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subcategory(int categoryID, String desc, String name, int subcategoryID, String desc2, String name2) {
		super(categoryID, desc, name);
		this.subcategoryID = subcategoryID;
		desc = desc2;
		name = name2;
	}
	
}
