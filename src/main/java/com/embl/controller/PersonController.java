package com.embl.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.embl.model.Person;
import com.embl.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;


	
	@RequestMapping(method = RequestMethod.POST, value = "/api/persons")
	public ResponseEntity<Person> create(@Valid @RequestBody PersonInput person) throws PersonAlreadyExistsException {
		System.out.println("add person " + person);
		return new ResponseEntity<Person>(personService.create(person), HttpStatus.CREATED);
	}

	@RequestMapping("/api/persons")
	public List<Person> getAll() {
		System.out.println("get all persons ");
		return personService.getAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/{id}")
	public Person getById(@PathVariable  String id) throws PersonNotFoundException {
		System.out.println("get person by id " + id);
		return personService.getById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/by-ids/{ids}")
	public List<Person> getByIds(@PathVariable String ids) throws PersonNotFoundException {
		System.out.println("get person by ids " + ids);
		return personService.getByIds(ids);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/by-firstname/{firstName}")
	public Person getByFirstName(@PathVariable String firstName) throws PersonNotFoundException {
		System.out.println("get person by first name " + firstName);
		return personService.getByFirstName(firstName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/persons/by-lastname/{lastName}")
	public Person getBylastName(@PathVariable String lastName) throws PersonNotFoundException {
		System.out.println("get person by last name " + lastName);
		return personService.getByFirstName(lastName);
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/api/persons/{id}")
	public ResponseEntity<Person> update(@PathVariable String id, @RequestBody Person person)
			throws PersonNotFoundException {
		System.out.println("update person by id " + id + " with " + person);
		Person updated = personService.update(id, person);
		return new ResponseEntity<Person>(updated, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/api/persons/{id}")
	public void deletePerson(@PathVariable String id) throws PersonNotFoundException {
		System.out.println("delete person by id" + id);
		personService.deleteById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/api/persons/deleteall")
	public void deleteAllPerson() throws PersonNotFoundException {
		System.out.println("delete all persons");
		personService.deleteAll();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/api/persons/by-firstname/{firstName}")
	public void deleteByFirstName(@PathVariable String firstName) throws PersonNotFoundException {
		System.out.println("delete person by first name " + firstName);
		personService.deleteByFirstName(firstName);
	}

}
