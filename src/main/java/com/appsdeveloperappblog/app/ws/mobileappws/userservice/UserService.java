package com.appsdeveloperappblog.app.ws.mobileappws.userservice;

import java.util.List;

import com.appsdeveloperappblog.app.ws.mobileappws.model.User;
import com.appsdeveloperappblog.app.ws.mobileappws.model.request.UpdateUserDeatails;
import com.appsdeveloperappblog.app.ws.mobileappws.model.request.UserDetialsRequestModel;

public interface UserService {

	User create(UserDetialsRequestModel userDetails);
	
	User update(String userId, UpdateUserDeatails userUpdate);
	
	boolean delete(String id);
	
	User getUser(String id);
	
	List<User> getAllUsers();
}
