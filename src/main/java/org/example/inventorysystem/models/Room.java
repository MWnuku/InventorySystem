package org.example.inventorysystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "room_id")
	private Long id;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonBackReference
	private Asset asset;
	private String building;
	private String symbol;
	private LocalDate dateFrom;
	private LocalDate dateTo;
}
