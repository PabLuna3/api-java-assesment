package com.api.assessment.pack.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private UserService userService; //= new UserService("pablo@solera", "haSvd12863tx");
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/api/login")
	public boolean Login(String username, String password) {
		
		return userService.ValidateLogin(username, password);
	
	}
	@GetMapping("/api/users/{username}")
	public User getUser(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	@GetMapping("/api/users")
	public User getUser() {
		return new User("Antonio", "Antonio");
	}
	
	
}
