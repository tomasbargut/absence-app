package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Review;

public class DataReview {

    private final DataRequest dataRequest;

    public DataReview(){
        dataRequest = new DataRequest();
    }
    public Review get(int reviewID) throws SQLException{
        Review review = null;
        try(Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM reviews WHERE requestID = ?"
            );){
            stmt.setInt(1, reviewID);
            try(ResultSet rs = stmt.executeQuery();){
                if (rs.next() ){
                    review = new Review(rs);
                    review.setRequest(dataRequest.get(rs.getInt("requestID")));
                }
            }
        }
        return review;
    }

    public void save(Review review) throws SQLException{
        try(Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmtService = conn.prepareStatement(
                "INSERT INTO reviews(title, `desc`, pointsGiven, reviewDate, requestID) VALUES(?,?,?,?,?)"
            );){
            stmtService.setString(1, review.getTitle());
            stmtService.setString(2, review.getDesc());
            stmtService.setInt(3, review.getPointsGiven());
            stmtService.setString(4, review.getReviewDate());
            stmtService.setInt(5, review.getRequest().getRequestID());
            stmtService.executeUpdate();
        }
    }
}