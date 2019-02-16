package logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.DataProvider;
import entities.Provider;
import logic.exceptions.ProviderException;
import utils.Utils;

public class ControllerProvider {
	private DataProvider dataProvider;
	private final long EDAD_MAX = 18 * 365 * 1000;

	public ControllerProvider() {
		dataProvider = new DataProvider();
	}

	public boolean save(Provider provider) throws ProviderException{
		long ahora = new Date().getTime();
		long nacimiento;
		try {
			nacimiento = new SimpleDateFormat(Utils.DATE_FORMAT).parse(provider.getBirthdate()).getTime();
		} catch (ParseException e) {
			throw new ProviderException("Fecha imparseable");
		}
		if(ahora - nacimiento  < EDAD_MAX) {
			throw new ProviderException("El usuario no es mayor de edad");
		}
		if(dataProvider.get(provider.getUserID()) != null) {
			throw new ProviderException("El wachin ya es proveedor");
		}
		provider.setPrestige(0);
		return dataProvider.save(provider);
	}
}
