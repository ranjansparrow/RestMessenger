package org.restmessenger.ranjan.RestMessenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.restmessenger.ranjan.RestMessenger.model.Message;
import org.restmessenger.ranjan.RestMessenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService ms = new MessageService();

	@GET
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size)

	{
		System.out.println("Year is " + year);
		System.out.println("start is " + start);
		/*
		 * @QueryParam("start") int start,
		 * 
		 * @QueryParam("size") int size
		 */

		System.out.println("size is " + size);

		if (year > 0) {
			System.out.println("I am here");
			return ms.getAllMessagesForYear(year);
		}

		if (start >= 0 && size > 0) {
			return ms.getAllMessagesPaginated(start, size);
		}

		return ms.getAllMessage();
	}

	@POST
	public Message addMessage(Message message) {
		return ms.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return ms.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		ms.removeMessage(id);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return ms.getMessage(id);
	}
}
