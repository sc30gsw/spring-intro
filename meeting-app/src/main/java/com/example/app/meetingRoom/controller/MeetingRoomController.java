package com.example.app.meetingRoom.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.service.MeetingRoomService;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * 会議室コントローラークラス
 *
 */
@Controller
@RequestMapping("/rooms")
@Slf4j
public class MeetingRoomController {
	
	/**会議室サービスクラス*/
	@Autowired
	MeetingRoomService service;

	/**
	 * 会議室一覧ページ画面に遷移する
	 * 
	 * @param model
	 * @return room/listRooms
	 */
	@GetMapping
	public String getListRooms(Model model) {
		
		// 本日の日付を取得
		LocalDate today = LocalDate.now();	
		// modelに登録
		model.addAttribute("date", today);
		
		return "room/listRooms";
	}
	
	/**
	 * 日付ごとの会議室一覧への遷移
	 * 
	 * @param date 予約日時
	 * @param model
	 * @return room/listRooms
	 */
	@GetMapping("/{date}")
	public String getListRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date, Model model) {
		
		log.info("処理開始");
		
		// 予約情報を持つ会議室一覧を取得
		val rooms = service.getAllRooms(date);
		// modelに登録
		model.addAttribute("rooms", rooms);
		
		log.info("処理完了");
		
		return "room/listRooms";
	}
}
