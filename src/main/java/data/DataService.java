/**
 * 
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Category;
import entities.Provider;
import entities.Service;

/**
 * @author ferna
 *
 */
public class DataService {
    private final DataProvider dataProvider;
    
    public DataService() {
        dataProvider = new DataProvider();
    }

    public Service get(int serviceID) throws SQLException{
        Service service = null;
        try(Connection conn = ConnectorBuilder.getConnector()) {
            PreparedStatement stmtService = conn.prepareStatement(
                "select * from services where serviceID = ?"
            );
            stmtService.setInt(1, serviceID);
            ResultSet rs = stmtService.executeQuery();
            if(rs.next() && rs != null){
                service = new Service(rs);
                service.setProvider(dataProvider.get(rs.getInt("userID")));
                ArrayList<Category> categories = new ArrayList<Category>();
                PreparedStatement stmtCategories = conn.prepareStatement(
                    "select c.* from categories as c inner join services_categories as sc on c.categoryID=sc.categoryID where sc.serviceID= ?"
                );
                stmtCategories.setInt(1, service.getServiceID());
                ResultSet rsCategories = stmtCategories.executeQuery();
                while(rs.next()){
                    categories.add(new Category(rs));
                }
            }
        }
        return service;
    }

    public void save(Service service) throws SQLException{
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO services(`title`, `desc`, `userID`) values(?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, service.getTitle());
            stmt.setString(2, service.getDesc());
            stmt.setInt(3, service.getProvider().getUserID());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
			service.setServiceID(generatedKeys.getInt(1));
            PreparedStatement stmtCategory = conn.prepareStatement(
                "INSERT into services_categories(`serviceID`, `categoryID`) values(?,?)"
            );
            for(Category category: service.getCategories()){
                stmtCategory.setInt(1, service.getServiceID());
                stmtCategory.setInt(2, category.getCategoryID());
                stmtCategory.executeUpdate();
            }
        }
    }

    public void update(Service service) throws SQLException{
        try(Connection conn = ConnectorBuilder.getConnector()) {
            PreparedStatement stmtService = conn.prepareStatement(
                "UPDATE services SET `desc`= ?, `title`= ?, `userID` = ? WHERE `serviceID` = ?"
            );
            stmtService.setString(1, service.getDesc());
            stmtService.setString(2, service.getTitle());
            stmtService.setInt(3, service.getProvider().getUserID());
            stmtService.setInt(4, service.getServiceID());
            stmtService.executeUpdate();
            PreparedStatement stmtDeleteServiceCategory = conn.prepareStatement(
                "delete from services_categories where serviceID = ?"
            );
            stmtDeleteServiceCategory.setInt(1, service.getServiceID());
            stmtDeleteServiceCategory.executeUpdate();
            PreparedStatement stmtServiceCategoryInsert = conn.prepareStatement(
                "insert into services_categories(serviceID, categoryID) values(?,?)"
            );
            // TODO: HACER BULK_INSERT
            for (Category category : service.getCategories()) {
                stmtServiceCategoryInsert.setInt(1, service.getServiceID());
                stmtServiceCategoryInsert.setInt(2, category.getCategoryID());
                stmtServiceCategoryInsert.executeUpdate();
            }
        }
    }

	public ArrayList<Service> getByProvider(Provider provider) throws SQLException{
        ArrayList<Service> services = new ArrayList<Service>();
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "select * from services where userID = ?"
            );
            stmt.setInt(1, provider.getUserID());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Service service = new Service(rs);
                service.setProvider(provider);
                services.add(service);
            }
        }
        return services;
	}
}
