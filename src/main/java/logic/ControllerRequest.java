package logic;

import java.sql.SQLException;

import data.DataRequest;
import entities.Request;
import logic.exceptions.RequestException;
import utils.Utils;

public class ControllerRequest {
	private DataRequest datarequest;

	public ControllerRequest() {
		datarequest = new DataRequest();
	}
	
	/**
	 * 
	 * @param solicitante
	 * @param publicationID
	 * @param requestIDtoDelete
	 * @return Deletion Status String
	 * @throws Exception
	 */
	public void delete(Request request) throws SQLException, RequestException {
		// Este metodo no borra datos, solo la busca en DB y actualiza su estado a "cancelada".
		// RN: No podra "eliminarla" si: 1) no existe la request. 2) La request fue
		// vista, respondida, moderada, inhibida, borrada.
		// (Esto ultimo asegura que la publicacion sea la ultima que haya hecho el
		// usuario)
		datarequest.delete(request);
	}

	private void validar(Request request) throws SQLException, RequestException{
		for(Request req: datarequest.getAll(request)){
			if(req.getStatus() != "CANCELADO"){ // TODO: HACER ENUM PARA ESTO
				throw new RequestException("Ya hay una request vijente");
			}
		}
		if(Utils.isNullOrEmpty(request.getMessage())){
			throw new RequestException("Envia un mensaje al proveedor");
		}
	}

	public void update(Request request) throws SQLException, RequestException{
		validar(request);	
		datarequest.update(request);
	}

	public void save(Request request) throws SQLException, RequestException{
		request.setRequestDate(Utils.getCurrentTime());
		request.setProvider(request.getService().getProvider());
		request.setStatus("CREADO");
		validar(request);
		datarequest.save(request);
	}
}
