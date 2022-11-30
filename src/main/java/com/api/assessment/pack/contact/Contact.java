package com.api.assessment.pack.contact;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Contact {

	
	private String date;
	private String procedure;
	private String description;
	public Contact(String date, String procedure, String description) {
		super();
		this.date = date;
		this.procedure = procedure;
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "Contacto : " + date + ", " + procedure + ", " + description; 
	}
}
