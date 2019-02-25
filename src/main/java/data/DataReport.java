package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Administrator;
import entities.Report;
import entities.Request;

/**
 * DataReport
 */
public class DataReport {

    private final DataAdministrator dataAdministrator;
    private final DataRequest dataRequest;

    public DataReport(){
        this.dataAdministrator = new DataAdministrator();
        this.dataRequest = new DataRequest();
    }

    public void save(Report report) throws SQLException{
        try(Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO reports(title, `desc`, sentDate, answerDate, `statusID`, reportType, `requestID`) values(?,?,?,?,?,?,?)"
            );){
            stmt.setString(1,report.getTitle());
            stmt.setString(2, report.getDesc());
            stmt.setString(3, report.getSentDate());
            stmt.setString(4, report.getAnswerDate());
            stmt.setString(5, report.getStatus());
            stmt.setString(6, report.getReportType());
            stmt.setInt(7, report.getRequest().getRequestID());
            stmt.executeUpdate();
        }
    }

    public Report get(int requestID) throws SQLException{
        Report report = null;
        try(Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM reports WHERE requestID = ?"
            );){
            stmt.setInt(1, requestID);
            try(ResultSet rs = stmt.executeQuery();){
                if(rs.next()){
                    Administrator administrator = dataAdministrator.get(rs.getInt("administratedBy"));
                    Request request = dataRequest.get(rs.getInt("requestID"));
                    report = new Report(rs);
                    report.setAdministrator(administrator);
                    report.setRequest(request);
                }
            }
        }
        return report;
    }

	public void update(Report report) throws SQLException{
        try(Connection conn = ConnectorBuilder.getConnector();
            PreparedStatement stmt = conn.prepareStatement(
                "update reports set title=?,`desc`=?,sentDate=?,answerDate=?,`statusID`=?,`administratorID`=?,reportType=? WHERE requestID = ?"
            );){
            stmt.setString(1,report.getTitle());
            stmt.setString(2, report.getDesc());
            stmt.setString(3, report.getSentDate());
            stmt.setString(4, report.getAnswerDate());
            stmt.setString(5, report.getStatus());
            stmt.setInt(6, report.getAdministrator().getUserID());
            stmt.setString(7, report.getReportType());
            stmt.setInt(8, report.getRequest().getRequestID());
            stmt.executeUpdate();
        }
    }
}