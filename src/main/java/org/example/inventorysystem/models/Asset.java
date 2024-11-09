package org.example.inventorysystem.models;

import com.fasterxml.jackson.annotation.*;
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
	@JsonBackReference
	private Person person;

	private String symbol;
	private Integer inventoryNumber;
	private String name;

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.MERGE})
	@JsonManagedReference
	private List<Acquisition> acquisitions = new ArrayList<>();

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.MERGE})
	@JsonManagedReference
	private List<Change> changes = new ArrayList<>();

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.MERGE})
	@JsonManagedReference
	private List<Deletion> deletions = new ArrayList<>();

	@Nullable
	private String adnotations;
	private AssetStatus status;

	@OneToMany(mappedBy = "asset", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonManagedReference
	private List<Room> rooms = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private InventoryField inventoryField;

	@Nullable
	private Type type;
}
