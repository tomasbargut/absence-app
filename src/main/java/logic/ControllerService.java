package logic;

import java.sql.SQLException;

import data.DataService;
import entities.Service;
import logic.exceptions.ServiceException;
import utils.Utils;

/**
 * ControllerService
 */
public class ControllerService {
    private final DataService dataService;

    public ControllerService(){
        this.dataService = new DataService();
    }
	public void save(Service service) throws ServiceException, SQLException {
        validar(service);
        dataService.save(service);
    }
    
    private void validar(Service service) throws ServiceException {
        if(Utils.isNullOrEmpty(service.getTitle())){
            throw new ServiceException("Titulo invalido");
        }
        if(Utils.isNullOrEmpty(service.getDesc())){
            throw new ServiceException("Descripcion invalida");
        }
    }
	public void update(Service service) throws ServiceException, SQLException{
        validar(service);
        dataService.update(service);
	}
}