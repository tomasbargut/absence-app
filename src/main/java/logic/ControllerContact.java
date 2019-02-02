package logic;

import java.util.ArrayList;

import data.DataRequest;
import data.DataService;
import data.DataUser;
import entities.Provider;
import entities.Request;

public class ControllerContact {
	private DataRequest data_request;
	private DataUser data_user;
	private DataService data_service;

	public ControllerContact() {
		data_request = new DataRequest();
	}

	public void save(Request request) throws Exception {
		data_request.save(request);
	}

	public ArrayList<Request> getAllContacts() throws Exception {
		return data_request.all();
	}
}
