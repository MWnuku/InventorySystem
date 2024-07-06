package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Deletion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletitionRepository extends JpaRepository<Long, Deletion> {
}
