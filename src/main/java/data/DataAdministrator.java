package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Administrator;

/**
 * DataAdministrator
 */
public class DataAdministrator {

    public Administrator get(int userID) {
        Administrator administrator = null;
        try {
            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "select * from users as u inner join administrators as a on u.`userID` = a.`userID` where u.`userID` = ?"
            );
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() && rs != null){
                administrator = new Administrator(rs);
            }
        }catch(Exception e){
            // TODO Implementar Loggerrrrrrrrrrrrrrr
        }
        return administrator;
    }

    public void save(Administrator administrator){
        try{
            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO administrators(userID, accessLevel) VALUES(?,?)"
            );
            stmt.setInt(1, administrator.getUserID());
            stmt.setString(2, administrator.getAccessLevel());
        }catch(Exception e){
            //TODO: implementar logger
        }
    }
}