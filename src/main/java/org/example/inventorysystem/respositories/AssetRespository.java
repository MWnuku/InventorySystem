package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRespository extends JpaRepository<Asset, Long> {
	Boolean existsByInventoryNumber(Integer inventoryNumber);
	Optional<Asset> findByName(String assetName);
	Optional<Asset> findBySymbol(String symbol);
}
