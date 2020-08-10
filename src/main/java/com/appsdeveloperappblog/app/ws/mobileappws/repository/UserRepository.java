package com.appsdeveloperappblog.app.ws.mobileappws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperappblog.app.ws.mobileappws.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
