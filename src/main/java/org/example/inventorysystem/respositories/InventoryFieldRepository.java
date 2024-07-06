package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.InventoryField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryFieldRepository extends JpaRepository<Long, InventoryField> {
}
