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
	public InventoryField addField(@RequestBody InventoryField field) {
		return inventoryFieldService.addInventoryField(field);
	}

	@PostMapping("/update")
	public InventoryField updateField(@RequestBody InventoryField field) {
		return inventoryFieldService.updateInventoryField(field);
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
	public List<InventoryField> getAllFields() {
		return inventoryFieldService.getAllInventoryFields();
	}

	@GetMapping("{id}")
	public InventoryField getField(@PathVariable long id) {
		return inventoryFieldService.getInventoryFieldById(id);
	}
}
