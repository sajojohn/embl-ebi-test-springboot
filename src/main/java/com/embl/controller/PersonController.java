package com.embl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.embl.exceptions.PersonAlreadyExistsException;
import com.embl.exceptions.PersonNotFoundException;
import com.embl.model.Person;
import com.embl.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/persons") 
	public ResponseEntity<Person> create(@RequestBody Person person) throws PersonAlreadyExistsException {		
		return new ResponseEntity<Person> (personService.create(person), HttpStatus.CREATED);
	}
	
	@RequestMapping("/api/persons")
	public List<Person> getAll() {
		return personService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/api/persons/{id}")
	public Person getById(@PathVariable String id) throws PersonNotFoundException{
		return personService.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/api/persons/by-firstname/{firstName}")
	public Person getByFirstName(@PathVariable String firstName) throws PersonNotFoundException{
		return personService.getByFirstName(firstName);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/api/persons/by-lastname/{lastName}")
	public Person getBylastName(@PathVariable String lastName) throws PersonNotFoundException{
		return personService.getByFirstName(lastName);
	}
	
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/api/persons/{id}") 
	public ResponseEntity<Person>  update(@PathVariable String id, @RequestBody Person person)  throws PersonNotFoundException {
		
		Person updated = personService.update(id,person );
		return new ResponseEntity<Person> (updated, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/api/persons/{id}")
	public void deletePerson(@PathVariable String id) throws PersonNotFoundException {
		System.out.println("in the controller delete");
		personService.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/api/persons/deleteall")
	public void deleteAllPerson() throws PersonNotFoundException {
		System.out.println("in the controller delete all");
		personService.deleteAll();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/api/persons/by-firstname/{firstName}")
	public void deleteByFirstName(@PathVariable String firstName) throws PersonNotFoundException {
		System.out.println("in the controller delete by firstname");
		personService.deleteByFirstName(firstName);
	}
	
	

}
