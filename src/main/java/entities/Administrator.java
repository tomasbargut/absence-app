package entities;

public class Administrator {
	private String accessLevel;

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Administrator(String accessLevel) {
		super();
		this.accessLevel = accessLevel;
	}

}
