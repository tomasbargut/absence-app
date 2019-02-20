package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Review {
    private int reviewID;
    private String title;
    private String desc;
    private int pointsGiven;
    private String reviewDate;

    public Review(ResultSet rs) throws SQLException {
        this.reviewID= rs.getInt("reviewID");
        this.title = rs.getString("title");
        this.desc = rs.getString("desc");
        this.pointsGiven = rs.getInt("pointsGiven");
        this.reviewDate = rs.getString("reviiwDate");
    }
    public Review(HttpServletRequest req) {
        this.title = req.getParameter("title");
        this.desc = req.getParameter("desc");
        this.pointsGiven = Integer.parseInt(req.getParameter("pointsGiven"));
	}
	/**
     * @return the reviewID
     */
    public int getReviewID() {
        return reviewID;
    }

    /**
     * @param reviewID the reviewID to set
     */
    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the pointsGives
     */
    public int getPointsGiven() {
        return pointsGiven;
    }

    /**
     * @param pointsGive the pointsGive to set
     */
    public void setPointsGive(int pointsGiven) {
        this.pointsGiven = pointsGiven;
    }

    /**
     * @return the reviewDate
     */
    public String getReviewDate() {
        return reviewDate;
    }

    /**
     * @param reviewDate the reviewDate to set
     */
    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
