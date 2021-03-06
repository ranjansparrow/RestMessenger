package org.restmessenger.ranjan.RestMessenger.database;

import java.util.HashMap;
import java.util.Map;

import org.restmessenger.ranjan.RestMessenger.model.Comment;
import org.restmessenger.ranjan.RestMessenger.model.Message;
import org.restmessenger.ranjan.RestMessenger.model.Profile;

public class DatabaseClass {
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	private static Map<Long,Comment> comments = new HashMap<>();
	public static Map<Long,Message> getMessage(){
		return messages;
	}
	
	public static Map<String,Profile> getProfiles(){
		return profiles;
	}
	
	public static Map<Long,Comment> getComments(){
		return comments;
	}
}
