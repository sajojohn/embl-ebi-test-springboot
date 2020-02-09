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

	public Person create(Person person) throws PersonAlreadyExistsException {
		if (isExists(person)) {
			throw new PersonAlreadyExistsException("Person with given first name and last name already exists");
		}
		return personRepository.save(person);
	}

	public List<Person> getAll() {
		return personRepository.findAll();
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
		Optional<Person> person = personRepository.findByLastName(lastName);
		if (person.isPresent()) {
			return person.get();
		}
		throw new PersonNotFoundException("Could not find person with name " + lastName);
	}

	public Person getByFirstName(String firstName) throws PersonNotFoundException {
		Optional<Person> person = personRepository.findByFirstName(firstName);
		if (person.isPresent()) {
			return person.get();
		}
		throw new PersonNotFoundException("Could not find person with name " + firstName);

	}

	public Person update(String id, Person pPerson) throws PersonNotFoundException {
		Optional<Person> toUpdate = personRepository.findByObjectId(id);

		if (toUpdate.isPresent()) {
			Person person = toUpdate.get();
			person.setFirstName(pPerson.getFirstName());
			person.setLastName(pPerson.getLastName());
			person.setCountry(pPerson.getCountry());
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

		Optional<Person> toDelete = personRepository.findByFirstName(firstName);
		if (toDelete.isPresent()) {
			Long idDeleted = personRepository.deletePersonById(toDelete.get().getId());
			return String.valueOf(idDeleted);
		}
		throw new PersonNotFoundException("Person with given id does not exists");
	}

	private boolean isExists(Person pers) {
		Optional<Person> person = personRepository.findByName(pers.getFirstName(), pers.getLastName());
		return person.isPresent();
	}

}
