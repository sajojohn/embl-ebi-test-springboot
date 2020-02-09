package com.embl.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {
	
	@Id
	String Id;
	String firstName;
	String lastName;
	String country;
	
	public Person(String firstName, String lastName, String country) {
		super();		
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Person [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ "]";
	}
	
	

}
