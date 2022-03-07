package com.example.domain.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import lombok.Data;

/**
 * 予約エンティティクラス
 *
 */
@Data
public class reservation implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	/**予約ID*/
	private Integer reservationId;
	
	/**会議開始時間*/
	private Time startTime;
	
	/**会議終了時間*/
	private Time endTime;

	/**予約日*/
	private Date reservedDate;
	
	/**会議室ID*/
	private Integer roomId;
	
	/**ユーザーID*/
	private String userId;
}
