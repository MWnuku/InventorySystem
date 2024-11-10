package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Person;
import org.example.inventorysystem.respositories.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
	private final PersonRepository personRepository;
	private final PasswordEncoder passwordEncoder;

	public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
		this.personRepository = personRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Person addPerson(Person person) {
		return personRepository.save(person);
	}

	public Person updatePerson(Person person) {
		if (person.getId() == null) {
			throw new IllegalArgumentException("Person id cannot be null");
		} else if(!personRepository.existsById(person.getId())) {
			throw new IllegalArgumentException("Person not found");
		}
		Person existingPerson = personRepository.findById(person.getId()).get();
		updatePersonFields(existingPerson, person);
		return personRepository.save(existingPerson);
	}

	private Person updatePersonFields(Person existingPerson, Person newPersonData) {
		if (newPersonData.getFirstName() != null) {
			existingPerson.setFirstName(newPersonData.getFirstName());
		}
		if (newPersonData.getLastName() != null) {
			existingPerson.setLastName(newPersonData.getLastName());
		}
		if (newPersonData.getPassword() != null) {
			existingPerson.setPassword(passwordEncoder.encode(newPersonData.getPassword())); // Encode the new password
		}
		if (newPersonData.getEmail() != null) {
			existingPerson.setEmail(newPersonData.getEmail());
		}
		if (newPersonData.getUnit() != null) {
			existingPerson.setUnit(newPersonData.getUnit());
		}
		if (newPersonData.getRole() != null) {
			existingPerson.setRole(newPersonData.getRole());
		}
		if (newPersonData.getInventoryFieldList() != null) {
			existingPerson.getInventoryFieldList().clear();
			newPersonData.getInventoryFieldList().forEach(existingPerson::addInventoryField);
		}
		return existingPerson;
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
