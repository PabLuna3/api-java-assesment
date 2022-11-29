package customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import company.Company;
import contact.Contact;

public class Customer {


	private String name;
	private String email;
	private Company company;
	private boolean isCustomer;
	private List<Contact> contacts = new ArrayList<>();
	
	
	
	public Customer(String name, String email, String nameCompany, String position, String date, String procedure,
			String description) {
		super();
		this.name = name;
		this.email = email;
		this.company = new Company(nameCompany, position);
		this.isCustomer = false;
		this.contacts.add(new Contact(date, procedure, description));
	}
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public void addContact(String date, String procedure, String description) {
		contacts.add(new Contact(date,procedure,description));
	}
	public int numberOfContacts() {
		return contacts.size();
	}
}
