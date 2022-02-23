package com.example.demo.domain.entity.error;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.domain.error.BookNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Map<Class<? extends Exception>, String> messageMappings =
			Collections.unmodifiableMap(new LinkedHashMap() {{
				put(MethodArgumentNotValidException.class, "Request value is invalid");
			}});
	
	private String resolveMessage(Exception e, String defaultMessage) {
		return messageMappings.entrySet().stream()
				.filter(entry -> entry.getKey().isAssignableFrom(e.getClass())).findFirst()
				.map(Map.Entry::getValue).orElse(defaultMessage);
	}

	private ApiError createApiError(Exception e) {
		ApiError apiError = new ApiError();
		apiError.setMessage(resolveMessage(e, e.getMessage()));
		apiError.setDocumentationUrl("http://example.com/api/errors");
		return apiError;
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ApiError apiError = createApiError(e);
		return super.handleExceptionInternal(e, apiError, headers, status, request);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException e, WebRequest request) {
		return handleExceptionInternal(e, null, null, HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleSystemException(Exception e, WebRequest request) {
		ApiError apiError = createApiError(e, "System error is occurred");
		return super.handleExceptionInternal(e, apiError, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	private ApiError createApiError(Exception e, String defaultMessage) {
		ApiError apiError = new ApiError();
		apiError.setMessage(resolveMessage(e, defaultMessage));
		apiError.setDocumentationUrl("http://example.com/api/errors");
		return apiError;
	}

}
