package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.Asset;
import org.example.inventorysystem.services.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/asset")
public class AssetController {
	private final AssetService assetService;

	public AssetController(AssetService assetService) {
		this.assetService = assetService;
	}

	@PostMapping("/")
	public ResponseEntity<?> addAsset(@RequestBody Asset asset) {
		try {
			Asset addedAsset = assetService.addAsset(asset);
			return new ResponseEntity<>(addedAsset, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateAsset(@RequestBody Asset asset) {
		try {
			Asset updatedAsset = assetService.updateAsset(asset);
			return new ResponseEntity<>(updatedAsset, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAsset(@PathVariable long id) {
		try {
			assetService.deleteAssetById(id);
			return new ResponseEntity<>("Asset deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllAssets() {
		try {
			List<Asset> assets = assetService.findAllAssets();
			return new ResponseEntity<>(assets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAssetById(@PathVariable long id) {
		try {
			Asset asset = assetService.findAssetById(id);
			return new ResponseEntity<>(asset, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> getAssetByName(@PathVariable String name) {
		try {
			Asset asset = assetService.findAssetByName(name);
			return new ResponseEntity<>(asset, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{symbol}")
	public ResponseEntity<?> getAssetBySymbol(@PathVariable String symbol) {
		try {
			Asset asset = assetService.findAssetBySymbol(symbol);
			return new ResponseEntity<>(asset, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
