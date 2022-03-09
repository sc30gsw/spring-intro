package com.example.domain.repository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.ReservableRoom;
import com.example.domain.mapper.ReservableRoomMapper;

/**
 * 会議室・予約リポジトリクラス
 *
 */
@Repository
public class ReservableRoomRepository {

	/**会議室・予約マッパー*/
	@Autowired
	ReservableRoomMapper mapper;
	
	/**
	 * 会議室・予約を1件取得する処理
	 * 
	 * @param reservedDate 予約日
	 * @param roomId 会議室ID
	 * @return 会議室・予約
	 */
	public ReservableRoom getOneReservableRoom(LocalDate reservedDate, Integer roomId) {
		return mapper.findOneReservableRoom(reservedDate, roomId);
	}
}
