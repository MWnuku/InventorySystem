package org.example.inventorysystem;

import org.example.inventorysystem.models.*;
import org.example.inventorysystem.respositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
	private final AssetRespository assetRespository;
	private final InventoryFieldRepository inventoryFieldRepository;
	private final PersonRepository personRepository;
	private final RoomRepository roomRepository;
	private final PasswordEncoder passwordEncoder;

	public DataLoader(AssetRespository assetRespository, InventoryFieldRepository inventoryFieldRepository, PersonRepository personRepository, RoomRepository roomRepository, PasswordEncoder passwordEncoder) {
		this.assetRespository = assetRespository;
		this.inventoryFieldRepository = inventoryFieldRepository;
		this.personRepository = personRepository;
		this.roomRepository = roomRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		InventoryField inventoryField2 = new InventoryField();
		inventoryField2.setNumber("177-02");
		inventoryFieldRepository.save(inventoryField2);
		Person gAdmin = new Person();
		gAdmin.setFirstName("Grzegorz");
		gAdmin.setLastName("Admin");
		gAdmin.setEmail("g.admin@mail.com");
		gAdmin.setPassword(passwordEncoder.encode("admin123"));
		gAdmin.setRole(Role.Admin);
		ArrayList<InventoryField> fields = new ArrayList<>();
		fields.add(inventoryField2);
		gAdmin.setInventoryFieldList(fields);
		personRepository.save(gAdmin);
		inventoryField2.setPerson(gAdmin);
		inventoryFieldRepository.save(inventoryField2);

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

		LocalDate date = LocalDate.now();
		Room room = new Room();
		room.setBuilding("Budynek A");
		room.setSymbol("symbol");
		room.setDateFrom(date);
		room.setDateTo(date.plusDays(10));
		roomRepository.save(room);

		Asset asset = new Asset();
		asset.setPerson(person);
		asset.setInventoryField(inventoryField);
		asset.setInventoryNumber(1234);
		asset.setName("Name");
		asset.setStatus(AssetStatus.Active);
		asset.setRoom(room);
		asset.setType(Type.Intelectual);
		assetRespository.save(asset);
		List<Asset> assets = new ArrayList<>();
		assets.add(asset);
		room.setAssets(assets);
		roomRepository.save(room);

		ArrayList<Asset> assets2 = new ArrayList<>();
		assets.add(asset);
		inventoryField.setPerson(person);
		inventoryField.setAssets(assets2);
		inventoryFieldRepository.save(inventoryField);
		ArrayList<InventoryField> inventoryFields = new ArrayList<>();
		inventoryFields.add(inventoryField);
		person.setInventoryFieldList(inventoryFields);
		personRepository.saveAndFlush(person);


	}
}
