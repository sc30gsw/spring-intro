package com.example.domain.service.reservation;

/**
 * 予約可否判定例外クラス
 *
 */
public class UnavailableReservationException extends RuntimeException {
	public UnavailableReservationException(String message) {
		super(message);
	}
}
