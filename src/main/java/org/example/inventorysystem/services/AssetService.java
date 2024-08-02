package org.example.inventorysystem.services;

import org.apache.coyote.Response;
import org.example.inventorysystem.models.Asset;
import org.example.inventorysystem.respositories.AssetRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
	private final AssetRespository assetRespository;

	public AssetService(AssetRespository assetRespository) {
		this.assetRespository = assetRespository;
	}

	public Asset addAsset(Asset asset) {
		if(assetRespository.existsByInventoryNumber(asset.getInventoryNumber())){
			throw new IllegalArgumentException("Inventory number already exists");
		}
		return assetRespository.save(asset);
	}

	public Asset updateAsset(Asset asset) {
		if(asset.getId() == null){
			throw new IllegalArgumentException("Asset id cannot be null");
		} else if(!assetRespository.existsById(asset.getId())){
			throw new IllegalArgumentException("Asset id does not exist");
		}
		return assetRespository.save(asset);
	}

	public void deleteAssetById(long id) {
		if(!assetRespository.existsById(id)){
			throw new IllegalArgumentException("Asset id does not exist");
		}
		assetRespository.deleteById(id);
	}

	public Asset findAssetById(Long id) {
		if(!assetRespository.existsById(id)){
			throw new IllegalArgumentException("Asset id does not exist");
		} else {
			Optional<Asset> asset = assetRespository.findById(id);
			if(asset.isPresent()){
				return asset.get();
			} else {
				throw new IllegalArgumentException("Asset does not exist");
			}
		}
	}

	public List<Asset> findAllAssets() {
		List<Asset> assets = assetRespository.findAll();
		if(assets.isEmpty()){
			throw new IllegalArgumentException("No assets found");
		} else {
			return assets;
		}
	}
}
