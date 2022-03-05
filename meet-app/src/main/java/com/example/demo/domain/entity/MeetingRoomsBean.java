package com.example.demo.domain.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MeetingRoomsBean implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	private List<MeetingRoom> meetingRooms;
	
	private long count;

}
