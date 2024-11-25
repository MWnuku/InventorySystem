package org.example.inventorysystem.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIdentityReference(alwaysAsId = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//	@JsonIgnoreProperties({"inventoryFieldList", "password", "role", "enabled", "username", "authorities",
//			"accountNonLocked", "accountNonExpired", "credentialsNonExpired"})
	private Person person;

	private Integer inventoryNumber;
	private String name;
	private Long value;
	private LocalDate date;

	@Nullable
	private String adnotations;
	private AssetStatus status;

	@OneToOne(mappedBy = "asset")
	@JsonManagedReference
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private InventoryField inventoryField;

	@Nullable
	private Type type;
}
