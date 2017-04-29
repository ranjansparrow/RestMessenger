package org.restmessenger.ranjan.RestMessenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restmessenger.ranjan.RestMessenger.database.DatabaseClass;
import org.restmessenger.ranjan.RestMessenger.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = DatabaseClass.getMessage();
	
	
	public List<Message> getAllMessage(){
		Message m1 = new Message(1, "Rest", "Ranjan");
		Message m2 = new Message(2, "Hellow", "Ranjan");
		messages.put(1l, m1);
		messages.put(2l, m2);
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <=0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
