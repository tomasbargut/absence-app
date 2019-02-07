/**
 * 
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Service;

/**
 * @author ferna
 *
 */
public class DataService {
    private DataCategory dataCategory;

    public DataService() {
        dataCategory = new DataCategory();
    }

    public Service get(int serviceID) {
        Service service = null;
        try {
            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmtService = conn.prepareStatement(
                "select * from services where serviceID = ?"
            );
            stmtService.setInt(1, serviceID);
            ResultSet rs = stmtService.executeQuery();
            if(rs.next() && rs != null){
                service = new Service(rs);
            }
            conn.close();
        } catch (Exception e) {
            // TODO: Implemetar logger
        }
        return service;
    }

    public Service[] getByKeywords(String[] keyowrds) {
        Service[] services = null;
        try {
            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmtService = conn.prepareStatement(
                "select * from services where serviceID = ?"
            );
            stmtService.setInt(1, serviceID);
            ResultSet rs = stmtService.executeQuery();
            if(rs.next() && rs != null){
                service = new Service(rs);
            }
            conn.close();
        } catch (Exception e) {
            // TODO: Implemetar logger
        }
        return services[];
    }


    public void save(Service service){
        try{
            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO service(title, desc, categoryID) values(?,?,?)"
            );
            stmt.setString(1, service.getTitle());
            stmt.setString(2, service.getDesc());
            stmt.setInt(3, 0);
            stmt.execute();
        }catch(Exception e){
            // TODO: IMPLEMENTAR LOGGER
        }
    }
}
