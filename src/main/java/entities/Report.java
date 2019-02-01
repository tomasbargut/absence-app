package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report {
    private int reportID;
    private String tilte;
    private String desc;
    private String sentDate;
    private String answerDate;
    private String status;
    private Administrator administrator;
    private String reportType;

    public Report(ResultSet rs, Administrator admin) throws SQLException {
        this.reportID = rs.getInt("reportID");
        this.tilte = rs.getString("title");
        this.desc = rs.getString("desc");
        this.sentDate = rs.getString("sentDate");
        this.answerDate = rs.getString("answerDate");
        this.status = rs.getString("statusID");
        this.administrator = admin;
        this.reportType = rs.getString("reportType");
    }
    /**
     * @return the reportID
     */
    public int getReportID() {
        return reportID;
    }

    /**
     * @param reportID the reportID to set
     */
    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    /**
     * @return the tilte
     */
    public String getTilte() {
        return tilte;
    }

    /**
     * @param tilte the tilte to set
     */
    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the sentDate
     */
    public String getSentDate() {
        return sentDate;
    }

    /**
     * @param sentDate the sentDate to set
     */
    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * @return the answerDate
     */
    public String getAnswerDate() {
        return answerDate;
    }

    /**
     * @param answerDate the answerDate to set
     */
    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
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
     * @return the administratorID
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
     * @param administratorID the administratorID to set
     */
    public void setAdministratorID(Administrator administrator) {
        this.administrator = administrator;
    }

    /**
     * @return the reportType
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * @param reportType the reportType to set
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
