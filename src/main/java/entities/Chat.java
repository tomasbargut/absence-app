package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Chat {
	private ArrayList<String> chat;
	private int chatID;
	

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
	}

	public ArrayList<String> getChat() {
		return chat;
	}

	public void setChat(ArrayList<String> chat) {
		this.chat = chat;
	}
	
	//Default constructor
	public Chat() {
		super();
	}
	
	//Only used once per request to initialize a new chat 
	public Chat(int id) {
		super();
		ArrayList<String> chat = new ArrayList<String>();
		this.chat = chat;
		this.setChatID(id);
	}
	
	//Method used to add new chat lines for both user and provider
	public void addChatLine(String message, LocalDateTime messageTime, String userName) {
		String newMessage = "["+ messageTime.toString() + "] " + userName +""+ message;
		this.chat.add(newMessage);
	}
}
