package com.embl.exceptions;
public class PersonNotFoundException extends Exception{


	public PersonNotFoundException(String message) {
		super();
		this.message = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
