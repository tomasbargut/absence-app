package entities;

public class Category {
	private int categoryID;
	private String desc;
	private String name;
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
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
	public Category(int categoryID, String desc, String name) {
		super();
		this.categoryID = categoryID;
		this.desc = desc;
		this.name = name;
	}

}
