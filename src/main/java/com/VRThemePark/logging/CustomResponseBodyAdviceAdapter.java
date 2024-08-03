package com.VRThemePark.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

	@Autowired
	LoggingService loggingService;

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		if (request instanceof ServletServerHttpRequest && response instanceof ServletServerHttpResponse) {
			try {
				loggingService.logResponse(((ServletServerHttpRequest) request).getServletRequest(),
						((ServletServerHttpResponse) response).getServletResponse(), body);
			} catch (Exception e) {
//				throw new LCException(ProblemEnum.GENERAL_EXP.exceptionCode, ProblemEnum.GENERAL_EXP.userMessage,
//						ProblemEnum.GENERAL_EXP.developerMessage);
			}
		}
		return body;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return true;
	}
}
