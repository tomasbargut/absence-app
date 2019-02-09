package logic;

import java.sql.SQLException;

import data.DataUser;
import entities.User;
import logic.exceptions.UserException;
import utils.Utils;

public class ControllerUser {
	private DataUser datauser;
	public ControllerUser() {
		datauser = new DataUser();
	}
	
	public boolean save(User user) throws UserException{
		// TODO IMPLEMENTAR HASH A PASSWORD
		comprobarUsuario(user);
		user.setAccountStatus("activo");
		user.setLastLogin(Utils.getCurrentTime());
		return datauser.save(user);
	}
	
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
	
	
	public void comprobarUsuario(User user) throws UserException {
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
	}
}
