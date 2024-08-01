package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.Acquisition;
import org.example.inventorysystem.services.AcquisitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acquisition")
public class AcquisitionController {
	private final AcquisitionService acquisitionService;

	public AcquisitionController(AcquisitionService acquisitionService) {
		this.acquisitionService = acquisitionService;
	}

	@PostMapping("/")
	public Acquisition addAcquisition(@RequestBody Acquisition acquisition) {
		return acquisitionService.addAcquisition(acquisition);
	}

	@PostMapping("/update")
	public Acquisition updateAcquisition(@RequestBody Acquisition acquisition) {
		return acquisitionService.updateAcquisition(acquisition);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAcquisition(@PathVariable long id) {
		try {
			acquisitionService.deleteAcquisitionById(id);
			return new ResponseEntity<>("Acquisition deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public List<Acquisition> getAllAcquisitions() {
		return acquisitionService.getAllAcquisitions();
	}

	@GetMapping("/{id}")
	public Acquisition getAcquisitionById(@PathVariable long id) {
		return acquisitionService.findAcquisitionById(id);
	}
}
