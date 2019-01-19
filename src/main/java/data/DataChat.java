package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import entities.Chat;


public class DataChat {

	public void saveChat(Chat chat) throws Exception {
		Connection conn = ConnectorBuilder.getConnector();
		
		PreparedStatement stmtReq = conn.prepareStatement(
					"INSERT INTO requests (chatID, chatFile) VALUES(?,?)"
				);
		stmtReq.setInt(1, chat.getChatID());
		stmtReq.setObject(2, chat.getChat());
				
		stmtReq.executeUpdate();		
	
		conn.close();
	}				
}
