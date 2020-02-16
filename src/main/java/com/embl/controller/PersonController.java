package com.embl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.embl.exceptions.PersonAlreadyExistsException;
import com.embl.exceptions.PersonNotFoundException;
import com.embl.input.PersonInput;
import com.embl.service.PersonService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PersonController {
	@Autowired
	private PersonService personService; 
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/persons")
	public ResponseEntity<PersonInput> create(@Valid @RequestBody PersonInput person) throws PersonAlreadyExistsException {
		System.out.println("add person " + person);
		return new ResponseEntity<PersonInput>(personService.create(person), HttpStatus.CREATED);
	}
	
	@RequestMapping("/api/persons")
	public ResponseEntity<List<PersonInput>> getAll() {
		List<PersonInput> list = personService.getAll();
		return new ResponseEntity<List<PersonInput>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/{id}")
	public PersonInput getById(@PathVariable  String id) throws PersonNotFoundException {
		System.out.println("get person by id " + id);
		return personService.getById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/by-ids/{ids}")
	public List<PersonInput> getByIds(@PathVariable String ids) throws PersonNotFoundException {
		System.out.println("get person by ids " + ids);
		return personService.getByIds(ids);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/by-firstname/{firstName}")
	public List<PersonInput> getByFirstName(@PathVariable String firstName) throws PersonNotFoundException {
		System.out.println("get person by first name " + firstName);
		return personService.getByFirstName(firstName);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/by-firstname/{firstName}/by-lastname/{lastName}")
	public List<PersonInput> getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) throws PersonNotFoundException {
		System.out.println("get person by first name " + firstName);
		return personService.getByFirstNameAndLastName(firstName, lastName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/by-lastname/{lastName}")
	public List<PersonInput> getBylastName(@PathVariable String lastName) throws PersonNotFoundException {
		System.out.println("get person by last name " + lastName);
		return personService.getByFirstName(lastName);
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/api/persons/{id}")
	public ResponseEntity<PersonInput> update(@PathVariable String id, @RequestBody PersonInput person)
			throws PersonNotFoundException {
		PersonInput updated = personService.update(id, person);
		return new ResponseEntity<PersonInput>(updated, HttpStatus.OK);

	}
	@CrossOrigin(origins = "http://localhost:3000") 
	@RequestMapping(method = RequestMethod.DELETE, value = "/api/persons/{id}")
	public String deletePerson(@PathVariable String id) throws PersonNotFoundException {
		return personService.deleteById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/api/persons/deleteall")
	public void deleteAllPerson() throws PersonNotFoundException {
		personService.deleteAll();
	}
	
}
