package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.InventoryField;
import org.example.inventorysystem.services.InventoryFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
public class InventoryFieldController {
	private final InventoryFieldService inventoryFieldService;

	public InventoryFieldController(InventoryFieldService inventoryFieldService) {
		this.inventoryFieldService = inventoryFieldService;
	}

	@PostMapping("/")
	public ResponseEntity<?> addField(@RequestBody InventoryField field) {
		try {
			InventoryField addedField = inventoryFieldService.addInventoryField(field);
			return new ResponseEntity<>(addedField, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateField(@RequestBody InventoryField field) {
		try {
			InventoryField updatedField = inventoryFieldService.updateInventoryField(field);
			return new ResponseEntity<>(updatedField, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteField(@PathVariable long id) {
		try {
			inventoryFieldService.deleteInventoryField(id);
			return new ResponseEntity<>("Inventory field deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllFields() {
		try {
			List<InventoryField> fields = inventoryFieldService.getAllInventoryFields();
			return new ResponseEntity<>(fields, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getField(@PathVariable long id) {
		try {
			InventoryField field = inventoryFieldService.getInventoryFieldById(id);
			return new ResponseEntity<>(field, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}