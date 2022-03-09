package com.example.domain.service.reservation;

/**
 * 予約の重複例外クラス
 *
 */
public class AlreadyReservedException extends RuntimeException {
	public AlreadyReservedException(String message) {
		super(message);
	}
}
