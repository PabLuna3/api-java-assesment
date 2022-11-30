package com.api.assessment.pack.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.assessment.pack.company.Company;
import com.api.assessment.pack.contact.Contact;



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

	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", company=" + company + ", isCustomer=" + isCustomer
				+ ", contacts=" + contacts + "]";
	}
	
}
