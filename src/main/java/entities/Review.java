package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Review {
    private Request request;
    private String title;
    private String desc;
    private int pointsGiven;
    private String reviewDate;

    public Review(ResultSet rs) throws SQLException {
        this.title = rs.getString("title");
        this.desc = rs.getString("desc");
        this.pointsGiven = rs.getInt("pointsGiven");
        this.reviewDate = rs.getString("reviewDate");
    }

    /**
     * @return the request
     */
    public Request getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    public Review(HttpServletRequest req) {
        this.title = req.getParameter("title");
        this.desc = req.getParameter("desc");
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
