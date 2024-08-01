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
		if(changeRepository.existsById(change.getId())) {
			return changeRepository.save(change);
		} else {
			throw new RuntimeException("Change not found");
		}
	}
}
