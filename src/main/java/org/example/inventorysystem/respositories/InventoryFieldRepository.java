package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.InventoryField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface InventoryFieldRepository extends JpaRepository<InventoryField, Long> {
	ArrayList<InventoryField> getInventoryFieldsByPersonId(Long id);
}
