package com.example.demo.app.echo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.bean.CommonRequestData;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String home(CommonRequestData commonRequestData) {
		System.out.println(commonRequestData.getUserAgent());
		System.out.println(commonRequestData.getSessionId());
		return "index";
	}
}
