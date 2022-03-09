package com.example.domain.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.entity.Reservation;

/**
 * 予約情報マッパーインターフェース
 *
 */
@Mapper
public interface ReservationMapper {
	
	/**
	 * 予約情報一覧の取得
	 * 
	 * @param reservedDate 予約日
	 * @param roomId 会議室ID
	 * @return 予約情報
	 */
	public List<Reservation> findByReservableRoom(@Param("reservedDate") LocalDate reservedDate, @Param("roomId") Integer roomId);

	/**
	 * 予約情報登録処理
	 * 
	 * @param result 予約情報
	 */
	public int insertReservation(Reservation result);
		
	/**
	 * 予約情報1件取得
	 * 
	 * @param reservationId 予約情報ID
	 * @return 予約情報
	 */
	public Reservation findByReservation(Integer reservationId);
	
	/**
	 * 予約情報1件削除
	 * 
	 * @param reservationId 予約情報ID
	 */
	public void deleteOneReservation(Integer reservationId);
}
