package com.example.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

/**
 * 会議室・予約エンティティクラス
 *
 */
@Data
public class ReservableRoom implements Serializable {

	private static final long serialVersionUID = 4L;
	
	/**連番*/
	private long seqNo;
	
	/**予約日*/
	private LocalDate reservedDate;
	
	/**会議室ID*/
	private Integer roomId;
	
}
