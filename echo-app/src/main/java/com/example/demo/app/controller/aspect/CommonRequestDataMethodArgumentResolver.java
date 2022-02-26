package com.example.demo.app.controller.aspect;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.demo.domain.bean.CommonRequestData;

public class CommonRequestDataMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return CommonRequestData.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpSession session = webRequest.getNativeRequest(HttpServletRequest.class).getSession(false);
		
		String userAgent = webRequest.getHeader(HttpHeaders.USER_AGENT);
		String sessionId = Optional.ofNullable(session).map(HttpSession::getId).orElse(null);
		
		CommonRequestData commonRequestData = new CommonRequestData();
		commonRequestData.setUserAgent(userAgent);
		commonRequestData.setSessionId(sessionId);
		return commonRequestData;
	}
}
