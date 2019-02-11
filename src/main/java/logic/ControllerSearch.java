package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Provider;
import entities.Report;
import entities.Request;
import entities.Review;
import entities.Service;
import entities.User;
import data.DataService;
import data.DataProvider;;


//TODO Send the parameter values to DataSearch.java and format them to be shown on Search.java
//code multiple filter selection scenarios

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
        Connection conn = ConnectorBuilder.getConnector();
        
    }

    public void searchByProximity(int proximity) {
        
    }

    public void searchByKeywords(String[] keywords) {
        
    }

    public void searchServicesByPrestige(int prestige) {
        
    }

    public void searchByCategory(String category) {
        
    }

}