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
		if (inventoryField.getId() == null) {
			throw new IllegalArgumentException("InventoryField id cannot be null");
		} else if(!inventoryFieldRepository.existsById(inventoryField.getId())) {
			throw new RuntimeException("No inventory field found with id " + inventoryField.getId());
		}
		InventoryField existingInventoryField = inventoryFieldRepository.findById(inventoryField.getId()).get();
		updateInventoryFieldFields(existingInventoryField, inventoryField);
		return inventoryFieldRepository.save(existingInventoryField);
	}

	private InventoryField updateInventoryFieldFields(InventoryField existingInventoryField, InventoryField newInventoryFieldData) {
		if (newInventoryFieldData.getNumber() != null) {
			existingInventoryField.setNumber(newInventoryFieldData.getNumber());
		}
		if (newInventoryFieldData.getPerson() != null) {
			existingInventoryField.setPerson(newInventoryFieldData.getPerson());
		}
		if (newInventoryFieldData.getAssets() != null) {
			existingInventoryField.setAssets(newInventoryFieldData.getAssets());
		}
		return existingInventoryField;
	}

	public void deleteInventoryField(long id) {
		if(inventoryFieldRepository.existsById(id)) {
			inventoryFieldRepository.deleteById(id);
		} else {
			throw new RuntimeException("No inventory field found with id " + id);
		}
	}
}
