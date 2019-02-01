package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Review;

public class DataReview {
    public Review get(int reviewID) {
        Review review = null;
        try {
            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM reviews WHERE reviewID = ?"
            );
            stmt.setInt(1, reviewID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs != null){
                review = new Review(rs);
            }
            conn.close();
        }catch(Exception e){
            // TODO: Implementar logger
        }
        return review;
    }

    public void save(Review review){
        try{
            Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmtService = conn.prepareStatement(
                "INSERT INTO reviews(title, desc, pointsGiven, reviewDate) VALUES(?,?,?,?)"
            );
            stmtService.setString(1, review.getTitle());
            stmtService.setString(2, review.getDesc());
            stmtService.setInt(3, review.getPointsGiven());
            stmtService.setString(4, review.getReviewDate());
            stmtService.executeUpdate();
            conn.close();
        }catch(Exception e){
            // TODO: IMPLEMENTAR LOGGER
        }
    }
}