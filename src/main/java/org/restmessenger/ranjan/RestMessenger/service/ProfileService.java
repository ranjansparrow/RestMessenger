package org.restmessenger.ranjan.RestMessenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restmessenger.ranjan.RestMessenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = new HashMap<>();

	public ProfileService() {
		Profile p1 = new Profile(1, "David", "Ranjan", "Sapkota");
		Profile p2 = new Profile(2, "Ricky", "Jack", "Sparrow");
		profiles.put("David", p1);
		profiles.put("Ricky", p2);
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile> (profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profileName) {
		profileName.setId(profiles.size() + 1);
		profiles.put(profileName.getProfileName(), profileName);
		return profileName;
	}

	public Profile updateProfile(Profile profileName) {
		if (profileName.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profileName.getProfileName(), profileName);
		return profileName;
	}
	
	public Profile deleteProfile(String profileName){
		return profiles.remove(profileName);
		
	}
}
