package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends User{
	private String accessLevel;

	public Administrator(ResultSet rs) throws SQLException{
		super(rs);
		this.accessLevel = rs.getString("accessLevel");
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
}
