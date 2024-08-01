package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.Asset;
import org.example.inventorysystem.services.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {
	private final AssetService assetService;

	public AssetController(AssetService assetService) {
		this.assetService = assetService;
	}

	@PostMapping("/")
	public Asset addAsset(@RequestBody Asset asset) {
		return assetService.addAsset(asset);
	}

	@PostMapping("/update")
	public Asset updateAsset(@RequestBody Asset asset) {
		return assetService.updateAsset(asset);
	}

	@DeleteMapping("/")
	public ResponseEntity<?> deleteAsset(@RequestBody Asset asset) {
		try {
			assetService.deleteAsset(asset);
			return new ResponseEntity<>("Asset deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public List<Asset> getAllAssets() {
		return assetService.findAllAssets();
	}

	@GetMapping("/{id}")
	public Asset getAssetById(@PathVariable long id) {
		return assetService.findAssetById(id);
	}
}
