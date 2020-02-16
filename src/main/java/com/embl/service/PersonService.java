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


	public PersonInput create(PersonInput person) throws PersonAlreadyExistsException {

		if (isExists(person)) {
			throw new PersonAlreadyExistsException("Person with given first name and last name already exists");
		}
		Person p = new Person(person.getFirst_name(), person.getLast_name(), person.getAge(),
				person.getFavourite_colour(), person.getHobby());
		return copy(personRepository.save(p));
	}

	public List<PersonInput> getAll() {
		return copy(personRepository.findAll());
	}

	public PersonInput getById(String id) throws PersonNotFoundException {
		Optional<Person> person = personRepository.findByObjectId(id);
		System.out.println("searching person byy id " + person);
		if (person.isPresent()) {
			return copy(person.get());
		}
		throw new PersonNotFoundException("Could not person object");
	}

	public List<PersonInput> getByIds(String ids) throws PersonNotFoundException {
		String[] idsToSearch = ids.split(",");
		List<Person> personList = personRepository.findByObjectIds(idsToSearch);
		return copy(personList);

	}

	public List<PersonInput> getByLastName(String lastName) throws PersonNotFoundException {
		Optional<List<Person>> person = personRepository.findByLastNameIgnoreCase(lastName);
		if (person.isPresent() && !person.get().isEmpty()) {
			return copy(person.get());
		}
		throw new PersonNotFoundException("Could not find person with name " + lastName);
	}

	public List<PersonInput> getByFirstName(String firstName) throws PersonNotFoundException {
		Optional<List<Person>> person = personRepository.findByFirstNameIgnoreCase(firstName);
		if (person.isPresent() && !person.get().isEmpty()) {
			return copy(person.get());
		}
		throw new PersonNotFoundException("Could not find person with name " + firstName);

	}

	public List<PersonInput> getByFirstNameAndLastName(String firstName, String lastName)
			throws PersonNotFoundException {
		Optional<List<Person>> persons = personRepository.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);
		if (persons.isPresent() && !persons.get().isEmpty()) {
			return copy(persons.get());
		}
		throw new PersonNotFoundException("Could not find person with name " + firstName);

	}

	public PersonInput update(String id, PersonInput pPerson) throws PersonNotFoundException {
		Optional<Person> toUpdate = personRepository.findByObjectId(id);

		if (toUpdate.isPresent()) {
			Person person = toUpdate.get();
			person.setFirstName(pPerson.getFirst_name());
			person.setLastName(pPerson.getLast_name());
			person.setAge(pPerson.getAge());
			person.setFavouriteColour(pPerson.getFavourite_colour());
			person.setHobby(pPerson.getHobby());
			return copy(personRepository.save(person));
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


	private boolean isExists(PersonInput pers) {
		Optional<List<Person>> persons = personRepository.findByFirstNameAndLastNameIgnoreCase(pers.getFirst_name(),
				pers.getLast_name());
		return (persons.isPresent() && !persons.get().isEmpty());
	}

	private List<PersonInput> copy(final List<Person> pList) {
		List<PersonInput> personList = new ArrayList<PersonInput>(pList.size());
		for (Person p : pList) {
			personList.add(copy(p));
		}

		return personList;
	}

	private PersonInput copy(final Person p) {
		PersonInput pi = new PersonInput(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(),
				p.getFavouriteColour(), p.getHobby());
		return pi;
	}

}
