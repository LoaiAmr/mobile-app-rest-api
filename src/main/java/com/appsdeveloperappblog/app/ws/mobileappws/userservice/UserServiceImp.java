package com.appsdeveloperappblog.app.ws.mobileappws.userservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperappblog.app.ws.mobileappws.model.User;
import com.appsdeveloperappblog.app.ws.mobileappws.model.request.UpdateUserDeatails;
import com.appsdeveloperappblog.app.ws.mobileappws.model.request.UserDetialsRequestModel;
import com.appsdeveloperappblog.app.ws.mobileappws.repository.UserRepository;
import com.appsdeveloperappblog.app.ws.mobileappws.shared.Utils;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	Map<String, User> users;

	Utils utils;

	// Constructor Based Dependency Injection...
	@Autowired
	public UserServiceImp(Utils utils) {
		this.utils = utils;
	}

	@Override
	public User create(UserDetialsRequestModel userDetails) {
		User user = new User();
		user.setEmail(userDetails.getEmail());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());

		String userId = utils.generateUserId();
		user.setUserId(userId);

		if (users == null)
			users = new HashMap<>();
		users.put(userId, user);
		return user;
	}

	@Override
	public User update(String userId, UpdateUserDeatails userUpdate) {

		if (users.containsKey(userId)) {
			User userStored = users.get(userId);
			userStored.setFirstName(userUpdate.getFirstName());
			userStored.setLastName(userUpdate.getLastName());
			return userStored;
		} else
			return null;
	}

	@Override
	public boolean delete(String id) {
		return users.remove(id, users.get(id));
	}

	@Override
	public User getUser(String id) {
		if (users.containsKey(id))
			return users.get(id);
		else
			return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = null;
		for (int i = 0; i < users.size(); i++) {
			allUsers.add(users.get(i));
		}
		return allUsers;
	}

}
