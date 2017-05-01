package org.restmessenger.ranjan.RestMessenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.restmessenger.ranjan.RestMessenger.database.DatabaseClass;
import org.restmessenger.ranjan.RestMessenger.model.Comment;
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
