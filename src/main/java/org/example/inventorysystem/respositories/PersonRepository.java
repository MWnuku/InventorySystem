package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
	Optional<Person> findByEmail(String email);
}
