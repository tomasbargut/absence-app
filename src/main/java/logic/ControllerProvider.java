package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

import data.DataProvider;
import entities.Provider;
import logic.exceptions.ProviderException;
import utils.Utils;

public class ControllerProvider {
	private DataProvider dataProvider;
	private final long EDAD_MAX = 18*365*1000;

	public ControllerProvider() {
		dataProvider = new DataProvider();
	}
	
	public void save(Provider provider) throws Exception{
		long ahora = new Date().getTime();
		long nacimiento = new SimpleDateFormat(Utils.DATE_FORMAT).parse(provider.getBirthdate()).getTime();
		if(ahora - nacimiento  < EDAD_MAX) {
			throw new ProviderException("El usuario no es mayor de edad");
		}
		if(dataProvider.get(provider.getUserID()) != null) {
			throw new ProviderException("El wachin ya es proveedor");
		}
		this.dataProvider.save(provider);
	}
}
