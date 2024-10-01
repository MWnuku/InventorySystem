package org.example.inventorysystem.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asset")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "asset_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;

	private String symbol;
	private Integer inventoryNumber;
	private String name;

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.MERGE})
	private List<Acquisition> acquisitions = new ArrayList<>();

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.MERGE})
	private List<Change> changes = new ArrayList<>();

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.MERGE})
	private List<Deletion> deletions = new ArrayList<>();

	@Nullable
	private String adnotations;
	private AssetStatus status;

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Room> rooms = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private InventoryField inventoryField;

	@Nullable
	private Type type;
}
