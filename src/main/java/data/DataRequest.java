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

	//#region
	private static final String STATUS_SOLICITADA = "solicitado";
	private static final String STATUS_RESPONDIDA = "respondid";
	private static final String STATUS_VISTA = "visto";
	private static final String STATUS_CANCELADA = "cancelado";
	private static final String STATUS_MODERACION = "moderacio";
	private static final String STATUS_INHABILITADA = "inhabilit";
	//#endregion

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
					"INSERT INTO requests (requesting_userID, providerID, serviceID, requestDate, responseDate, reviewID, request_statusID, reportID) VALUES(?,?,?,?,?,?,?,?)");
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

	public ArrayList<Request> all() {
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

	public boolean deleteRequest(Request solicitud) {
		Connection conn = ConnectorBuilder.getConnector();
		try {
			PreparedStatement stmtReq = null;
			
			if (solicitud.getRequestID() != 0) {
				stmtReq = conn.prepareStatement(
						"UPDATE requests SET request_statusID = ? WHERE requestID = ? AND requesting_user = ? AND providerID = ? AND serviceID = ? AND request_statusID = ? ");		
				stmtReq.setString(1, solicitud.getStatus(STATUS_CANCELADA));
				stmtReq.setInt(2, solicitud.getRequestID());
				stmtReq.setInt(3, solicitud.getPetitioner().getUserID());
				stmtReq.setInt(4, solicitud.getProvider().getUserID());
				stmtReq.setInt(5, solicitud.getService().getServiceID());
				stmtReq.setString(6, solicitud.getStatus(STATUS_SOLICITADA));
				stmtReq.executeUpdate();
			} else {
				stmtReq = conn.prepareStatement(
						"UPDATE requests SET request_statusID = ? WHERE requesting_user = ? AND providerID = ? AND serviceID = ? AND request_statusID = ? ");		
				stmtReq.setString(1, solicitud.getStatus(STATUS_CANCELADA));
				stmtReq.setInt(2, solicitud.getPetitioner().getUserID());
				stmtReq.setInt(3, solicitud.getProvider().getUserID());
				stmtReq.setInt(4, solicitud.getService().getServiceID());
				stmtReq.setString(5, solicitud.getStatus(STATUS_SOLICITADA));
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
		return false;
	}
}