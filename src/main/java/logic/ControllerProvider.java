package logic;

import data.DataProvider;
import entities.Provider;
import logic.exceptions.ProviderException;

public class ControllerProvider {
	private DataProvider data;
	private final long EDAD_MAX = 18 * 365 * 24 * 1000;
	
	public ControllerProvider() {
		data = new DataProvider();
	}
	
	public Provider save_provider(Provider provider) throws Exception{
		/*if(new SimpleDateFormat("yyyyMMdd").parse(provider.getBirthdate()).getTime() - new Date().getTime() < EDAD_MAX) {
			throw new ProviderException("no es mayo de edad");
		}*/
		Provider providerite = data.get_provider_by_id(provider.getUser_id());
		if(providerite != null) {
			throw new ProviderException("El wachin ya es proveedor");
		}
		provider = this.data.save_provider(provider);
		return provider;
	}
}
