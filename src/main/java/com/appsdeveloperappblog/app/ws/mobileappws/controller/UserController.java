package com.appsdeveloperappblog.app.ws.mobileappws.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.appsdeveloperappblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperappblog.app.ws.mobileappws.model.User;
import com.appsdeveloperappblog.app.ws.mobileappws.model.request.UpdateUserDeatails;
import com.appsdeveloperappblog.app.ws.mobileappws.model.request.UserDetialsRequestModel;
import com.appsdeveloperappblog.app.ws.mobileappws.userservice.UserService;

@RestController // Be able to recive http request and send url path
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

	Map<String, User> users;

	@Autowired
	UserService userService;

	@GetMapping
	public String getUsersWithParamters(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit,
			@RequestParam(value = "sort", defaultValue = "default") String sort) {
		return "Get users called with page = " + page + " And limit = " + limit + " And sort = " + sort;
	}

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<User>> getUsers() {
//		List<User> users = userService.getAllUsers();
//		return new ResponseEntity<>(users, HttpStatus.OK);
//	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUser(@PathVariable String userId) {

		User user = userService.getUser(userId);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> post(@Valid @RequestBody UserDetialsRequestModel userDetails) {

		User user = userService.create(userDetails);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> put(@PathVariable String userId, @Valid @RequestBody UpdateUserDeatails userUpdate) {
		if (userService.update(userId, userUpdate) == null)
			throw new UserServiceException("A User can't be updated...");
		else {
			User user = userService.update(userId, userUpdate);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<User> delete(@PathVariable String id) {
		if (userService.delete(id))
			return ResponseEntity.noContent().build();
		else
			throw new UserServiceException("A User can't be deleted...");
	}

}
