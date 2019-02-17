/**
 * 
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.Category;
import entities.Service;

/**
 * @author ferna
 *
 */
public class DataService {

    public DataService() {
    }

    public Service get(int serviceID) {
        Service service = null;
        try(Connection conn = ConnectorBuilder.getConnector()) {
            PreparedStatement stmtService = conn.prepareStatement(
                "select * from services where serviceID = ?"
            );
            stmtService.setInt(1, serviceID);
            ResultSet rs = stmtService.executeQuery();
            if(rs.next() && rs != null){
                service = new Service(rs);
            }
        } catch (Exception e) {
            // TODO: Implemetar logger
        }
        return service;
    }

    public boolean save(Service service){
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO services(`title`, `desc`) values(?,?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, service.getTitle());
            stmt.setString(2, service.getDesc());
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
                stmt.executeUpdate();
            }
        }catch(Exception e){
            // TODO: IMPLEMENTAR LOGGER
            return false;
        }
        return true;
    }

    public boolean update(Service service){
        try(Connection conn = ConnectorBuilder.getConnector()) {
            PreparedStatement stmtService = conn.prepareStatement(
                "UPDATE service SET `desc`= ?, `title`= ? WHERE `serviceID` = ?"
            );
            stmtService.setString(1, service.getDesc());
            stmtService.setString(2, service.getTitle());
            stmtService.setInt(3, service.getServiceID());
            stmtService.executeUpdate();
            PreparedStatement stmtServiceCategoryInsert = conn.prepareStatement(
                "insert into services_categories(serviceID, categoryID) values(?,?)"
            );
            PreparedStatement stmtServiceCategorySelect = conn.prepareStatement(
                "select * from services_categories where `serviceID`= ? and `categoryID` = ?"
            );
            for (Category category : service.getCategories()) {
                stmtServiceCategorySelect.setInt(1,service.getServiceID());
                stmtServiceCategorySelect.setInt(2, category.getCategoryID());
                ResultSet rs = stmtServiceCategorySelect.executeQuery();
                if(!rs.next()){
                    stmtServiceCategoryInsert.setInt(1, service.getServiceID());
                    stmtServiceCategoryInsert.setInt(2, category.getCategoryID());
                    stmtServiceCategoryInsert.executeUpdate();
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return true;
    }
}
