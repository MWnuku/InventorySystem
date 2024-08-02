package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Person;
import org.example.inventorysystem.respositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person addPerson(Person person) {
		return personRepository.save(person);
	}

	public Person updatePerson(Person person) {
		if(personRepository.existsById(person.getId())){
			return personRepository.save(person);
		} else {
			throw new IllegalArgumentException("Person not found");
		}
	}

	public void deletePersonById(long id) {
		if(personRepository.existsById(id)){
			personRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Person not found");
		}
	}

	public Person getPersonById(long id) {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()){
			return person.get();
		} else {
			throw new IllegalArgumentException("Person not found");
		}
	}

	public List<Person> getAllPersons() {
		List<Person> persons = personRepository.findAll();
		if(persons.isEmpty()){
			throw new IllegalArgumentException("No persons found");
		} else {
			return persons;
		}
	}
}
