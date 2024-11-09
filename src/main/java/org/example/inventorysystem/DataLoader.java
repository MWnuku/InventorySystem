package org.example.inventorysystem;

import org.example.inventorysystem.models.*;
import org.example.inventorysystem.respositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DataLoader implements CommandLineRunner {
	private final AcquisitionRepository acquisitionRepository;
	private final AssetRespository assetRespository;
	private final ChangeRepository changeRepository;
	private final DeletitionRepository deletionRepository;
	private final InventoryFieldRepository inventoryFieldRepository;
	private final PersonRepository personRepository;
	private final RoomRepository roomRepository;
	private final PasswordEncoder passwordEncoder;

	public DataLoader(AcquisitionRepository acquisitionRepository, AssetRespository assetRespository, ChangeRepository changeRepository, DeletitionRepository deletionRepository, InventoryFieldRepository inventoryFieldRepository, PersonRepository personRepository, RoomRepository roomRepository, PasswordEncoder passwordEncoder) {
		this.acquisitionRepository = acquisitionRepository;
		this.assetRespository = assetRespository;
		this.changeRepository = changeRepository;
		this.deletionRepository = deletionRepository;
		this.inventoryFieldRepository = inventoryFieldRepository;
		this.personRepository = personRepository;
		this.roomRepository = roomRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		InventoryField inventoryField = new InventoryField();
		inventoryField.setNumber("A1234");
		inventoryFieldRepository.save(inventoryField);

		Person person = new Person();
		person.setFirstName("Admin");
		person.setLastName("Admin");
		person.setEmail("admin@mail.com");
		person.setUnit("Admin");
		person.setPassword(passwordEncoder.encode("admin123"));
		person.setInventoryFieldList(new ArrayList<>());
		person.setRole(Role.Admin);
		personRepository.save(person);

		Asset asset = new Asset();
		asset.setPerson(person);
		asset.setInventoryField(inventoryField);
		asset.setSymbol("symbol");
		asset.setInventoryNumber(1234);
		asset.setName("Name");
		asset.setAcquisitions(new ArrayList<>());
		asset.setChanges(new ArrayList<>());
		asset.setDeletions(new ArrayList<>());
		asset.setStatus(AssetStatus.Active);
		asset.setRooms(new ArrayList<>());
		asset.setType(Type.Intelectual);
		assetRespository.save(asset);

		ArrayList<Asset> assets = new ArrayList<>();
		assets.add(asset);
		inventoryField.setPerson(person);
		inventoryField.setAssets(assets);
		inventoryFieldRepository.save(inventoryField);
		ArrayList<InventoryField> inventoryFields = new ArrayList<>();
		inventoryFields.add(inventoryField);
		person.setInventoryFieldList(inventoryFields);
		personRepository.saveAndFlush(person);

		Change change = new Change();
		change.setAsset(asset);
		change.setDescription("Description");
		LocalDate date = LocalDate.now();
		change.setDate(date);
		change.setValue(123);
		changeRepository.save(change);

		Deletion deletion = new Deletion();
		deletion.setAsset(asset);
		deletion.setDescription("Description deletion");
		deletion.setDate(date);
		deletion.setValue(1234);
		deletionRepository.save(deletion);

		Acquisition acquisition = new Acquisition();
		acquisition.setAsset(asset);
		acquisition.setDescription("Description acquisition");
		acquisition.setDate(date);
		acquisition.setValue(123);
		acquisitionRepository.save(acquisition);

		Room room = new Room();
		room.setAsset(asset);
		room.setBuilding("Budynek A");
		room.setSymbol("symbol");
		room.setDateFrom(date);
		room.setDateTo(date.plusDays(10));
		roomRepository.save(room);

	}
}
