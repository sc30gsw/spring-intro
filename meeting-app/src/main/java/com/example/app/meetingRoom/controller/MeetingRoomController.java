package com.example.app.meetingRoom.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		log.info("予約情報を持つ会議室全件を取得");
		
		// 本日の日付を取得
		LocalDate today = LocalDate.now();
		
		// 会議室一覧をmodelに登録
		val rooms = service.getAllRooms(today);
		
		// modelに登録
		model.addAttribute("date", today);
		model.addAttribute("rooms", rooms);
		
		log.info("処理の完了");
		
		return "room/listRooms";
	}
}
