package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Provider;
import entities.Report;
import entities.Request;
import entities.Review;
import entities.Service;
import entities.User;

public class DataRequest {
	private DataService dataService;
	private DataUser dataUser;
	private DataProvider dataProvider;
	private DataReview dataReview;
	private DataReport dataReport;

	public DataRequest() {
		this.dataService = new DataService();
		this.dataUser = new DataUser();
		this.dataProvider = new DataProvider();
	}

	/**
	 * @author ferna
	 * @param userProvisionRequest
	 * @return ProvisionRequest
	 * @throws Exception
	 */
	public void save(Request request) throws SQLException{
		try (Connection conn = ConnectorBuilder.getConnector()) {
			PreparedStatement stmtReq = conn.prepareStatement(
					"INSERT INTO requests (requesting_userID, serviceID, requestDate, responseDate, request_statusID)"
							+ " VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmtReq.setInt(1, request.getPetitioner().getUserID());
			stmtReq.setInt(2, request.getService().getServiceID());
			stmtReq.setString(3, request.getRequestDate());
			stmtReq.setString(4, request.getResponseDate());
			stmtReq.setString(5, request.getStatus());
			stmtReq.executeUpdate();
			ResultSet generatedKeys = stmtReq.getGeneratedKeys();
			generatedKeys.next();
			request.setRequestID(generatedKeys.getInt(1));
		}
	}

	public ArrayList<Request> all() throws SQLException {
		ArrayList<Request> requests = new ArrayList<Request>();
		try(Connection conn = ConnectorBuilder.getConnector();){
			PreparedStatement stmt = conn.prepareStatement("SELECT requestID FROM requests");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				requests.add(this.get(rs.getInt(1)));
			}
		}
		return requests;
	}

	public void delete(Request solicitud) throws SQLException{
		try(Connection conn = ConnectorBuilder.getConnector()) {
			PreparedStatement stmtReq = null;
			if (solicitud.getRequestID() != 0) {
				stmtReq = conn.prepareStatement("UPDATE requests SET request_statusID = ?"
						+ " WHERE requestID = ? AND requesting_user = ? AND serviceID = ? AND request_statusID = ? ");
				stmtReq.setString(1, Request.STATUS_CANCELADA);
				stmtReq.setInt(2, solicitud.getRequestID());
				stmtReq.setInt(3, solicitud.getProvider().getUserID());
				stmtReq.setInt(4, solicitud.getService().getServiceID());
				stmtReq.setString(5, Request.STATUS_SOLICITADA);
				stmtReq.executeUpdate();
			} else {
				stmtReq = conn.prepareStatement("UPDATE requests SET request_statusID = ?"
						+ " WHERE requesting_user = ? AND serviceID = ? AND request_statusID = ? ");
				stmtReq.setString(1, Request.STATUS_CANCELADA);
				stmtReq.setInt(2, solicitud.getPetitioner().getUserID());
				stmtReq.setInt(3, solicitud.getService().getServiceID());
				stmtReq.setString(4, Request.STATUS_SOLICITADA);
				stmtReq.executeUpdate();
			}
		}
	}

	public Integer getIdByAttr(Request solicitudSinID) throws Exception {
		Integer requestID = 0;
		try {
			Connection conn = ConnectorBuilder.getConnector();
			PreparedStatement stmtReq = conn.prepareStatement("SELECT * FROM requests"
					+ " WHERE requesting_user = ? AND providerID = ? AND serviceID = ? AND request_statusID = ? ");
			stmtReq.setInt(1, solicitudSinID.getPetitioner().getUserID());
			stmtReq.setInt(2, solicitudSinID.getProvider().getUserID());
			stmtReq.setInt(3, solicitudSinID.getService().getServiceID());
			stmtReq.setString(4, Request.STATUS_SOLICITADA);
			ResultSet rs = stmtReq.executeQuery();
			if (rs != null) {
				requestID = rs.getInt("requestID");
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return requestID;
		}
		return requestID;
	}

	public void update(Request request) throws SQLException {
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmtReq = conn.prepareStatement(
				"UPDATE requests requesting_userID=?, serviceID=?, requestDate=?, responseDate=?, request_statusID=? WHERE `requestID = ?"
			);
			stmtReq.setInt(1, request.getPetitioner().getUserID());
			stmtReq.setInt(2, request.getService().getServiceID());
			stmtReq.setString(3, request.getRequestDate());
			stmtReq.setString(4, request.getResponseDate());
			stmtReq.setString(5, request.getStatus());
			stmtReq.setInt(6, request.getRequestID());
			stmtReq.executeUpdate();
		}
	}

	public Request get(int requestID) throws SQLException {
		Request request = null;
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement(
				"select * from requests where requestID = ?"
			);
			stmt.setInt(1, requestID);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				User petitioner = dataUser.get(rs.getInt("requesting_userID"));
				Service service = dataService.get(rs.getInt("serviceID"));
				request = new Request(rs);
				request.setPetitioner(petitioner);
				request.setService(service);
			}
		}
		return request;
	}

	public ArrayList<Request> getAll(Request request) throws SQLException{
		ArrayList<Request> requests = new ArrayList<Request>();
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement(
				"select requestID from requests where serviceID = ? and requesting_userID = ?"
			);
			stmt.setInt(1, request.getService().getServiceID());
			stmt.setInt(2, request.getPetitioner().getUserID());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				requests.add(this.get(rs.getInt(1)));
			}
		}
		return requests;
	}

	public List<Request> getAllByProvider(User user) throws SQLException{
		ArrayList<Request> ProvisionRequestList = new ArrayList<Request>();
		try(Connection conn = ConnectorBuilder.getConnector();){
			PreparedStatement stmt = conn.prepareStatement(
				"SELECT r.requestID FROM requests AS r INNER JOIN services AS s WHERE s.userID = ?"
			);
			stmt.setInt(1, user.getUserID());
			try(ResultSet rs = stmt.executeQuery();){
				while (rs.next()) {
					Request request = this.get(rs.getInt("requestID"));
					ProvisionRequestList.add(request);
				}	
			}
			return ProvisionRequestList;
		}
	}
}