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
	public Deletion addDeletion(@RequestBody final Deletion deletion) {
		return deletionService.addDeletion(deletion);
	}

	@PostMapping("/update")
	public Deletion updateDeletion(@RequestBody final Deletion deletion) {
		return deletionService.updateDeletion(deletion);
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
	public List<Deletion> getAllDeletions() {
		return deletionService.getAllDeletions();
	}

	@GetMapping("/{id}")
	public Deletion getDeletionById(@PathVariable final long id) {
		return deletionService.getDeletionById(id);
	}
}
