package org.example.inventorysystem.services;

import org.example.inventorysystem.models.Room;
import org.example.inventorysystem.respositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
	private final RoomRepository roomRepository;

	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public Room addRoom(Room room) {
		return roomRepository.save(room);
	}

	public Room updateRoom(Room room) {
		if(roomRepository.existsById(room.getId())) {
			return roomRepository.save(room);
		} else {
			throw new IllegalArgumentException("Room not found");
		}
	}

	public void deleteRoom(long id) {
		if(roomRepository.existsById(id)) {
			roomRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Room not found");
		}
	}

	public Room getRoomById(long id) {
		Optional<Room> room = roomRepository.findById(id);
		if(room.isPresent()) {
			return room.get();
		} else {
			throw new IllegalArgumentException("Room not found");
		}
	}

	public List<Room> getAllRooms() {
		List<Room> rooms = roomRepository.findAll();
		if(rooms.isEmpty()) {
			throw new IllegalArgumentException("Rooms not found");
		} else {
			return rooms;
		}
	}
}
