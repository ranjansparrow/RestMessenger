package org.restmessenger.ranjan.RestMessenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;
import javax.ws.rs.core.UriInfo;

import org.restmessenger.ranjan.RestMessenger.model.Message;
import org.restmessenger.ranjan.RestMessenger.resources.beans.MessageFilterBean;
import org.restmessenger.ranjan.RestMessenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService ms = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJSONMessages(@BeanParam MessageFilterBean filterbean)

	{
		if (filterbean.getYear() > 0) {
			System.out.println("I am here");
			return ms.getAllMessagesForYear(filterbean.getYear());
		}

		if (filterbean.getStart() >= 0 && filterbean.getSize() > 0) {
			return ms.getAllMessagesPaginated(filterbean.getStart(), filterbean.getSize());
		}

		return ms.getAllMessage();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessages(@BeanParam MessageFilterBean filterbean)

	{
		if (filterbean.getYear() > 0) {
			System.out.println("I am here");
			return ms.getAllMessagesForYear(filterbean.getYear());
		}

		if (filterbean.getStart() >= 0 && filterbean.getSize() > 0) {
			return ms.getAllMessagesPaginated(filterbean.getStart(), filterbean.getSize());
		}

		return ms.getAllMessage();
	}

	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriinfo) {
		Message newMessage = ms.addMessage(message);
		String messgeId = String.valueOf(newMessage.getId());
		URI  uri  = uriinfo.getAbsolutePathBuilder().path(messgeId).build();
		 return Response.created(uri)
				 .entity(newMessage)
				 .build();
		//return ms.addMessage(message);
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
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriinfo) {
		Message message = ms.getMessage(id);
				
		
		message.addLink(getUriForSelf(uriinfo,message), "self");
		message.addLink(getUriForProfile(uriinfo, message), "profile");
		message.addLink(getUriForComments(uriinfo, message), "Comments");
		return message;
	}

	private String getUriForSelf(UriInfo uriinfo, Message message) {
		String uri = uriinfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		return uri;
	}
	
	private String getUriForProfile(UriInfo uriinfo, Message message){
		String uri = uriinfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
		return uri;
	}
	
	private String getUriForComments(UriInfo uriinfo, Message message){
		String uri = uriinfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
		return uri;
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
