package data;

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
	public Integer save(Request request) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();
		try {
			PreparedStatement stmtReq = conn.prepareStatement(
					"INSERT INTO requests (requesting_userID, providerID, serviceID, requestDate, responseDate, reviewID, request_statusID, reportID)"
							+ " VALUES(?,?,?,?,?,?,?,?)");
			stmtReq.setInt(1, request.getPetitioner().getUserID());
			stmtReq.setInt(2, request.getProvider().getUserID());
			stmtReq.setInt(3, request.getService().getServiceID());
			stmtReq.setString(4, request.getRequestDate());
			stmtReq.setString(5, request.getResponseDate());
			stmtReq.setInt(6, request.getReview().getReviewID());
			stmtReq.setString(7, request.getStatus());
			stmtReq.setInt(8, request.getReport().getReportID());
			stmtReq.executeUpdate();

			ResultSet rs = stmtReq.executeQuery("select last_insert_id() as last_id from requests");
			Integer lastid = Integer.parseInt(rs.getString("last_id"));
			conn.close();

			return lastid;

		} catch (Exception e) {
			// TODO: IMPLEMENTAR LOGGER
			return null;
		}
	}

	public ArrayList<Request> all() throws Exception {
		ArrayList<Request> ProvisionRequestList = new ArrayList<Request>();
		try {
			Connection conn = ConnectorBuilder.getConnector();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM request");
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
			System.out.println(e);
		}

		return ProvisionRequestList;
	}

	public boolean deleteRequest(Request solicitud) throws Exception {
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
			ResultSet rs = stmtReq.executeQuery("select last_insert_id() as last_id from requests");
			Integer lastid = Integer.parseInt(rs.getString("last_id"));
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
}