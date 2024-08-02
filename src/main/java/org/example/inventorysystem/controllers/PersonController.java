package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.Person;
import org.example.inventorysystem.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping("/")
	public ResponseEntity<?> addPerson(@RequestBody Person person) {
		try {
			Person addedPerson = personService.addPerson(person);
			return new ResponseEntity<>(addedPerson, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updatePerson(@RequestBody Person person) {
		try {
			Person updatedPerson = personService.updatePerson(person);
			return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePersonById(@PathVariable long id) {
		try {
			personService.deletePersonById(id);
			return new ResponseEntity<>("Person deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllPersons() {
		try {
			List<Person> persons = personService.getAllPersons();
			return new ResponseEntity<>(persons, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable long id) {
		try {
			Person person = personService.getPersonById(id);
			return new ResponseEntity<>(person, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

