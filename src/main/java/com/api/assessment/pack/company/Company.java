package com.api.assessment.pack.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Company {

	
	
	private String name;
	private String position;
	public Company(String name, String position) {
		super();
		this.name = name;
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
