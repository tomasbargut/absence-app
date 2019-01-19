package logic;

import java.util.ArrayList;

import data.DataChat;
import data.DataProvision;
import data.DataProvisionRequest;
import data.DataUser;
import entities.ProvisionRequest;
import entities.Chat;
import entities.Provider;

public class ControllerCRUDContact {

	// used for registering new provisionRequest with associated chat
	private DataProvisionRequest data_request;
	private DataChat data_chat;
	// used for provisionRequest building
	private DataProvision data_provision;
	private DataUser data_user;
	private DataService data_service;

	public ControllerCRUDContact() {
		data_request = new DataProvisionRequest();
		data_chat = new DataChat();
	}

	public ProvisionRequest saveContact(ProvisionRequest userProvisionRequest, Chat chat) throws Exception {
		ProvisionRequest provisionRequestWithID = data_request.saveProvisionRequest(userProvisionRequest);
		data_chat.saveChat(chat);
		return provisionRequestWithID;
		// TODO notify the provider via DB? how to do it via APP?
	}

	public ArrayList<ProvisionRequest> getAllContacts(Provider provider) throws Exception {
		return data_request.getAllProvisionRequest(provider);
		// TODO deberia llamar al DAO para recuperar la tabla de la DB y en base a dicha
		// tabla llamar a los DAO correspondientes a cada ID contenido en la primer
		// tabla (request) para construir la entidad completa provisionRequest?
	}
}
