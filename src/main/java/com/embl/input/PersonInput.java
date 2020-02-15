package com.embl.input;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonInput {

	String id;
	@NotNull()
	@NotEmpty()	
	private String first_name;
	
	@NotNull()
	@NotEmpty()	
	private String last_name;
	
	
	@Min(1)	
	private Integer age;
	
	private String favourite_colour;
	
	private String hobby;
	

	public PersonInput() {
		
	}
	public PersonInput(@NotNull @NotEmpty String first_name, @NotNull @NotEmpty String last_name, @Min(1) Integer age) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
	}


	
	
	public PersonInput(String id, @NotNull @NotEmpty String first_name, @NotNull @NotEmpty String last_name,
			@Min(1) Integer age, String favourite_colour, String hobby) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
		this.hobby = hobby;
	}
	public PersonInput( @NotNull @NotEmpty String first_name, @NotNull @NotEmpty String last_name,
			@Min(1) Integer age, String favourite_colour, String hobby) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
		this.hobby = hobby;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Integer getAge() {
		return age;
	}
	public String getFavourite_colour() {
		return favourite_colour;
	}
	public void setFavourite_colour(String favourite_colour) {
		this.favourite_colour = favourite_colour;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "PersonInput [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", age=" + age
				+ ", favourite_colour=" + favourite_colour + ", hobby=" + hobby + "]";
	}
	
	
	
}
