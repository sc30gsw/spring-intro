package com.example.domain.mapper;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.entity.ReservableRoom;

/**
 * 会議室・予約マッパーインターフェース
 *
 */
@Mapper
public interface ReservableRoomMapper {

	/**
	 * 会議室・予約を1件取得する処理
	 * 
	 * @param reservedDate 予約日
	 * @param roomId 会議室ID
	 * @return 会議室・予約
	 */
	public ReservableRoom findOneReservableRoom(@Param("reservedDate") LocalDate reservedDate, @Param("roomId") Integer roomId);
}
