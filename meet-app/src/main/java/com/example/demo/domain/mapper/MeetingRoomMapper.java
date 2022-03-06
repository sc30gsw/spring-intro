package com.example.demo.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.entity.MeetingRoom;
import com.example.demo.domain.entity.MeetingRoomCriteria;

@Mapper
public interface MeetingRoomMapper {

	public MeetingRoom findOne(Integer roomId);
	
	public long count();
	
	public List<MeetingRoom> findAll();
	
	public void createMeetingRoom(MeetingRoom meetingRoom);
	
	public List<MeetingRoom> findByCriteria(MeetingRoomCriteria criteria);
}
