package com.example.demo.domain.entity.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private ApiError createApiError(Exception e) {
		ApiError apiError = new ApiError();
		apiError.setMessage(e.getMessage());
		apiError.setDocumentationUrl("http://example.com/api/errors");
		return apiError;
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ApiError apiError = createApiError(e);
		return super.handleExceptionInternal(e, apiError, headers, status, request);
	}

}
