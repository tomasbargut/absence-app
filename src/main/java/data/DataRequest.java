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
	public Integer save(Request request) {

		try (Connection conn = ConnectorBuilder.getConnector()) {
			PreparedStatement stmtReq = conn.prepareStatement(
					"INSERT INTO requests (requesting_userID, providerID, serviceID, requestDate, responseDate, reviewID, request_statusID, reportID)"
							+ " VALUES(?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmtReq.setInt(1, request.getPetitioner().getUserID());
			stmtReq.setInt(2, request.getProvider().getUserID());
			stmtReq.setInt(3, request.getService().getServiceID());
			stmtReq.setString(4, request.getRequestDate());
			stmtReq.setString(5, request.getResponseDate());
			stmtReq.setInt(6, request.getReview().getReviewID());
			stmtReq.setString(7, request.getStatus());
			stmtReq.setInt(8, request.getReport().getReportID());
			stmtReq.executeUpdate();

			ResultSet generatedKeys = stmtReq.getGeneratedKeys();
			generatedKeys.next();
			request.setRequestID(generatedKeys.getInt(1));
			return request.getRequestID();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public ArrayList<Request> all() throws Exception {
		ArrayList<Request> ProvisionRequestList = new ArrayList<Request>();
		try(Connection conn = ConnectorBuilder.getConnector();){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM requests");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User petitioner = dataUser.get(rs.getInt("requesting_userID"));
				Service service = dataService.get(rs.getInt("serviceID"));
				Provider provider = dataProvider.get(rs.getInt("providerID"));
				Review review = dataReview.get(rs.getInt("reviewID"));
				Report report = dataReport.get(rs.getInt("reportID"));
				Request request = new Request(rs, petitioner, service, provider, review, report);
				ProvisionRequestList.add(request);
			}

		} catch (Exception e) {
			// TODO: IMPLEMENTAR LOGGER
		}

		return ProvisionRequestList;
	}

	public boolean delete(Request solicitud) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();
		try {
			PreparedStatement stmtReq = null;

			if (solicitud.getRequestID() != 0) {
				stmtReq = conn.prepareStatement("UPDATE requests SET request_statusID = ?"
						+ " WHERE requestID = ? AND requesting_user = ? AND providerID = ? AND serviceID = ? AND request_statusID = ? ");
				stmtReq.setString(1, Request.STATUS_CANCELADA);
				stmtReq.setInt(2, solicitud.getRequestID());
				stmtReq.setInt(3, solicitud.getPetitioner().getUserID());
				stmtReq.setInt(4, solicitud.getProvider().getUserID());
				stmtReq.setInt(5, solicitud.getService().getServiceID());
				stmtReq.setString(6, Request.STATUS_SOLICITADA);
				stmtReq.executeUpdate();
			} else {
				stmtReq = conn.prepareStatement("UPDATE requests SET request_statusID = ?"
						+ " WHERE requesting_user = ? AND providerID = ? AND serviceID = ? AND request_statusID = ? ");
				stmtReq.setString(1, Request.STATUS_CANCELADA);
				stmtReq.setInt(2, solicitud.getPetitioner().getUserID());
				stmtReq.setInt(3, solicitud.getProvider().getUserID());
				stmtReq.setInt(4, solicitud.getService().getServiceID());
				stmtReq.setString(5, Request.STATUS_SOLICITADA);
				stmtReq.executeUpdate();
			}
			conn.close();

			return true;

		} catch (Exception e) {
			// TODO: IMPLEMENTAR LOGGER
			return false;
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

	public boolean update(Request request) {
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmtReq = conn.prepareStatement(
				"UPDATE requests requesting_userID=?, providerID=?, serviceID=?, requestDate=?, responseDate=?, reviewID=?, request_statusID=?, reportID=? WHERE `requestID = ?"
			);
			stmtReq.setInt(1, request.getPetitioner().getUserID());
			stmtReq.setInt(2, request.getProvider().getUserID());
			stmtReq.setInt(3, request.getService().getServiceID());
			stmtReq.setString(4, request.getRequestDate());
			stmtReq.setString(5, request.getResponseDate());
			stmtReq.setInt(6, request.getReview().getReviewID());
			stmtReq.setString(7, request.getStatus());
			stmtReq.setInt(8, request.getReport().getReportID());
			stmtReq.setInt(9, request.getRequestID());
			stmtReq.executeUpdate();
		}catch(Exception e ){
			//TODO: IMPLEMENTAR LOGGER
			return false;
		}
		return true;
	}

	public Request get(int requestID) {
		Request request = null;
		try(Connection conn = ConnectorBuilder.getConnector()){
			PreparedStatement stmt = conn.prepareStatement(
				"select * from requests where id = ?"
			);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				User petitioner = dataUser.get(rs.getInt("requesting_userID"));
				Provider provider = dataProvider.get(rs.getInt("providerID"));
				Service service = dataService.get(rs.getInt("serviceID"));
				Report report = dataReport.get(rs.getInt("reportID"));
				Review review = dataReview.get(rs.getInt("reviewID"));
				request = new Request(rs, petitioner, service, provider, review, report);
				return request;
			}
		}catch(Exception e){
			//TODO IMPLEMENTAR LOGGER
			e.printStackTrace();
		}
		return request;
	}

	public List<Request> getAllByProvider(User user) {
		ArrayList<Request> ProvisionRequestList = new ArrayList<Request>();
		try(Connection conn = ConnectorBuilder.getConnector();){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM requests WHERE providerID = ?");
			stmt.setInt(1, user.getUserID());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User petitioner = dataUser.get(rs.getInt("requesting_userID"));
				Service service = dataService.get(rs.getInt("serviceID"));
				Provider provider = dataProvider.get(rs.getInt("providerID"));
				Review review = dataReview.get(rs.getInt("reviewID"));
				Report report = dataReport.get(rs.getInt("reportID"));
				Request request = new Request(rs, petitioner, service, provider, review, report);
				ProvisionRequestList.add(request);
			}

		} catch (Exception e) {
			// TODO: IMPLEMENTAR LOGGER
		}

		return ProvisionRequestList;
	}
}