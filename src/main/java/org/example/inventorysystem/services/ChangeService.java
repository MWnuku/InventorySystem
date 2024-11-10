package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Change;
import org.example.inventorysystem.respositories.ChangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChangeService {
	private final ChangeRepository changeRepository;

	public ChangeService(ChangeRepository changeRepository) {
		this.changeRepository = changeRepository;
	}

	public Change addChange(Change change) {
		return changeRepository.save(change);
	}

	public Change getChangeById(Long id) {
		Optional<Change> change = changeRepository.findById(id);
		if(change.isPresent()) {
			return change.get();
		} else {
			throw new RuntimeException("Change not found");
		}
	}

	public List<Change> getAllChanges() {
		List<Change> changes = changeRepository.findAll();
		if(changes.isEmpty()) {
			throw new RuntimeException("Change not found");
		} else {
			return changes;
		}
	}

	public void deleteChangeById(Long id) {
		if(changeRepository.existsById(id)) {
			changeRepository.deleteById(id);
		} else {
			throw new RuntimeException("Change not found");
		}
	}

	public Change updateChange(Change change) {
		if (change.getId() == null) {
			throw new IllegalArgumentException("Change id cannot be null");
		} else if(!changeRepository.existsById(change.getId())) {
			throw new IllegalArgumentException("Change not found");
		}
		Change change1 = changeRepository.findById(change.getId()).get();
		updateChangeFields(change1, change);
		return changeRepository.save(change1);
	}

	private Change updateChangeFields(Change existingChange, Change newChangeData) {
		if (newChangeData.getDescription() != null) {
			existingChange.setDescription(newChangeData.getDescription());
		}
		if (newChangeData.getDate() != null) {
			existingChange.setDate(newChangeData.getDate());
		}
		if (newChangeData.getValue() != null) {
			existingChange.setValue(newChangeData.getValue());
		}
		if (newChangeData.getAsset() != null) {
			existingChange.setAsset(newChangeData.getAsset());
		}
		return existingChange;
	}
}
