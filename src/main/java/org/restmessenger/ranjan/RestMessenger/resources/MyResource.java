package org.restmessenger.ranjan.RestMessenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restmessenger.ranjan.RestMessenger.model.Message;
import org.restmessenger.ranjan.RestMessenger.service.MessageService;

@Path("/messages")
public class MyResource {
	
	
	MessageService ms = new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	
	
	public List<Message> getMessages(){
		return ms.getAllMessage();
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("messageId") long id){
		return ms.getMessage(id);
	}
}
