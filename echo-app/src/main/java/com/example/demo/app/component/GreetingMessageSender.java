package com.example.demo.app.component;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class GreetingMessageSender {

	@Async
	public void send(SseEmitter emitter) throws IOException, InterruptedException {
		
		//1秒間隔でイベントを送信する
		emitter.send(emitter.event().id(UUID.randomUUID().toString()).data("Good Morning!"));
		TimeUnit.SECONDS.sleep(1);
		
		emitter.send(emitter.event().id(UUID.randomUUID().toString()).data("Hello!"));
		TimeUnit.SECONDS.sleep(1);
		
		emitter.send(emitter.event().id(UUID.randomUUID().toString()).data("Good Night!"));
		
		//非同期処理を終了する
		emitter.complete();
	}

}
