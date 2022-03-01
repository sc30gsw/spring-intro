package com.example.demo.domain.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Equipment;
import com.example.demo.domain.entity.Room;

@Repository
public class RoomRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public List<Equipment> getEquipmentsInRoom(Integer roomId) {
		Room room = entityManager.find(Room.class, roomId);
		return room.getEquipments();
	}
	
	@Transactional(readOnly = true)
	public Room getRoomOfEquipment(Integer equipmentId) {
		Equipment equipment = entityManager.find(Equipment.class, equipmentId);
		return equipment.getRoom();
	}

}
