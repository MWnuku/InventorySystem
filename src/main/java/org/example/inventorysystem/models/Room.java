package org.example.inventorysystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonBackReference
	private List<Asset> assets = new ArrayList<>();

	private String building;
	private String symbol;
	private LocalDate dateFrom;

	@Nullable
	private LocalDate dateTo;
}

