package com.api.assessment.pack.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<User> getUser(@PathVariable String username) {
		User tempUser = userService.getUser(username);
		if(tempUser != null) {
			return new ResponseEntity <>(tempUser, HttpStatus.OK);
		}
		return new ResponseEntity <>(HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/api/users/{username}/opportunities")
	public List<Customer> getOpportunities(@PathVariable String username){
		return userService.getOpportunities(username);
	}
	
	@GetMapping("/api/users/{username}/customers")
	public List<Customer> getClients(@PathVariable String username){
		return userService.getClients(username);
	}
	@PutMapping("/api/users/{username}/update/{emailCustomer}")
	public boolean updateOpportunity(@PathVariable String username, @PathVariable String emailCustomer) {
		userService.turnOpportunityIntoClient(username, emailCustomer);
		return true;
	}
	@GetMapping("/api/users/{username}/{emailCustomer}")
	public Customer getCustomer(@PathVariable String username, @PathVariable String emailCustomer) {
		return userService.getCustomer(username, emailCustomer);	
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
	
	
	@DeleteMapping("/api/users/{username}/delete/{emailCustomer}")
	public boolean deleteCustomer(@PathVariable String username, @PathVariable String emailCustomer) {
		return userService.deleteCustomer(username, emailCustomer);
	}
	
	@DeleteMapping("/api/users/{username}/delete-contact/{emailCustomer}")
	public boolean deleteContact(@PathVariable String username, @PathVariable String emailCustomer, @RequestBody Contact contact) {
		return userService.deleteContact(username, emailCustomer, contact);
	}
	
}
