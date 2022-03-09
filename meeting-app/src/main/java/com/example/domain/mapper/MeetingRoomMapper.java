package com.example.domain.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.entity.MeetingRoom;

/**
 * 会議室マッパーインターフェース
 *
 */
@Mapper
public interface MeetingRoomMapper {

	/**
	 * 会議室全件を取得する
	 * 
	 * @param reservedDate 予約日
	 * @return 予約情報を持つ会議室全件
	 */
	public List<MeetingRoom> findAllReservedRooms(LocalDate reservedDate);

	/**
	 * 会議室1件取得する
	 * 
	 * @param roomId 会議室ID
	 * @return 会議室
	 */
	public MeetingRoom findOneMeetingRoom(Integer roomId);
}
