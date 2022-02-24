package com.example.demo.controller;

import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.error.ApiError;

@RestController
public class ApiErrorPageController {
	
	@RequestMapping("/apiError")
	public ApiError handleError(HttpServletRequest request) {
		
		String message;
		Exception e = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		
		Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if (e != null) {
			message = e.getMessage();
		} else {
			if (Arrays.asList(HttpStatus.values()).stream()
					.anyMatch(status -> status.value() == statusCode)) {
				message = HttpStatus.valueOf(statusCode).getReasonPhrase();
			} else {
				message = "Custom error(" + statusCode + ") is occurred";
			}
		}
		
		ApiError apiError = new ApiError();
		apiError.setMessage(message);
		apiError.setDocumentationUrl("http://example.com/api/errors");
		return apiError;
	}

}
