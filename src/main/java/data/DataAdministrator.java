package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Administrator;

/**
 * DataAdministrator
 */
public class DataAdministrator {

    public Administrator get(int userID) throws SQLException {
        Administrator administrator = null;
        try(Connection conn = ConnectorBuilder.getConnector()) {
            PreparedStatement stmt = conn.prepareStatement(
                "select * from users as u inner join administrators as a on u.`userID` = a.`userID` where u.`userID` = ?"
            );
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() && rs != null){
                administrator = new Administrator(rs);
            }
        }
        return administrator;
    }

    public boolean save(Administrator administrator){
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO administrators(userID, accessLevel) VALUES(?,?)"
            );
            stmt.setInt(1, administrator.getUserID());
            stmt.setString(2, administrator.getAccessLevel());
        }catch(Exception e){
            //TODO: implementar logger
            return false;
        }
        return true;
    }
}