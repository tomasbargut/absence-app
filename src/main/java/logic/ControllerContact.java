package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.*;
import entities.*;
import logic.exceptions.*;

public class ControllerContact {
	private DataRequest datarequest;
	private DataPublication datapublication;

	public ControllerContact() {
		datarequest = new DataRequest();
		datapublication = new DataPublication();
	}

	/**
	 * 
	 * @param solicitante
	 * @param publicationID
	 * @param mensaje
	 * @param fechaInicio
	 * @return Saved Request (Full DTO) or Error message
	 * @throws Exception
	 */
	public Request newRequest(User solicitante, String publicationID, String mensaje, String fechaInicio)
			throws ContactException {
		try {
			Request requestToSave = null;

			requestToSave = getRequestIfExists(solicitante, publicationID);
			if (requestToSave.getRequestID() == 0) {
				Integer requestID = datarequest.save(requestToSave);
				if (requestID != null) {
					requestToSave.setRequestID(requestID);

					// si fue registrada, la retorna entera con ID para mostrar datos
					// confirmatorios.
					return requestToSave;
				} else {
					throw new ContactException("Error, solicitud no registrada");
				}
			} else {
				throw new ContactException("Error, publicacion no encontrada.");
			}
		} catch (SQLException e) {
			// error general sql
			return null;
		} catch (Exception e) {
			// error general sql
			return null;
		}
	}

	/**
	 * 
	 * @param solicitante
	 * @param publicationID
	 * @param requestIDtoDelete
	 * @return Deletion Status String
	 * @throws Exception
	 */
	public String deleteRequest(User solicitante, String publicationID) throws Exception {
		// Este metodo no borra datos, solo la busca en DB y actualiza su estado a
		// "cancelada".
		// RN: No podra "eliminarla" si: 1) no existe la request. 2) La request fue
		// vista, respondida, moderada, inhibida, borrada.
		// (Esto ultimo asegura que la publicacion sea la ultima que haya hecho el
		// usuario)
		try {
			Request requestToDelete = null;

			requestToDelete = getRequestIfExists(solicitante, publicationID);
			if (requestToDelete.getRequestID() != 0) {
				boolean deletion = datarequest.delete(requestToDelete);
				if (deletion == true) {
					return "La solicitud ha sido eliminada con exito.";
				} else {
					throw new ContactException("La solicitud no existe.");
				}
			}
		} catch (Exception ex) {
			return null;
		}
		return "Si ves esto, algo salio mal.";
	}

	/**
	 * 
	 * @param solicitante
	 * @param publicationID
	 * @return Request with/without ID (if exists/notExists in DB)
	 * @throws Exception
	 */
	public Request getRequestIfExists(User solicitante, String publicationID) throws Exception {
		// Este metodo realiza ingenieria inversa para reconstruir una Request, para
		// luego validar su existencia contra la DB.

		// Crea instancia publicacion con solo ID
		Publication publication = new Publication(publicationID);
		// Obtiene todos los datos enlazados de la publicacion y la reescribe
		publication = datapublication.getOneByID(publication.getProviderID(), publication.getServiceID());
		// Si la publicacion fue obtenida con exito, no sera nula y genero una request a
		// partir de ella
		// RN: Esto es posible solo porque no puede haber mas de 1 request de un usuario
		// a una publicacion activa.
		// busco en la DB su ID y lo retorno, si no la encuentro, no existia en DB.
		if (publication != null) {
			Request solicitud = new Request(solicitante, publication.getService(), publication.getProvider(), null,
					null);
			Integer requestid = datarequest.getIdByAttr(solicitud);
			if (requestid != 0) {
				solicitud.setRequestID(requestid);
				return solicitud;
			} else {
				return solicitud;
			}
		} else {
			throw new ContactException("La publicacion ya no existe.");
		}
	}

	public boolean update(Request request) throws ContactException {

		return datarequest.update(request);
	}

	public ArrayList<Request> getAllRequestsByProvider(User solicitante) {

		return datarequest.getAllRequestsByProvider(solicitante);
	}
}
