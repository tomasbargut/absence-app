package logic;

import data.DataPublication;
import data.DataService;
import entities.Category;
import entities.Publication;
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
	public boolean save(Service service) throws ServiceException {
        validar(service);
        return dataService.save(service);
    }
    
    private void validar(Service service) throws ServiceException {
        if(Utils.isNullOrEmpty(service.getTitle())){
            throw new ServiceException("Titulo invalido");
        }
        if(Utils.isNullOrEmpty(service.getDesc())){
            throw new ServiceException("Descripcion invalida");
        }
    }
	public boolean update(Service service) throws ServiceException{
        validar(service);
        return dataService.update(service);
	}

    
}