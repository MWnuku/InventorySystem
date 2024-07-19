package org.example.inventorysystem.respositories;

import org.example.inventorysystem.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
