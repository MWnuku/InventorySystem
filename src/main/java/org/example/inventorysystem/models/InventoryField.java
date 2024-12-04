package org.example.inventorysystem.models;

import com.fasterxml.jackson.annotation.*;
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
	private Long id;
	private String number;

	@ManyToOne
	@JsonBackReference
	private Person person;

	@OneToMany(mappedBy = "inventoryField", cascade = CascadeType.ALL)
	@JsonManagedReference("inventoryField-assets")
	private List<Asset> assets;

}

