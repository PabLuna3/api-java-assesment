package customer;

import java.util.Date;
import java.util.List;

import company.Company;
import contact.Contact;

public class Customer {


	private String name;
	private String email;
	private Company company;
	private boolean isCustomer;
	private List<Contact> contacts;
	
	
	
	public Customer(String name, String email, String nameCompany, String position, Date date, String procedure,
			String description) {
		super();
		this.name = name;
		this.email = email;
		this.company = new Company(nameCompany, position);
		this.isCustomer = false;
		this.contacts.add(new Contact(date, procedure, description));
	}
}
