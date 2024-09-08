package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Person;
import org.example.inventorysystem.respositories.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final PersonRepository personRepository;

	public UserDetailsServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Person> person = personRepository.findByEmail(email);
		if (person.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return person.get();
	}
}
