package logic;

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
	public boolean save(Report report) throws ReportException{
        report.setSentDate(Utils.getCurrentTime());
        report.setStatus("CREADA");
        return dataReport.save(report);
	}
	public boolean update(Report report) {
        //TODO IMPLEMENTAR BORRADO DE PULBICAICIO EN CASO DE APROBADO
		return dataReport.update(report);
	}

    
}