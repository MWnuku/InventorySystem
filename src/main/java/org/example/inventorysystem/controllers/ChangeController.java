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
	public Change addChange(@RequestBody Change change) {
		return changeService.addChange(change);
	}

	@PostMapping("/update")
	public Change updateChange(@RequestBody Change change) {
		return changeService.updateChange(change);
	}

	@DeleteMapping("/")
	public ResponseEntity<?> deleteChange(@RequestBody Change change) {
		try {
			changeService.deleteChangeById(change.getId());
			return new ResponseEntity<>("Change deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public List<Change> getAllChanges() {
		return changeService.getAllChanges();
	}

	@GetMapping("/{id}")
	public Change getChangeById(@PathVariable long id) {
		return changeService.getChangeById(id);
	}
}
