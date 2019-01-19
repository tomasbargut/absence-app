package entities;

import java.time.LocalDateTime;

public class ProvisionRequest {
	private int requestID;
	private User user;
	private Provision provision;
	private Provider provider;
	private LocalDateTime requestDate;
	private Chat chat;
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Provision getProvision() {
		return provision;
	}
	public void setProvision(Provision provision) {
		this.provision = provision;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public LocalDateTime getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public ProvisionRequest(User user, Provision provision, Provider provider, LocalDateTime requestDate, Chat chat) {
		super();
		this.setUser(user);
		this.setProvision(provision);
		this.setProvider(provider);
		this.setRequestDate(requestDate);
		this.setChat(chat);
	}
		
	public ProvisionRequest() {
		// TODO Auto-generated constructor stub
	}
}
