package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Change;
import org.example.inventorysystem.respositories.ChangeRepository;
import org.springframework.stereotype.Service;

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

	}
}
