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
			throw new IllegalArgumentException("Asset with this id does not exist");
		}
		Asset asset1 = assetRespository.findById(asset.getId()).get();
		if(asset.getAcquisitions() != null){
			asset1.setAcquisitions(asset.getAcquisitions());
		}
		if(asset.getInventoryNumber() != null){
			asset1.setInventoryNumber(asset.getInventoryNumber());
		}
		if(asset.getChanges() != null){
			asset1.setChanges(asset.getChanges());
		}
		if(asset.getAdnotations() != null){
			asset1.setAdnotations(asset.getAdnotations());
		}
		if(asset.getDeletions() != null){
			asset1.setDeletions(asset.getDeletions());
		}
		if(asset.getName() != null){
			asset1.setName(asset.getName());
		}
		if(asset.getStatus() != null){
			asset1.setStatus(asset.getStatus());
		}
		if(asset.getSymbol() != null){
			asset1.setSymbol(asset.getSymbol());
		}
		if(asset.getRooms() != null){
			asset1.setRooms(asset.getRooms());
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

	public Asset findAssetByName(String name){
		Optional<Asset> asset = assetRespository.findByName(name);
		if(asset.isPresent()){
			return asset.get();
		} else {
			throw new IllegalArgumentException("Asset does not exist");
		}
	}

	public Asset findAssetBySymbol(String symbol){
		Optional<Asset> asset = assetRespository.findBySymbol(symbol);
		if(asset.isPresent()){
			return asset.get();
		} else {
			throw new IllegalArgumentException("Asset does not exist");
		}
	}
}
