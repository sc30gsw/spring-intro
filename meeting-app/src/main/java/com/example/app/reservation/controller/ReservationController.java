package com.example.app.reservation.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.entity.Reservation;
import com.example.domain.form.ReservationForm;
import com.example.domain.service.MeetingRoomService;
import com.example.domain.service.ReservationService;
import com.example.domain.service.reservation.AlreadyReservedException;
import com.example.domain.service.reservation.UnavailableReservationException;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * 予約情報コントローラークラス
 *
 */
@Controller
@RequestMapping("/reservations/{date}/{roomId}")
@Slf4j
public class ReservationController {

	/**会議室サービスクラス*/
	@Autowired
	MeetingRoomService meetingRoomService;

	/**予約情報サービスクラス*/
	@Autowired
	ReservationService reservationService;

	/**
	 * フォームの初期値を設定
	 * 
	 * @return form 予約情報入力フォーム
	 */
	@ModelAttribute
	ReservationForm setUpForm() {
		ReservationForm form = new ReservationForm();
		// デフォルト値
		form.setStartTime(LocalTime.of(9, 0));
		form.setEndTime(LocalTime.of(10, 0));
		return form;
	}

	/**
	 * 予約情報入力画面に遷移する
	 * 
	 * @param date 日付
	 * @param roomId 会議室ID
	 * @param model
	 * @return reservation/reserveForm
	 */
	@GetMapping
	public String reserveForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
			@PathVariable("roomId") Integer roomId, Model model) {

		log.info("処理開始");

		// 予約情報一覧の取得
		List<Reservation> reservations = reservationService.findReservations(date, roomId);

		// 00:00から30分刻みの無限ストリームを作成
		List<LocalTime> timeList = Stream.iterate(LocalTime.of(0, 0), t -> t.plusMinutes(30)).limit(24 * 2)
				.collect(Collectors.toList());

		val user = reservationService.dummyUser();

		// モデルに登録
		model.addAttribute("room", meetingRoomService.findMeetingRoom(roomId));
		model.addAttribute("reservations", reservations);
		model.addAttribute("timeList", timeList);
		model.addAttribute("user", user);

		log.info("処理完了");

		return "reservation/reserveForm";
	}

	/**
	 * 予約情報登録
	 * 
	 * @param form 予約情報入力フォーム
	 * @param result 入力情報
	 * @param date 日付
	 * @param roomId 会議室ID
	 * @param model
	 * @return reservations/date/roomId
	 */
	@PostMapping
	public String reserve(@Validated ReservationForm form, BindingResult result,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
			@PathVariable("roomId") Integer roomId, Model model) {
		log.info("登録処理開始");		
		// 入力チェックエラー
		if (result.hasErrors()) {
			log.info("入力失敗");
			return "redirect:/reservations/{date}/{roomId}";
		}
		
		try {
			reservationService.regist(form, date, roomId);
		} catch (UnavailableReservationException | AlreadyReservedException e) {
			model.addAttribute("error", e.getMessage());
			log.info(e.getMessage());
		}
		
		log.info("登録完了");
		
		return "redirect:/reservations/{date}/{roomId}";

	}

}
