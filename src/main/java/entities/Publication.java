package entities;

import java.sql.SQLException;

/**
 * Publication
 */
public class Publication {

    private Service service;
    private Provider provider;

    public Publication(Service service, Provider provider){
        this.service = service;
        this.provider = provider;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @return the provider
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}