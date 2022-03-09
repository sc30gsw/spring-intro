package com.example.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.entity.ReservableRoom;
import com.example.domain.entity.Reservation;
import com.example.domain.entity.Users;
import com.example.domain.form.ReservationForm;
import com.example.domain.repository.ReservableRoomRepository;
import com.example.domain.repository.ReservationRepository;
import com.example.domain.service.reservation.AlreadyReservedException;
import com.example.domain.service.reservation.UnavailableReservationException;

import lombok.val;

/**
 * 予約情報サービスクラス
 *
 */
@Service
@Transactional
public class ReservationService {

	/**予約情報リポジトリクラス*/
	@Autowired
	ReservationRepository reservationRepository;

	/**会議室・予約リポジトリクラス*/
	@Autowired
	ReservableRoomRepository reservabelRoomRepository;

	/**
	 * 予約情報を取得する処理
	 * 
	 * @param reservedDate　予約日
	 * @param roomId 会議室ID
	 * @return 予約情報
	 */
	public List<Reservation> findReservations(LocalDate reservedDate, Integer roomId) {
		return reservationRepository.getByReservableRooms(reservedDate, roomId);
	}

	/**
	 * 予約情報登録処理
	 * 
	 * @param form　予約情報入力フォーム
	 * @param roomId 会議室ID
	 * @return 予約情報
	 */
	public void regist(ReservationForm form, LocalDate reservedDate, Integer roomId) {

		// 検証用ユーザーの作成
		val dummyUser = dummyUser();

		// 会議室・予約の取得
		ReservableRoom reservable = reservabelRoomRepository.getOneReservableRoom(reservedDate, roomId);

		// 対処の会議室が予約可能かどうかチェック
		if (reservable == null) {
			throw new UnavailableReservationException("入力の日付・会議室の組み合わせは予約できません");
		}

		// 予約情報の設定
		Reservation result = new Reservation();
		result.setStartTime(form.getStartTime());
		result.setEndTime(form.getEndTime());
		result.setReservedDate(reservedDate);
		result.setRoomId(roomId);
		result.setUserId(dummyUser.getUserId());
		reservationRepository.insertReservationOne(result);

		// 重複チェック
		if (overlap(result, reservable, reservedDate, roomId)) {
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです");
		}
	}

	/**
	 * 2つの予約の重複をチェックする処理
	 * 
	 * @param target　予約情報
	 * @return 2つの予約の開始時刻と終了時刻が交差しているか、または包含関係にあるかを返す
	 * true:交差・包含関係 / false:重複
	 */
	public boolean overlap(Reservation result, ReservableRoom reservableRoom, LocalDate reservedDate, Integer roomId) {
		List<Reservation> reservation = reservationRepository.getByReservableRooms(reservedDate, roomId);
		// 2つの予約の開始時刻と終了時刻が重複するかどうかのチェック
		// true:重複 / false:重複なし
		boolean isDateOverlap = reservation.stream().anyMatch(reserve -> reserve.getReservedDate().equals(result.getReservedDate()));
		boolean isTimeOverlap = reservation.stream().anyMatch(reserve -> reserve.getStartTime().equals(result.getStartTime()) 
				&& reserve.getEndTime().equals(result.getEndTime()));
		if (isTimeOverlap && isDateOverlap) {
			return true;
		}
		
		return reservation.stream().anyMatch(reserve -> reserve.getStartTime().isAfter(result.getStartTime())
				&& reserve.getEndTime().isAfter(result.getEndTime()));
	}

	/**
	 * 検証用ユーザーの設定
	 * 
	 * @return user
	 */
	public Users dummyUser() {
		Users user = new Users();
		user.setUserId("taro-yamada");
		user.setFirstName("山田");
		user.setLastName("太郎");
		return user;
	}

	/**
	 * ユーザーが取消処理の権限があるかをチェックする
	 * 
	 * @param reservationId 予約情報ID
	 * @param requestUser 取消処理を行うユーザー
	 */
	public void cancel(Integer reservationId, Users requestUser) {
		Reservation reservation = reservationRepository.findOne(reservationId);
		if (!"ADMIN".equals(requestUser.getRoleName())
				&& !Objects.equals(reservation.getUserId(), requestUser.getUserId())) {
			throw new IllegalStateException("要求されたキャンセルは許可できません");
		}
		reservationRepository.delete(reservationId);
	}
}
