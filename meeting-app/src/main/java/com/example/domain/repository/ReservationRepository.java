package com.example.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.Reservation;
import com.example.domain.mapper.ReservationMapper;

/**
 * 予約情報リポジトリクラス
 *
 */
@Repository
public class ReservationRepository {

	/**予約情報マッパー*/
	@Autowired
	ReservationMapper mapper;
	
	/**
	 * 予約情報一覧の取得
	 * 
	 * @param reservedDate 予約日
	 * @param roomId 会議室ID
	 * @return 予約情報
	 */
	public List<Reservation> getByReservableRooms(LocalDate reservedDate, Integer roomId) {
		return mapper.findByReservableRoom(reservedDate, roomId);
	}
	
	/**
	 * 予約情報登録処理
	 * 
	 * @param result 予約
	 */
	public void insertReservationOne(Reservation result) {
		mapper.insertReservation(result);
	}
	
	/**
	 * 予約情報1件取得
	 * 
	 * @param reservationId 予約情報ID
	 * @return 予約情報
	 */
	public Reservation findOne(Integer reservationId) {
		return mapper.findByReservation(reservationId);
	}
	
	/**
	 * 予約情報1件削除
	 * 
	 * @param reservationId 予約情報ID
	 */
	public void delete(Integer reservationId) {
		mapper.deleteOneReservation(reservationId);
	}
}
