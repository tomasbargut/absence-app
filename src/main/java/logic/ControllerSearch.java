package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Category;
import entities.Provider;
import entities.Report;
import entities.Request;
import entities.Review;
import entities.Service;
import entities.User;
import data.DataService;
import data.DataProvider;
import data.ConnectorBuilder;
import entities.Publication;



public class ControllerSearch {

    private Integer[] priceRange = new Integer[] {0,5000000};
    private int locationRangeinKm;
    private String[] keywords;
    private int prestigeLevel;

    private DataService dataService;
    private DataProvider dataProvider;

    public void DataRequest(){
		this.dataService = new DataService();
		this.dataProvider = new DataProvider();
	}


    public void searchByPrice(int bottomPrice, int topPrice) {
        //Connection conn = ConnectorBuilder.getConnector();
        //Finish Last
    }

    public void searchByProximity(int proximity) {
        //Finish Last
    }

    public ArrayList<Publication> searchBySentence(ArrayList<String> keywords) {
        ArrayList<Publication> publicationResults = new ArrayList<>();
        
		try (Connection conn = ConnectorBuilder.getConnector()){			
			PreparedStatement stmt = conn.prepareStatement(
				"SELECT * from services WHERE CONCAT ('serviceID', 'serviceDesc') LIKE '%"+keywords+"%'"
			);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
			}
		} catch (Exception e) {
			// TODO Implementar logger
			System.out.print(e.getMessage());
		}
		return publicationResults;
    }

    public ArrayList<Publication> searchByKeywords(ArrayList<String> keywords) throws Exception {
        ArrayList<Publication> publicationResults = new ArrayList<>();
        String dynamicQueryString = null;

        //Query builder, tal vez sea buena idea crear una util para esto.
        if(keywords.size() > 1){
            String firstKeyword = keywords.get(0);
            keywords.remove(0);
            dynamicQueryString = "SELECT * FROM services WHERE CONCAT ('serviceID','title', 'desc') LIKE '%"+ firstKeyword + String.join("%' OR LIKE '%", keywords);
        } else if (keywords.size() == 1){
            dynamicQueryString = "SELECT * FROM services WHERE CONCAT ('serviceID','title', 'desc') LIKE '%" + keywords.get(0) +"%'";
        } else { 
            throw new Exception("error Listado de keywords invalido");
        }   
        //consulta a DB con la query      
		try (Connection conn = ConnectorBuilder.getConnector()){			
			PreparedStatement stmt = conn.prepareStatement(dynamicQueryString);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
                //falta construir todas las publicaciones en base al ID del servicio
			}
		} catch (Exception e) {
			// TODO Implementar logger
			System.out.print(e.getMessage());
		}
		return publicationResults;
    }

    public void searchServicesByPrestige(int prestige) {
        //Finish Last
    }

    public Category searchByCategory(String category) {
        Category categoryResult = null;
        try (Connection conn = ConnectorBuilder.getConnector()){
			
			PreparedStatement stmt = conn.prepareStatement(
				"SELECT * from sategories WHERE categoryID LIKE '%"+category+"%'"
			);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
			}
		} catch (Exception e) {
			// TODO Implementar logger
			System.out.print(e.getMessage());
        }
        return categoryResult;
    }

}