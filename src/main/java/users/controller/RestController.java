package users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import users.models.Status;
import users.models.User;
import users.service.UserManager;

@Controller
public class RestController {
	
	private UserManager manager;
	
	@Autowired
	public RestController(UserManager manager) {
		this.manager = manager;
	}
	
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsers() {
		
		List<User> userList = null;
		try {
			userList = manager.getEntityList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	@RequestMapping(value= "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status addUser(@RequestBody User user) {
		try {
			manager.addEntity(user);			
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
		
		return new Status(1, "User added");
	}
	
	@RequestMapping(value= "/id", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("id") long id) {
		User user = null;
		try {
			user = manager.getEntityById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status deleteUser(@PathVariable("id") long id) {
		
		User user = new User(id);
		try {
			manager.deleteEntity(user);			
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
		
		return new Status(1, "User deleted");
	}
	
	@RequestMapping(value= "/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status updateUser(@PathVariable("id") long id, @RequestBody User user) {
		User oldUser = null;
		try {
			user = manager.getEntityById(id);			
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
		oldUser.setUsername(user.getUsername());
		
		try {
			manager.update(oldUser);
		} catch(Exception e) {
			return new Status(0, e.toString());
		}
		return new Status(1, "User updated");
	}	
}
