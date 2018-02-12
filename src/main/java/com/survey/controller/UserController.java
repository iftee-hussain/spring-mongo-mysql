package com.survey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.model.User;
import com.survey.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * This method return all the users
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/users")
    public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		userService.findAll().forEach(userList::add);
        return userList;
    }

	/**
	 * This method creates a new User object
	 * @param user
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/users")
    public User save(@RequestBody User user) {
		userService.save(user);
		return user;
    }
	/**
	 * This method updates a User object
	 * @param id
	 * @param user
	 * @return
	 */
	 @RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
	    public User update(@PathVariable Long id, @RequestBody User user) {
		 User _user= userService.getById(id);
	        if(user.getFirstName() != null)
	        	_user.setFirstName(user.getFirstName());
	        if(user.getLastName() != null)
	        	_user.setLastName(user.getLastName());
	       userService.save(_user);
	        return _user;
	    }
	 /**
	  * This method deletes a User object
	  * @param id
	  * @return
	  */
	 @RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
	    public String delete(@PathVariable Long id) {
		 User user = userService.getById(id);
		 userService.delete(user);

	        return "Survey Rating Deleted";
	    }
}
