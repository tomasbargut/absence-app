package logic;

import java.sql.SQLException;

import data.DataReport;
import entities.Report;
import logic.exceptions.ReportException;
import utils.Utils;

/**
 * ControllerReport
 */
public class ControllerReport {
    private final DataReport dataReport;
    public ControllerReport(){
        dataReport = new DataReport();
    }
	public void save(Report report) throws ReportException, SQLException{
        report.setSentDate(Utils.getCurrentTime());
        report.setStatus("CREADA");
        dataReport.save(report);
	}
	public void update(Report report) throws SQLException{
		dataReport.update(report);
	}

    
}