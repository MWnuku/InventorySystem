package org.example.inventorysystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventoryField")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryField {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "inventory_id")
	private Long id;
	private String number;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	@OneToMany(mappedBy = "inventoryField", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<Asset> assets = new ArrayList<>();
}
