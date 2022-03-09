package com.example.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.entity.MeetingRoom;
import com.example.domain.repository.MeetingRoomRepository;

/**
 * 会議室サービスクラス
 *
 */
@Service
@Transactional
public class MeetingRoomService {

	/**会議室リポジトリクラス*/
	@Autowired
	MeetingRoomRepository repository;
	
	
	/**
	 * 予約情報を持つ会議室全件を取得する処理
	 * 
	 * @param date 予約日
	 * @return 予約情報を持つ会議室全件
	 */
	public List<MeetingRoom> getAllRooms(LocalDate date) {
		// 会議室一覧の取得
		List<MeetingRoom> rooms = new ArrayList<>();
		rooms = repository.getAllReservedRooms(date);
		
		return rooms;
	}
	
	/**
	 * 会議室1件取得する
	 * 
	 * @param roomId 会議室ID
	 * @return 会議室
	 */
	public MeetingRoom findMeetingRoom(Integer roomId) {
		return repository.findOne(roomId);
	}
}
