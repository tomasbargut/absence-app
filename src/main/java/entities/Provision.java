package entities;

/**
 * @author ferna
 *
 */
public class Provision {

	private int provisionID;
	private Provider providingUser;
	private Service providedService;

	public int getProvisionID() {
		return provisionID;
	}

	public void setProvisionID(int provisionID) {
		this.provisionID = provisionID;
	}

	public Provider getProvidingUser() {
		return providingUser;
	}

	public void setProvidingUser(Provider providingUser) {
		this.providingUser = providingUser;
	}

	public Service getProvidedService() {
		return providedService;
	}

	public void setProvidedService(Service providedService) {
		this.providedService = providedService;
	}

	public Provision(int provisionID, Provider providingUser, Service providedService) {
		super();
		this.provisionID = provisionID;
		this.providingUser = providingUser;
		this.providedService = providedService;
	}

}