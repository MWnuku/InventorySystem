package org.example.inventorysystem.models;

import jakarta.persistence.*;
import lombok.*;

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
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Person person;
	private String symbol;
	private Integer inventoryNumber;
	private String name;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Acquisition> acquisitions;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Change> changes;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Deletion> deletions;
	private String adnotations;
	private AssetStatus status;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Room> rooms;
}
