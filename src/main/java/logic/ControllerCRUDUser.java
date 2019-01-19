package logic;

import data.DataUser;
import entities.User;
import logic.exceptions.UserException;

public class ControllerCRUDUser {
	private DataUser datauser;
	public ControllerCRUDUser() {
		datauser = new DataUser();
	}
	
	public User saveUser(User user) throws Exception{
		comprobarUsuario(user);
		//comprobarProfile(user.getProfile());
		user = datauser.newUser(user);
		return user;
	}
	
//	public User retrieveUser()
	/**
	 * Se fija y el campo es valido
	 * @param field
	 * @return
	 * @throws Exception
	 */
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
		if(datauser.getUserByUsername(user.getUsername()) != null){
			throw new UserException("Nombre de usuario ya usado");
		}
		if(datauser.getUserByEmail(user.getEmail()) != null){
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
