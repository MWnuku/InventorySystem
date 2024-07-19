package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Acquisition;
import org.example.inventorysystem.respositories.AcquisitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public void deleteAcquisition(Acquisition acquisition) {
		if(acquisitionRepository.existsById(acquisition.getId())){
			acquisitionRepository.delete(acquisition);
		} else {
			throw new IllegalArgumentException("Acquisition does not exist");
		}
	}

	public Acquisition findAcquisitionById(Long id) {
		if(acquisitionRepository.existsById(id)){
			Optional<Acquisition> acquisition = acquisitionRepository.findById(id);
			if(acquisition.isPresent()){
				return acquisition.get();
			} else {
				throw new IllegalArgumentException("Acquisition does not exist");
			}
		} else {
			throw new IllegalArgumentException("Acquisition does not exist");
		}
	}

	public List<Acquisition> getAllAcquisitions() {
		List<Acquisition> acquisitions = acquisitionRepository.findAll();
		if(acquisitions.isEmpty()){
			throw new IllegalArgumentException("No acquisitions found");
		} else {
			return acquisitions;
		}
	}
}
