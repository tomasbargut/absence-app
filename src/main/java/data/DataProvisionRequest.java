package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Chat;
import entities.Provider;
import entities.Provision;
import entities.ProvisionRequest;
import entities.User;

public class DataProvisionRequest {

	/**
	 * @author ferna
	 * @param userProvisionRequest
	 * @return ProvisionRequest
	 * @throws Exception
	 */
	public ProvisionRequest saveProvisionRequest(ProvisionRequest userProvisionRequest) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();

		ProvisionRequest request = userProvisionRequest;
		User user = request.getUser();
		Provider provider = request.getProvider();
		Provision provision = request.getProvision();
		Chat chat = request.getChat();

		try {
			PreparedStatement stmtReq = conn.prepareStatement(
					"INSERT INTO requests (requestingUser, requestDate, providerID, pendingNotifications, provisionID, chatID) VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmtReq.setInt(1, user.getUserID());
			stmtReq.setObject(2, request.getRequestDate());
			stmtReq.setInt(3, provider.getUser_id());
			stmtReq.setBoolean(4, provider.getPendingNotifications());
			stmtReq.setInt(5, provision.getProvisionID());
			stmtReq.setInt(6, chat.getChatID());

			stmtReq.executeUpdate();
			ResultSet rs = stmtReq.getGeneratedKeys();
			rs.next();
			request.setRequestID(rs.getInt(1));

			conn.close();
			
			// TODO check if returning the request is correct, shouldn't return boolean
			// result instead?

		} catch (Exception e) {
			System.out.println(e);
		}
		return request;
	}

	public ArrayList<ProvisionRequest> getAllProvisionRequest(Provider provider) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();
		ArrayList<ProvisionRequest> ProvisionRequestList = new ArrayList<ProvisionRequest>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ProvisionRequest WHERE provider.user_id = ?");
			stmt.setInt(0, provider.getUser_id());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ProvisionRequest pR = buildProvisionRequest(rs);
				pR.setProvider(provider);
				ProvisionRequestList.add(pR);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return ProvisionRequestList;
	}

	protected ProvisionRequest buildProvisionRequest(ResultSet rs) throws SQLException {
		ProvisionRequest pR = new ProvisionRequest();

		// TODO consultar corresponde al DAO construir cada provisionRequest en base a
		// la request (DB version de solicitud de proveeduria) o si deberia solo
		// retornar la ultima mencionada, y a partir de la misma construir la
		// super-entidad desde el controlador

		return pR;
	}
}