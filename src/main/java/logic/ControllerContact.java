package logic;

import data.*;
import entities.*;
import logic.exceptions.*;
import utils.Utils;

//PLACEHOLDER CLASS


public class ControllerContact {
    private DataUser datauser;
    private DataProvider dataprovider;
    private DataRequest datarequest;
    private DataPublication datapublication;

	public ControllerContact() {
        datauser = new DataUser();
        dataprovider = new DataProvider();
        datarequest = new DataRequest();
		datapublication = new DataPublication();
	}
	
	public Request newRequest(User solicitante, String publicationID, String mensaje, String fechaInicio) throws Exception {
		try {
			Publication publication = new Publication(publicationID); 
			datapublication.getOneByID(publication.getProviderID(), publication.getServiceID());
			
			Request solicitud = new Request(solicitante, publication.getService(), publication.getProvider(), fechaInicio, mensaje);
			Integer requestID = datarequest.save(solicitud);
			if(requestID != null){
				solicitud.setRequestID(requestID);
				return solicitud;
			} else {
				return null;
			}

		} catch (Exception ex){
			//error la publicacion no existe

		}
		// TODO IMPLEMENTAR AVISO A PROVEEDOR Y GUARDADO DE REQUEST

		/* comprobarUsuario(user);
		user.setAccountStatus("activo");
		user.setLastLogin(Utils.getCurrentTime());
		datauser.save(user); */
	}
	/* 
	public boolean isValid(String field) {
		//Chequea si no estta vacio o nulo
		if(isNullOrEmpty(field)) {
			return false;
		}
		//Chequea si tiene la longitud correcta
		if(field.length() > 24 || field.length() < 6) {
			return false;
		}
		//Checuea si es alfanumerico
		if(!field.matches("[a-zA-Z0-9]+")) {
			return false;
		}
		return true;
	}
	
	
	public void comprobarUsuario(User user) throws Exception {
		if(!isValid(user.getUsername())) {
			throw new UserException("Usuario no valido");
		}
		if(!isValid(user.getPassword())) {
			throw new UserException("Contraseña no valida");
		}
		if(!isEmailValid(user.getEmail())) {
			throw new UserException("Email no valido");
		}
		if(datauser.getByUsername(user.getUsername()) != null){
			throw new UserException("Nombre de usuario ya usado");
		}
		if(datauser.getByEmail(user.getEmail()) != null){
			throw new UserException("Nombre de usuario ya usado");
		}
	}
	
	private boolean isEmailValid(String eMail) {
		return eMail.matches("^(.+)@(.+)$");
	}
	
	private boolean isNullOrEmpty(String str) {
		return (str == null || str.isEmpty());
	} */

	public void deleteRequest(User solicitante, Integer publicationID, String mensaje, String fechaInicio) {
	}
}
