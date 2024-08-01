package org.example.inventorysystem.services;

import org.example.inventorysystem.models.InventoryField;
import org.example.inventorysystem.respositories.InventoryFieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryFieldService {
	private final InventoryFieldRepository inventoryFieldRepository;

	public InventoryFieldService(InventoryFieldRepository inventoryFieldRepository) {
		this.inventoryFieldRepository = inventoryFieldRepository;
	}

	public InventoryField addInventoryField(InventoryField inventoryField) {
		return inventoryFieldRepository.save(inventoryField);
	}

	public List<InventoryField> getAllInventoryFields() {
		List<InventoryField> inventoryFields = inventoryFieldRepository.findAll();
		if (inventoryFields.isEmpty()) {
			throw new RuntimeException("No inventory fields found");
		} else {
			return inventoryFields;
		}
	}

	public InventoryField getInventoryFieldById(long id) {
		Optional<InventoryField> inventoryField = inventoryFieldRepository.findById(id);
		if (inventoryField.isPresent()) {
			return inventoryField.get();
		} else {
			throw new RuntimeException("No inventory field found with id " + id);
		}
	}

	public InventoryField updateInventoryField(InventoryField inventoryField) {
		if(inventoryFieldRepository.existsById(inventoryField.getId())) {
			return inventoryFieldRepository.save(inventoryField);
		} else {
			throw new RuntimeException("No inventory field found with id " + inventoryField.getId());
		}
	}

	public void deleteInventoryField(long id) {
		if(inventoryFieldRepository.existsById(id)) {
			inventoryFieldRepository.deleteById(id);
		} else {
			throw new RuntimeException("No inventory field found with id " + id);
		}
	}
}
