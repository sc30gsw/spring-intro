package com.example.demo.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Equipment;
import com.example.demo.domain.entity.Room;

@Service
public class RoomService {

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
	
	@Transactional(readOnly = true)
	public List<Room> getRoomsByName(String roomName) {
		String jpql = "SELECT r FROM Room r WHERE r.roomName = roomName";
		TypedQuery<Room> query = entityManager.createQuery(jpql, Room.class);
		query.setParameter("roomName", roomName);
		return query.getResultList();
	}
}
