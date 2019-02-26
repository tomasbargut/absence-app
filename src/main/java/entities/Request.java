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

	public Request(ResultSet rs)
			throws SQLException {
		this.requestID = rs.getInt("requestID");
		this.requestDate = rs.getString("requestDate");
		this.status = rs.getString("request_statusID");
		this.responseDate = rs.getString("responseDate");
		this.message = rs.getString("message");
	}

	public Request(User petitioner, Service service, String message) {
		this.petitioner = petitioner;
		this.service = service;
		this.message = message;
	}

	/**
	 * @return the requestID
	 */
	public int getRequestID() {
		return requestID;
	}

	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	/**
	 * @return the petitioner
	 */
	public User getPetitioner() {
		return petitioner;
	}

	/**
	 * @param petitioner the petitioner to set
	 */
	public void setPetitioner(User petitioner) {
		this.petitioner = petitioner;
	}

	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the provider
	 */
	public Provider getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
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
}
