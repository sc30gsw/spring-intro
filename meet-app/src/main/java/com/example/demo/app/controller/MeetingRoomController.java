package com.example.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.MeetingRoom;
import com.example.demo.domain.entity.MeetingRoomsBean;
import com.example.demo.domain.service.MeetingRoomService;

@RestController
@RequestMapping("/meetingRoom")
public class MeetingRoomController {

	@Autowired
	MeetingRoomService service;

	@GetMapping("/getRoom/{roomId}")
	public MeetingRoom getRoomOne(@PathVariable("roomId") Integer roomId) {

		MeetingRoom room = service.findRoomOne(roomId);

		return room;
	}

	@GetMapping("/getRooms")
	public MeetingRoomsBean getRooms() {

		return service.getAllRooms();
	}

	@GetMapping("/getCriteria")
	public List<MeetingRoom> getCriterias(@RequestParam(name = "roomId", required = false) Integer roomId,
			@RequestParam(name = "roomName", required = false) String roomName,
			@RequestParam(name = "capacity", required = false) Integer capacity) {

		return service.getCreteria(roomId, roomName, capacity);
	}
}
