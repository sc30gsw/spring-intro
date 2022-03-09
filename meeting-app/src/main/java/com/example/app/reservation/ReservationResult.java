package com.example.app.reservation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

/**
 * 予約情報レスポンスクラス
 *
 */
@Data
public class ReservationResult implements Serializable {

	private static final long serialVersionUID = 4L;
	
	/**予約ID*/
	private Integer reservationId;
	
	/**会議開始時間*/
	private LocalTime startTime;
	
	/**会議終了時間*/
	private LocalTime endTime;

	/**予約日*/
	private LocalDate reservedDate;
	
	/**会議室ID*/
	private Integer roomId;
	
	/**ユーザーID*/
	private String userId;
}
