package user;

import java.util.ArrayList;
import java.util.List;

import customer.Customer;

public class User {

	
	private String username;
	private String password;
	private List<Customer> customers;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.customers = new ArrayList<>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
}
