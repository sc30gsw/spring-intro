package com.example.demo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.MeetingRoom;
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
}