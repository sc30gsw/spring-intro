package com.example.domain.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 会議室エンティティクラス
 *
 */
@Data
public class MeetingRoom implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**会議室ID*/
	private Integer roomId;
	
	/**会議室名*/
	private String roomName;
	
	/**会議室・予約*/
	private List<ReservableRoom> reservableRooms;
}
