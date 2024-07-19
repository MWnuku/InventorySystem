package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Acquisition;
import org.example.inventorysystem.respositories.AcquisitionRepository;
import org.springframework.stereotype.Service;

@Service
public class AcquisitionService {
	private final AcquisitionRepository acquisitionRepository;

	public AcquisitionService(AcquisitionRepository acquisitionRepository) {
		this.acquisitionRepository = acquisitionRepository;
	}

	public Acquisition addAcquisition(Acquisition acquisition) {
		return acquisitionRepository.save(acquisition);
	}

	public Acquisition updateAcquisition(Acquisition acquisition) {
		if(acquisition.getId() == null){
			throw new IllegalArgumentException("Acquisition id is null");
		} else if(!acquisitionRepository.existsById(acquisition.getId())){
			throw new IllegalArgumentException("Acquisition does not exist");
		}
		return acquisitionRepository.save(acquisition);
	}
}
