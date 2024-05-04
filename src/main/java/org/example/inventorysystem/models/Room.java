package org.example.inventorysystem.models;

import jakarta.persistence.*;
import lombok.*;

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
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Asset asset;
}
