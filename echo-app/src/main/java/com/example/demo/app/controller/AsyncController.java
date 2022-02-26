package com.example.demo.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.demo.app.component.GreetingMessageSender;

@Controller
public class AsyncController {
	
	@Autowired
	private GreetingMessageSender sender;
	
	@GetMapping("/greeting")
	public SseEmitter greeting() throws IOException, InterruptedException {
		
		SseEmitter emitter = new SseEmitter();
		
		//非同期処理を呼び出す
		sender.send(emitter);
		return emitter;
	}

}
