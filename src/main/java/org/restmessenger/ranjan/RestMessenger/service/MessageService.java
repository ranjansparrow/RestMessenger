package org.restmessenger.ranjan.RestMessenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restmessenger.ranjan.RestMessenger.database.DatabaseClass;
import org.restmessenger.ranjan.RestMessenger.exception.DataNotFoundException;
import org.restmessenger.ranjan.RestMessenger.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = DatabaseClass.getMessage();
	
	
	public MessageService(){
		Message m1 = new Message(1, "Rest", "Ranjan");
		Message m2 = new Message(2, "Hellow", "Ranjan");
		messages.put(1l, m1);
		messages.put(2l, m2);
	}
	
	public List<Message> getAllMessage(){		
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		
		return list.subList(start, size);
	}
	
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with id " + id  + "Not found");
		}
		return message;
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
