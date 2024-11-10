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
		if (room.getId() == null) {
			throw new IllegalArgumentException("Room id cannot be null");
		} else if(!roomRepository.existsById(room.getId())) {
			throw new IllegalArgumentException("Room not found");
		}
		Room existingRoom = roomRepository.findById(room.getId()).get();
		updateRoomFields(existingRoom, room);
		return roomRepository.save(existingRoom);
	}

	private Room updateRoomFields(Room existingRoom, Room newRoomData) {
		if (newRoomData.getBuilding() != null) {
			existingRoom.setBuilding(newRoomData.getBuilding());
		}
		if (newRoomData.getSymbol() != null) {
			existingRoom.setSymbol(newRoomData.getSymbol());
		}
		if (newRoomData.getDateFrom() != null) {
			existingRoom.setDateFrom(newRoomData.getDateFrom());
		}
		if (newRoomData.getDateTo() != null) {
			existingRoom.setDateTo(newRoomData.getDateTo());
		}
		if (newRoomData.getAsset() != null) {
			existingRoom.setAsset(newRoomData.getAsset());
		}
		return existingRoom;
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
