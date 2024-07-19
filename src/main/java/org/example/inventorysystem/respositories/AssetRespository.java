package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRespository extends JpaRepository<Asset, Long> {
	Boolean existsByInventoryNumber(Integer inventoryNumber);
}
