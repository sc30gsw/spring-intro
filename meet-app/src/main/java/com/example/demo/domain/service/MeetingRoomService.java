package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.MeetingRoom;
import com.example.demo.domain.entity.MeetingRoomCriteria;
import com.example.demo.domain.entity.MeetingRoomsBean;
import com.example.demo.domain.mapper.MeetingRoomMapper;

@Service
public class MeetingRoomService {

	@Autowired
	MeetingRoomMapper mapper;
	
	public MeetingRoom findRoomOne(Integer roomId) {
		return mapper.findOne(roomId);
	}
	
	public MeetingRoomsBean getAllRooms() {
		MeetingRoomsBean bean = new MeetingRoomsBean();
		bean.setMeetingRooms(mapper.findAll());
		bean.setCount(mapper.count());
		
		return bean;
	}
	
	public List<MeetingRoom> getCreteria(Integer roomId, String roomName, Integer capacity) {
		MeetingRoomCriteria criteria = new MeetingRoomCriteria();
		criteria.setRoomId(roomId);
		criteria.setRoomName(roomName);
		criteria.setCapacity(capacity);
		List<MeetingRoom> rooms = mapper.findByCriteria(criteria);
		return rooms;
	}
}
