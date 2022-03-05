package com.example.demo.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.entity.MeetingRoom;

@Mapper
public interface MeetingRoomMapper {

	public MeetingRoom findOne(Integer roomId);
	
	public long count();
	
	public List<MeetingRoom> findAll();
	
	public MeetingRoom createMeetingRoom(MeetingRoom meetingRoom);
}
