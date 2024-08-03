package com.VRThemePark.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Aspect
@Component
public class LoggingResponseFilter {
	
	private static final Logger logger = LoggerFactory.getLogger("aspectLogger");
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controller() {
	}
	
	@Pointcut("within(@org.springframework.stereotype.Service *)")
	public void service() {
	}
	
	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	public void dao() {
	}
	
	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
	}
	
	@Before(" (controller() || service() || dao()) && allMethod()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Entering in Method :  " + joinPoint.getSignature().getName());
		logger.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
		logger.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
		logger.info("Target class : " + joinPoint.getTarget().getClass().getName());
	}
	
	@Around("(controller() || service() || dao()) && allMethod()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		try {
			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			Object result = joinPoint.proceed();
			long elapsedTime = System.currentTimeMillis() - start;
			logger.info("Method " + className + "." + methodName + " ()" + " execution time : "
					+ elapsedTime + " ms");
			
			return result;
		} catch (IllegalArgumentException e) {
			logger.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
					+ joinPoint.getSignature().getName() + "()");
			throw e;
		}
	}
	
	@AfterThrowing(pointcut = "(controller() || service() || dao()) && allMethod()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
		logger.error("Cause : " + exception.getCause());
	}
	
}