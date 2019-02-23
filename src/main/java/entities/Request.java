package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Request {
	// Constants
	// #region
	public static final String STATUS_SOLICITADA = "solicitado";
	public static final String STATUS_RESPONDIDA = "respondid";
	public static final String STATUS_VISTA = "visto";
	public static final String STATUS_CANCELADA = "cancelado";
	public static final String STATUS_MODERACION = "moderacio";
	public static final String STATUS_INHABILITADA = "inhabilit";
	// #endregion

	// Attributes
	private int requestID;
	private User petitioner;
	private Service service;
	private Provider provider;
	private String requestDate;
	private String responseDate;
	private Review review;
	private String status;
	private Report report;
	private String message;
	private String response;

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

	public Request(User petitioner, Service service, Provider provider, String requestDate, String message) {

		this.petitioner = petitioner;
		this.service = service;
		this.provider = provider;
		this.requestDate = requestDate;
		this.message = message;
	}
	public Request(User petitioner, Service service, Provider provider, String message) {
		this.petitioner = petitioner;
		this.service = service;
		this.provider = provider;
		this.message = message;
	}
	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Request(HttpServletRequest request) {
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

	public String getRequestDate() {
		return this.requestDate;
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

	@Override
	public String toString() {
		return "{" + " requestID='" + getRequestID() + "'" + ", petitioner='" + getPetitioner() + "'" + ", service='"
				+ getService() + "'" + ", provider='" + getProvider() + "'" + ", requestDate='" + getRequestDate() + "'"
				+ ", responseDate='" + getResponseDate() + "'" + ", review='" + getReview() + "'" + ", status='"
				+ getStatus() + "'" + ", report='" + getReport() + "'" + ", message='" + getMessage() + "'"
				+ ", response='" + getResponse() + "'" + "}";
	}

	public String toJson() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(this);
	}


	public String toJsonSimplified() {
		Gson gson = new Gson();
		JsonObject data = new JsonObject();

		data.addProperty("requestID", this.requestID); 
		data.addProperty("requestDate", this.requestDate); 
		data.addProperty("petitionerID", this.petitioner.getUserID()); 
		data.addProperty("petitionerName", this.petitioner.getUsername()); 
		data.addProperty("providerID", this.provider.getUserID()); 
		data.addProperty("providerName", this.provider.getName());
		data.addProperty("providerPhone", this.provider.getPhone()); 
		data.addProperty("providerEmail", this.provider.getEmail()); 
		data.addProperty("serviceID", this.service.getServiceID());
		data.addProperty("serviceTitle", this.service.getTitle());
		String request = gson.toJson(data);
		
		return request;
	}
}
