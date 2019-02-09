package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entities.Provider;
import entities.Publication;
import entities.Service;

/**
 * DataPublication
 */
public class DataPublication {

    private DataProvider dataProvider;
    private DataService dataService;

    public DataPublication(){
        this.dataProvider = new DataProvider();
        this.dataService = new DataService();
    }

    public boolean save(Publication publication){
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "insert into provisions(`userID`,`serviceID`) values(?,?)"
            );
            stmt.setInt(1, publication.getProvider().getUserID());
            stmt.setInt(2, publication.getService().getServiceID());
        }catch(Exception e){
            // TODO: Implementar logger
            return false;
        }
        return true;
    }

    public ArrayList<Publication> getByProvider(Provider provider){
        ArrayList<Publication> publications = new ArrayList<Publication>();
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement statementPublications = conn.prepareStatement(
                "select * from provisions where userID = ?"
            );
            statementPublications.setInt(1, provider.getUserID());
            ResultSet rs = statementPublications.executeQuery();
            while (rs.next()) {
                Service service = dataService.get(rs.getInt("serviceID"));
                Publication publication = new Publication(service, provider);
                publications.add(publication);
            }
        } catch (Exception e) {
            //TODO: Implementar logger
        }
        return publications;
    }

    public ArrayList<Publication> getByService(Service service){
        ArrayList<Publication> publications = new ArrayList<Publication>();
        try (Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement statementPublications = conn.prepareStatement(
                "select * from provisions where serviceID = ?"
            );
            statementPublications.setInt(1, service.getServiceID());
            ResultSet rs = statementPublications.executeQuery();
            while (rs.next()) {
                Provider provider = dataProvider.get(rs.getInt("userID"));
                Publication publication = new Publication(service, provider);
                publications.add(publication);
            }
        } catch (Exception e) {
            //TODO: Implementar logger
        }
        return publications;
    }
}