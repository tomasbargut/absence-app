package logic;

import java.sql.SQLException;

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

	public Request newRequest(User solicitante, String publicationID, String mensaje, String fechaInicio)
			throws Exception {
		try {
			Publication publication = new Publication(publicationID);

			publication = datapublication.getOneByID(publication.getProviderID(), publication.getServiceID());

			if (publication != null) {
				Request solicitud = new Request(solicitante, publication.getService(), publication.getProvider(),
						fechaInicio, mensaje);
				Integer requestID = datarequest.save(solicitud);
				if (requestID != null) {
					solicitud.setRequestID(requestID);
					return solicitud;
				} else {
					throw new ContactException("Error, solicitud no registrada");
				}
			} else {
				throw new ContactException("Error, publicacion no encontrada.");
			}

		} catch (ContactException e) {
			// error la publicacion no existe
			return null;
		} catch (SQLException e) {
			// error general sql
			return null;
		}
	}

	public String deleteRequest(User solicitante, String publicationID, String mensaje, String fechaInicio,
			Integer requestIDtoDelete) {

		try {
			Publication publication = new Publication(publicationID);

			publication = datapublication.getOneByID(publication.getProviderID(), publication.getServiceID());

			if (publication != null) {
				Request solicitud = new Request(solicitante, publication.getService(), publication.getProvider(),
						fechaInicio, mensaje);
				if (requestIDtoDelete != null) {
					solicitud.setRequestID(requestIDtoDelete);
				}
				boolean deletion = datarequest.deleteRequest(solicitud);

				if (deletion == true) {
					return "La solicitud ha sido eliminada con exito.";
				} else {
					throw new ContactException("La solicitud no existe.");
				}
			} else {
				throw new ContactException("La publicacion no existe.");
			}

		} catch (ContactException e) {
			// error la publicacion no existe
			return null;
		} catch (SQLException e) {
			// error general sql
			return null;
		}
	}
}
