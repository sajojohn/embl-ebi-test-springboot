package com.embl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embl.exceptions.PersonAlreadyExistsException;
import com.embl.exceptions.PersonNotFoundException;
import com.embl.model.Person;
import com.embl.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person create(String firstName, String lastName, String country) {
		return personRepository.save(new Person(firstName, lastName, country));
	}
	
	public Person create (Person person) throws PersonAlreadyExistsException {
		if(isExists(person)) {
			throw new PersonAlreadyExistsException("Person with given first name and last name already exists");
		}
		Person p = personRepository.save(person);
		return p;	
	}

	public List<Person> getAll() {
		return personRepository.findAll();
	}
	
	public Person getById(String id) throws PersonNotFoundException{
		Optional<Person>  person = personRepository.findByObjectId(id);
		System.out.println("searching person byy id "+ person);
		if(person.isPresent()) {
			return person.get();
		}
		return null;
		//throw new PersonNotFoundException("Could not person object");
		
	}
	
	public Person getByLastName(String lastName) throws PersonNotFoundException {
		Person p = personRepository.findByLastName(lastName);
		if(p == null) {
			throw new PersonNotFoundException("Could not find person with name "+ lastName);
		}
		return p;
	}

	public Person getByFirstName(String firstName) throws PersonNotFoundException {
		Person person =  personRepository.findByFirstName(firstName);
		if(person == null) {
			throw new PersonNotFoundException("Could not find person with name "+ firstName);
		}
		return person;
	}

	public Person update(String id, Person pPerson) throws PersonNotFoundException {
		Optional<Person> p = personRepository.findByObjectId(id);

		if (p.isPresent()) {
			Person person = p.get();
			person.setFirstName(pPerson.getFirstName());
			person.setLastName(pPerson.getLastName());
			person.setCountry(pPerson.getCountry());
			return personRepository.save(person);
		} 
		throw new PersonNotFoundException("Could not update record");
	}
	
	public String deleteById(String id) {
		Optional<Person> toDelete = personRepository.findByObjectId(id);
		
		if(toDelete.isPresent()) {
			System.out.println("person found to delete" + toDelete.get());
			
//			toDelete = null;
//			 personRepository.delete(toDelete.get());			
//			 personRepository.delete(toDelete.get());
			personRepository.deletePersonById(id);
			 
			 return id;
		}
		System.out.println("could not find person to delete");
		return null;
	}
	
	public void deleteAll() {
		personRepository.deleteAll();
	}
	
	public void deleteByFirstName(String firstName) {
		System.out.println("deleting by first name "+ firstName);
		Person person = personRepository.findByFirstName(firstName);
		System.out.println("person found "+ person);
		personRepository.delete(person);
		
	}
	
	
	private boolean isExists(Person person) {
		Person p = personRepository.findByName(person.getFirstName(), person.getLastName());
		return false;
	}
	
	

}
