package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class Service {
    private int serviceID;
    private String title;
    private String desc;
    private ArrayList<Category> categories;

    public Service(ResultSet rs) throws SQLException {
        this.serviceID = rs.getInt("serviceID");
        this.title = rs.getString("title");
        this.desc = rs.getString("desc");
        this.categories = new ArrayList<Category>();
    }
    
    public Service(ResultSet rs, ArrayList<Category> categories) throws SQLException {
        this.serviceID = rs.getInt("serviceID");
        this.title = rs.getString("title");
        this.desc = rs.getString("desc");
        this.categories=categories;
    }

    public Service(HttpServletRequest request) {
        this.title = request.getParameter("title");
        this.desc = request.getParameter("desc");
        this.categories = new ArrayList<Category>();
	}

	/**
     * @return the serviceID
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the categories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }


}
