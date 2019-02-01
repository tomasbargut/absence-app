package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    private int serviceID;
    private String title;
    private String desc;
    private Category[] categories;

    public Service(ResultSet rs) throws SQLException {
        this.serviceID = rs.getInt("serviceID");
        this.title = rs.getString("title");
        this.desc = rs.getString("desc");
    }
    
    public Service(ResultSet rs, Category[] categories) throws SQLException {
        this.serviceID = rs.getInt("serviceID");
        this.title = rs.getString("title");
        this.desc = rs.getString("desc");
        this.categories=categories;
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
    public Category[] getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(Category[] categories) {
        this.categories = categories;
    }


}
