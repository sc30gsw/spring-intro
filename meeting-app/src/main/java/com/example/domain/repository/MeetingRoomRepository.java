package com.example.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.MeetingRoom;
import com.example.domain.mapper.MeetingRoomMapper;

/**
 * 会議室リポジトリクラス
 *
 */
@Repository
public class MeetingRoomRepository {
	
	/**会議室マッパー*/
	@Autowired
	MeetingRoomMapper mapper;
	
	/**
	 * @param reservedDate 予約日
	 * @return 予約情報を持つ会議室全件
	 */
	public List<MeetingRoom> getAllReservedRooms(LocalDate reservedDate) {
		return mapper.findAllReservedRooms(reservedDate);
	}

}
