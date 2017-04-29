package org.restmessenger.ranjan.RestMessenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restmessenger.ranjan.RestMessenger.model.Message;
import org.restmessenger.ranjan.RestMessenger.service.MessageService;

@Path("/messages")
public class MyResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	
	
	public List<Message> getMessage(){
		MessageService ms = new MessageService();
		return ms.getAllMessage();
	}
}
