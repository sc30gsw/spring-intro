package com.example.demo.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.entity.MeetingRoom;

@Mapper
public interface MeetingRoomMapper {

	public MeetingRoom findOne(Integer roomId);
}
