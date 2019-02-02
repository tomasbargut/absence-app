package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

import data.DataProvider;
import entities.Provider;
import logic.exceptions.ProviderException;

public class ControllerProvider {
	private DataProvider dataProvider;
	private final long EDAD_MAX = 18*365*1000;

	public ControllerProvider() {
		dataProvider = new DataProvider();
	}
	
	public void save(Provider provider) throws Exception{
		if(new SimpleDateFormat("yyyyMMdd").parse(provider.getBirthdate()).getTime() - new Date().getTime() < EDAD_MAX) {
			throw new ProviderException("El usuario no es mayor de edad");
		}
		if(dataProvider.get(provider.getUserID()) != null) {
			throw new ProviderException("El wachin ya es proveedor");
		}
		this.dataProvider.save(provider);
	}
}
