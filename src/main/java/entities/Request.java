package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Request {
	private int requestID;
	private User petitioner;
	private Service service;
	private Provider provider;
	private String requestDate;
	private String responseDate;
	private Review review;
	private String status;
	private Report report;

	public Request(ResultSet rs, User petitioner, Service service, Provider provider, Review review, Report report)
			throws SQLException {
		this.requestID = rs.getInt("requestID");
		this.petitioner = petitioner;
		this.service = service;
		this.provider = provider;
		this.requestDate = rs.getString("requestDate");
		this.review = review;
		this.status = rs.getString("status");
		this.report = report;
		this.responseDate = rs.getString("responseDate");
	}

	public Request(HttpServletRequest request){
		// TODO: NOT IMPLEMENTED
	}
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public User getPetitioner() {
		return petitioner;
	}
	public void setPetitioner(User petitioner) {
		this.petitioner = petitioner;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the review
	 */
	public Review getReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(Review review) {
		this.review = review;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the report
	 */
	public Report getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(Report report) {
		this.report = report;
	}

	/**
	 * @return the responseDate
	 */
	public String getResponseDate() {
		return responseDate;
	}

	/**
	 * @param responseDate the responseDate to set
	 */
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}
}
