package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.Change;
import org.example.inventorysystem.services.ChangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/change")
public class ChangeController {
	private final ChangeService changeService;

	public ChangeController(ChangeService changeService) {
		this.changeService = changeService;
	}

	@PostMapping("/")
	public ResponseEntity<?> addChange(@RequestBody Change change) {
		try {
			Change addedChange = changeService.addChange(change);
			return new ResponseEntity<>(addedChange, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateChange(@RequestBody Change change) {
		try {
			Change updatedChange = changeService.updateChange(change);
			return new ResponseEntity<>(updatedChange, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteChangeById(@PathVariable long id) {
		try {
			changeService.deleteChangeById(id);
			return new ResponseEntity<>("Change deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllChanges() {
		try {
			List<Change> changes = changeService.getAllChanges();
			return new ResponseEntity<>(changes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getChangeById(@PathVariable long id) {
		try {
			Change change = changeService.getChangeById(id);
			return new ResponseEntity<>(change, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
