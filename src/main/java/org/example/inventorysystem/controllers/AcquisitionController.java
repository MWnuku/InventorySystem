package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.Acquisition;
import org.example.inventorysystem.services.AcquisitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> addAcquisition(@RequestBody Acquisition acquisition) {
		try {
			Acquisition addedAcquisition = acquisitionService.addAcquisition(acquisition);
			return new ResponseEntity<>(addedAcquisition, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateAcquisition(@RequestBody Acquisition acquisition) {
		try {
			Acquisition updatedAcquisition = acquisitionService.updateAcquisition(acquisition);
			return new ResponseEntity<>(updatedAcquisition, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAcquisitionById(@PathVariable long id) {
		try {
			acquisitionService.deleteAcquisitionById(id);
			return new ResponseEntity<>("Acquisition deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllAcquisitions() {
		try {
			List<Acquisition> acquisitions = acquisitionService.getAllAcquisitions();
			return new ResponseEntity<>(acquisitions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAcquisitionById(@PathVariable long id) {
		try {
			Acquisition acquisition = acquisitionService.findAcquisitionById(id);
			return new ResponseEntity<>(acquisition, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
