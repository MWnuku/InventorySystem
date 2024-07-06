package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Change;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeRepository extends JpaRepository<Long, Change> {
}
