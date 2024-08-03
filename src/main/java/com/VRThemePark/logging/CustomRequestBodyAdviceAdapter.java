package com.VRThemePark.logging;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

	@Autowired
	LoggingService loggingService;

	@Autowired
	HttpServletRequest httpServletRequest;

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		try {
			loggingService.logRequest(httpServletRequest, body);
		} catch (Exception e) {
//			throw new LCException(ProblemEnum.GENERAL_EXP.exceptionCode, ProblemEnum.GENERAL_EXP.userMessage,
//					ProblemEnum.GENERAL_EXP.developerMessage);
		}
		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return true;
	}
}
