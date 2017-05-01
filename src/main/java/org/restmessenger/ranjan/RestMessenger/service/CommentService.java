package org.restmessenger.ranjan.RestMessenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.restmessenger.ranjan.RestMessenger.database.DatabaseClass;
import org.restmessenger.ranjan.RestMessenger.model.Comment;
import org.restmessenger.ranjan.RestMessenger.model.ErrorMessage;
import org.restmessenger.ranjan.RestMessenger.model.Message;

public class CommentService {
	private Map<Long,Message> messages = DatabaseClass.getMessage();
	private Map<Long,Comment> comments = DatabaseClass.getComments();
	
	public CommentService(){
		Comment c1 = new Comment(1, "I like you", "Ranjan");
		Comment c2 = new Comment(2, "Its nice", "Sparrow");
		comments.put(1L, c1);
		comments.put(2L, c2);
	}
	
	public List<Comment> getAllComments(long messageId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId,long commentId){
		ErrorMessage errorMessage = new ErrorMessage("Not Found",
				500, "http://www.ranjansapkota.com.np");
		Response response = Response.status(Status.NOT_FOUND).
								entity(errorMessage).
								build();
		Message message = messages.get(messageId);
		if(message == null){
			throw new WebApplicationException(response);
		}
		Comment comment = comments.get(commentId);
		if(comment == null){
			throw new WebApplicationException(response);
		}
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comment.getId()<=0){
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
