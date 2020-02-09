package com.embl.input;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonInput {

	@NotNull()
	@NotEmpty()
	@Max(30)
	private String firstName;
	
	@NotNull()
	@NotEmpty()
	@Max(30)
	private String lastName;
	
	@NotNull()
	@NotEmpty()
	@Max(30)
	private String country;

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
		return "PersonInput [firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + "]";
	}
	
	
}
