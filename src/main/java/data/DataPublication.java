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

    public DataPublication() {
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
            stmt.executeUpdate();
        }catch(Exception e){
            // TODO: Implementar logger
            return false;
        }
        return true;
    }

    public Publication getOneByID(int proveedor, int servicio) {
        Publication publication = null;
        try {

            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement statementPublications = conn
                    .prepareStatement("select * from provisions where userID = ? and serviceID = ?");
            statementPublications.setInt(1, proveedor);
            statementPublications.setInt(2, servicio);
            ResultSet rs = statementPublications.executeQuery();
            rs.next();
            Service service = dataService.get(rs.getInt("serviceID"));
            Provider provider = dataProvider.get(rs.getInt("userID"));
            publication = new Publication(service, provider);

            conn.close(); 

        } catch (Exception e) {
            // TODO: Implementar logger
            // parecera redundante pero si falla significa que no existia una publicacion de este tipo en la DB y alguien intento falsear un registro ingresando manualmente un proveedor y un servicio no correlativos
        }
        return publication;
    }

    public ArrayList<Publication> getByProvider(Provider provider) {
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
            // TODO: Implementar logger
        }
        return publications;
    }

    public ArrayList<Publication> getByService(Service service) {
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
            // TODO: Implementar logger
        }
        return publications;
    }

	public ArrayList<Publication> all() {
        ArrayList<Publication> publications = new ArrayList<Publication>();
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "select * from provisions"
            );
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Service service = dataService.get(rs.getInt("serviceID"));
                Provider provider = dataProvider.get(rs.getInt("userID"));
                Publication publication = new Publication(service, provider);
                publications.add(publication);
            }
        }catch(Exception e){
            // IMPLEMENTAR LOGGER
        }
		return publications;
	}

}