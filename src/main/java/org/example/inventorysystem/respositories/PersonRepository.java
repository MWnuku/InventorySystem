package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Long, Person> {
}
