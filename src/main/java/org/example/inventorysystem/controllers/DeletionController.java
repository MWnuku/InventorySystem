package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.Deletion;
import org.example.inventorysystem.services.DeletionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deletion")
public class DeletionController {
	private final DeletionService deletionService;

	public DeletionController(DeletionService deletionService) {
		this.deletionService = deletionService;
	}

	@PostMapping("/")
	public ResponseEntity<?> addDeletion(@RequestBody final Deletion deletion) {
		try {
			Deletion addedDeletion = deletionService.addDeletion(deletion);
			return new ResponseEntity<>(addedDeletion, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateDeletion(@RequestBody final Deletion deletion) {
		try {
			Deletion updatedDeletion = deletionService.updateDeletion(deletion);
			return new ResponseEntity<>(updatedDeletion, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDeletion(@PathVariable final long id) {
		try {
			deletionService.deleteDeletionById(id);
			return new ResponseEntity<>("Deletion deleted.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllDeletions() {
		try {
			List<Deletion> deletions = deletionService.getAllDeletions();
			return new ResponseEntity<>(deletions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getDeletionById(@PathVariable final long id) {
		try {
			Deletion deletion = deletionService.getDeletionById(id);
			return new ResponseEntity<>(deletion, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
