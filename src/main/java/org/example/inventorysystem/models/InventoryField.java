package org.example.inventorysystem.models;

import jakarta.persistence.*;
import lombok.*;

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
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(referencedColumnName = "person_id")
	private Person person;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "asset_id")
	private List<Asset> assets;
}
