package org.example.inventorysystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "deletion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deletion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "deletion_id")
	private Long id;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Asset asset;
	private String description;
	private LocalDate date;
	private Integer value;
}
