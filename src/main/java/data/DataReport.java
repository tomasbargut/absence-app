package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Administrator;
import entities.Report;

/**
 * DataReport
 */
public class DataReport {

    private DataAdministrator dataAdministrator;

    public DataReport(){
        this.dataAdministrator = new DataAdministrator();
    }

    public boolean save(Report report){
        try(Connection conn = ConnectorBuilder.getConnector();){
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO reports(title, desc, sentDate, answerDate, statusID, administratorID, reportType) values(?,?,?,?,?,?,?)"
            );
            stmt.setString(1,report.getTilte());
            stmt.setString(2, report.getDesc());
            stmt.setString(3, report.getSentDate());
            stmt.setString(4, report.getAnswerDate());
            stmt.setString(5, report.getStatus());
            stmt.setInt(6, report.getAdministrator().getUserID());
            stmt.setString(7, report.getReportType());
            stmt.executeUpdate();
        } catch (Exception e) {
            //TODO: handle except
            return false;
        }
        return true;
    }

    public Report get(int reportID){
        Report report = null;
        try(Connection conn = ConnectorBuilder.getConnector()){
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM reports WHERE reportID = ?"
            );
            stmt.setInt(1, reportID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() && rs != null){
                Administrator administrator = dataAdministrator.get(rs.getInt("administratedBy"));
                report = new Report(rs, administrator);
            }
        } catch (Exception e) {
            //TODO: Implementar logger
        }
        return report;
    }
}