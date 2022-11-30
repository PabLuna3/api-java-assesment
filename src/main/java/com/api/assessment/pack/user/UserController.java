package com.api.assessment.pack.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.assessment.pack.contact.Contact;
import com.api.assessment.pack.customer.Customer;

@RestController
public class UserController {

	private UserService userService; //= new UserService("pablo@solera", "haSvd12863tx");
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/api/login")
	public boolean Login(String username, String password) {
		
		return userService.ValidateLogin(username, password);
	
	}
	@GetMapping("/api/users/{username}")
	public User getUser(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	@PostMapping(path="/api/users/{username}/{email}/add-contact")
	public boolean addContact(@PathVariable String username, @PathVariable String email, @RequestBody Contact contact) {
		if(userService.getUser(username) == null) {
			return false;
		}
		userService.addContact(username, email, contact.getDate(), contact.getProcedure(), contact.getDescription());
		return true;
		
		
	}
	@PostMapping(path="/api/users/{username}/add-customer")
	public boolean addCustomer(@PathVariable String username, @RequestBody Customer customer) {
		if(userService.getUser(username) == null) {
			return false;
		}
		userService.addCustomer(username, customer.getName(), customer.getEmail(), customer.getCompany().getName(),
				customer.getCompany().getPosition(), customer.getContacts().get(0).getDate(), customer.getContacts().get(0).getProcedure(),
				customer.getContacts().get(0).getDescription());
		//System.out.println(customer);
		return true;
	}
	
	
}
