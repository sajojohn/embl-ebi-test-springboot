package com.embl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.embl.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
	public Optional<Person> findByFirstName(String firstName);

	public Optional<Person> findByLastName(String lastName);

	public List<Person> findByCountry(String country);

	@Query("{'_id': ?0}")
	public Optional<Person> findByObjectId(String id);

	@Query("{'_id': {$in: ?0}}")
	public List<Person> findByObjectIds(String[] ids);

	@Query("{'firstName': ?0, 'lastName': ?1}")
	public Optional<Person> findByName(String firstName, String lastName);

	@Query(value = "{id : $0}", delete = true)
	public void deleteById(String id);

	public Long deletePersonById(String Id);

}
