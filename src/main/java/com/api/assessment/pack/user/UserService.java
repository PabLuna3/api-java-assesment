package com.api.assessment.pack.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.assessment.pack.contact.Contact;
import com.api.assessment.pack.customer.Customer;

@Component
public class UserService {

	private List<User> users = new ArrayList<>();
	
	public UserService() {
		users.add( new User("pablo@solera", "azul"));
	}
	public UserService(String username, String password) {
		users.add( new User(username, password));
	}
	
	
	public  boolean ValidateLogin(String username, String password) {
		boolean flag = false;
		for(int i = 0; i < users.size();i++) {
			if((users.get(i).getUsername().equals(username)) && (users.get(i).getPassword().equals(password))) flag = true;
		}
		
		return flag;
	}
	//We have to check all the information about the customer is added. Only field that can be left empty is "description"
	public  boolean addCustomer(String userName, String name, String email, String nameCompany, String position, String date, String procedure,
			String description) {
		boolean flag = true;
		//System.out.println("Hola desde crear usuario");
		if(name == null || name.isEmpty() || name.trim().isEmpty()) flag = false;
		if(email == null || email.isEmpty() || email.trim().isEmpty()) flag = false;
		if(nameCompany == null || nameCompany.isEmpty() || nameCompany.trim().isEmpty()) flag = false;
		if(position == null || position.isEmpty() || position.trim().isEmpty()) flag = false;
		if(date == null || date.isEmpty() || date.trim().isEmpty()) flag = false;
		if(procedure == null || procedure.isEmpty() || procedure.trim().isEmpty()) flag = false;
		if(flag) {
			for(int i = 0; i < users.size(); i++) {
				String temp = users.get(i).getUsername();
				if(temp.equals(userName)) {
					User tempUser = users.get(i);
					if (this.checkExistingEmail(tempUser, email)) {
						tempUser.getCustomers().get(i).addContact(date, procedure, description);
					}else {
						Customer tempCustomer = new Customer(name,email,nameCompany, position, date, procedure, description);
						tempUser.setCustomers(new Customer(name,email,nameCompany, position, date, procedure, description));
						
					}
					users.set(i, tempUser);
				}
			}
		}
		
		return flag;
	}
	public  List<Customer> getCustomers(String userName){
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(userName)) {
				return users.get(i).getCustomers();
			}
		}
		return null;
	}
	public  int numberOfCustomers(String userName) {
		int counter = 0;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(userName)) {
				counter = users.get(i).getCustomers().size();
			}
		}
		return counter;
	}
	
	public boolean checkExistingEmail(User user, String email) {
		boolean flag = false;
		for(int i = 0; i < user.getCustomers().size(); i++) {
			if(user.getCustomers().get(i).getEmail().equals(email)) flag = true;
		}
		
		return flag;
	}
	
	public User getUser(String userName){
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(userName)) return users.get(i);
		}
		return null;
	}
	
	public boolean addContact(String username,String email,  String date, String procedure, String description) {
		boolean flag = true;
		if(date == null || date.isEmpty() || date.trim().isEmpty()) flag = false;
		if(procedure == null || procedure.isEmpty() || procedure.trim().isEmpty()) flag = false;
		
		Customer tempCustomer = getCustomer(username,email);
		if(tempCustomer != null) {
			tempCustomer.getContacts().add(new Contact(date, procedure, description));
		}else {
			flag = false;
		}
		
		return flag;
	}
	
	public List<Customer> getOpportunities(String username){
		User tempUser = getUser(username);
		List<Customer> customers = new ArrayList<>();
		
		for(int i = 0; i < tempUser.getCustomers().size(); i++) {
			if(!tempUser.getCustomers().get(i).isCustomer()) customers.add(tempUser.getCustomers().get(i));
		}
		
		return customers;
	}
	public List<Customer> getClients(String username){
		User tempUser = getUser(username);
		List<Customer> clients = new ArrayList<>();
		
		for(int i = 0; i < tempUser.getCustomers().size(); i++) {
			if(tempUser.getCustomers().get(i).isCustomer()) clients.add(tempUser.getCustomers().get(i));
		}
		
		return clients;
	}
	public Customer getCustomer(String username, String emailCustomer) {
		User tempUser = getUser(username);
		
		for(int i = 0; i < tempUser.getCustomers().size(); i++) {
			if(tempUser.getCustomers().get(i).getEmail().equals(emailCustomer)) return tempUser.getCustomers().get(i);
		}
		return null;
	}
	public boolean turnOpportunityIntoClient(String username, String emailCustomer) {
		Customer tempCustomer = getCustomer(username, emailCustomer);
		if(tempCustomer == null) return false;
		tempCustomer.setCustomer(true);
		return true;
	}
	
	public boolean deleteCustomer(String username, String emailCustomer) {
		User tempUser = getUser(username);
		for(int i = 0; i < tempUser.getCustomers().size(); i++) {
			if(tempUser.getCustomers().get(i).getEmail().equals(emailCustomer)) {
				tempUser.getCustomers().remove(i);
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteContact(String username, String emailCustomer, Contact contact) {
		Customer tempCustomer = getCustomer(username, emailCustomer);
		if(tempCustomer != null) {
			for(int i = 0; i < tempCustomer.getContacts().size(); i++) {
				if(tempCustomer.getContacts().get(i).equals(contact)) {
					tempCustomer.getContacts().remove(i);
					if(tempCustomer.getContacts().size() == 0) {
						deleteCustomer(username, emailCustomer);
					}
					return true;
				}
				
			}
		}
		return false;
	}
}
