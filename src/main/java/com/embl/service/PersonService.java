package com.embl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embl.exceptions.PersonAlreadyExistsException;
import com.embl.exceptions.PersonNotFoundException;
import com.embl.input.PersonInput;
import com.embl.model.Person;
import com.embl.repository.PersonRepository;


@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public PersonInput create(String firstName, String lastName, Integer age) {
		return copy(personRepository.save(new Person(firstName, lastName, age, null, null)));
	}

	public PersonInput create(PersonInput person) throws PersonAlreadyExistsException {
		
		if (isExists(person)) {
			throw new PersonAlreadyExistsException("Person with given first name and last name already exists");
		}
		Person p = new Person(person.getFirst_name(), person.getLast_name(), person.getAge(), person.getFavourite_colour(), person.getHobby());
		System.out.println("person created " + p);
		return copy(personRepository.save(p));
	}

	public List<PersonInput> getAll() {
		return copy(personRepository.findAll());
	}

	public Person getById(String id) throws PersonNotFoundException {
		Optional<Person> person = personRepository.findByObjectId(id);
		System.out.println("searching person byy id " + person);
		if (person.isPresent()) {
			return person.get();
		}
		throw new PersonNotFoundException("Could not person object");
	}

	public List<Person> getByIds(String ids) throws PersonNotFoundException {
		String[] idsToSearch = ids.split(",");
		List<Person> personList = personRepository.findByObjectIds(idsToSearch);
		return personList;

	}

	public Person getByLastName(String lastName) throws PersonNotFoundException {
		Optional<Person> person = personRepository.findByLastNameIgnoreCase(lastName);
		if (person.isPresent()) {
			return person.get();
		}
		throw new PersonNotFoundException("Could not find person with name " + lastName);
	}

	public Person getByFirstName(String firstName) throws PersonNotFoundException {
		Optional<Person> person = personRepository.findByFirstNameIgnoreCase(firstName);
		if (person.isPresent()) {
			return person.get();
		}
		throw new PersonNotFoundException("Could not find person with name " + firstName);

	}

	public Person update(String id, PersonInput pPerson) throws PersonNotFoundException {
		Optional<Person> toUpdate = personRepository.findByObjectId(id);

		if (toUpdate.isPresent()) {
			Person person = toUpdate.get();
			person.setFirstName(pPerson.getFirst_name());
			person.setLastName(pPerson.getLast_name());
			person.setAge(pPerson.getAge());
			return personRepository.save(person);
		}
		throw new PersonNotFoundException("Could not update record");
	}

	public String deleteById(String id) throws PersonNotFoundException {
		Optional<Person> toDelete = personRepository.findByObjectId(id);

		if (toDelete.isPresent()) {
			personRepository.deletePersonById(id);
			return id;
		}
		throw new PersonNotFoundException("Person with given id does not exists");
	}

	public void deleteAll() {
		personRepository.deleteAll();
	}

	public String deleteByFirstName(String firstName) throws PersonNotFoundException {

		Optional<Person> toDelete = personRepository.findByFirstNameIgnoreCase(firstName);
		if (toDelete.isPresent()) {
			Long idDeleted = personRepository.deletePersonById(toDelete.get().getId());
			return String.valueOf(idDeleted);
		}
		throw new PersonNotFoundException("Person with given id does not exists");
	}

	private boolean isExists(PersonInput pers) {
		Optional<Person> person = personRepository.findByFirstNameAndLastName(pers.getFirst_name(), pers.getLast_name());
		return person.isPresent();
	}
	
	private List<PersonInput> copy(final List<Person> pList) {
		List<PersonInput> personList = new ArrayList<PersonInput>(pList.size());
		for(Person p : pList) {
			personList.add(copy(p));			
		}		
		
		return personList;
	}
	
	
	private PersonInput copy(final Person p) {
		PersonInput pi = new PersonInput(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(), p.getFavouriteColour(), p.getHobby());
		return pi;
	}

}
