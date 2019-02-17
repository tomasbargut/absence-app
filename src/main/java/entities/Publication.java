package entities;

/**
 * Publication
 */
public class Publication {
    
    private String publicationID;
    private Service service;
    private Provider provider;

    public Publication(Service service, Provider provider) {
        this.publicationID = Integer.toString(provider.getUserID()).concat(Integer.toString(service.getServiceID())); 
        this.service = service;
        this.provider = provider;
    }

    public Publication(String id) {
        this.setPublicationID(id);
	}

    public Publication() {
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

    /**
     * @return the publicationID
     */
    public String getPublicationID() {
        return publicationID;
    }

      /**
     * @return the publicationID based on publicationID
     */
    public Integer getServiceID() {
        if(this.service != null){
            return this.service.getServiceID();
        } else if(this.publicationID != null) {                  
            return Integer.parseInt(this.publicationID.substring((this.publicationID.length()/2)));            
        } else { 
            return null;
        }
    }

      /**
     * @return the serviceID based on publicationID
     */
    public Integer getProviderID() {
        if(this.provider != null){
            return this.provider.getUserID();
        } else if(this.publicationID != null) { 
            return Integer.parseInt(this.publicationID.substring(0, (this.publicationID.length()/2)));
        } else { 
            return null;
        }
    }

    /**
     * @param publicationID the publicationID to set
     */
    public void setPublicationID(String publicationID) {
        this.publicationID = publicationID;
    }
}