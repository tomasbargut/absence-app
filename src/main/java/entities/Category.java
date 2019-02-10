package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Category {
    private int categoryID;
    private String desc;
    private String name;

    public Category(ResultSet rs) throws SQLException {
        this.categoryID = rs.getInt("categoryID");
        this.name = rs.getString("name");
        this.desc = rs.getString("desc");
    }
    public Category(HttpServletRequest request) {
        this.name = request.getParameter("name");
        this.desc = request.getParameter("description");
	}
	/**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
}
