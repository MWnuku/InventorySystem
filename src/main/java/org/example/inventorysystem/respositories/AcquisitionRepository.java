package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Acquisition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquisitionRepository extends JpaRepository<Acquisition, Long> {
}
