package org.example.inventorysystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "acquisition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Acquisition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "acquisition_id")
	private Long id;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonBackReference
	private Asset asset;
	private String description;
	private LocalDate date;
	private Integer value;
}
