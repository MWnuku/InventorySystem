package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Deletion;
import org.example.inventorysystem.respositories.DeletitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeletionService {
	private final DeletitionRepository deletionRepository;

	public DeletionService(DeletitionRepository deletionRepository) {
		this.deletionRepository = deletionRepository;
	}

	public Deletion addDeletion(Deletion deletion) {
		return deletionRepository.save(deletion);
	}

	public List<Deletion> getAllDeletions() {
		List<Deletion> deletions = deletionRepository.findAll();
		if (deletions.isEmpty()) {
			throw new RuntimeException("No deletions found");
		} else {
			return deletions;
		}
	}

	public Deletion getDeletionById(long id) {
		Optional<Deletion> deletion = deletionRepository.findById(id);
		if(deletion.isPresent()) {
			return deletion.get();
		} else {
			throw new RuntimeException("No deletion found");
		}
	}

	public void deleteDeletionById(long id) {
		if(deletionRepository.existsById(id)){
			deletionRepository.deleteById(id);
		} else {
			throw new RuntimeException("No deletion found");
		}
	}

	public Deletion updateDeletion(Deletion deletion) {
		if (deletion.getId() == null) {
			throw new IllegalArgumentException("Deletion id cannot be null");
		} else if(!deletionRepository.existsById(deletion.getId())) {
			throw new RuntimeException("No deletion found");
		}
		Deletion updatedDeletion = deletionRepository.findById(deletion.getId()).get();
		updateDeletionFields(updatedDeletion, deletion);
		return deletionRepository.save(updatedDeletion);
	}

	private Deletion updateDeletionFields(Deletion existingDeletion, Deletion newDeletionData) {
		if (newDeletionData.getDescription() != null) {
			existingDeletion.setDescription(newDeletionData.getDescription());
		}
		if (newDeletionData.getDate() != null) {
			existingDeletion.setDate(newDeletionData.getDate());
		}
		if (newDeletionData.getValue() != null) {
			existingDeletion.setValue(newDeletionData.getValue());
		}
		if (newDeletionData.getAsset() != null) {
			existingDeletion.setAsset(newDeletionData.getAsset());
		}
		return existingDeletion;
	}
}
