package com.hugui.annotationbatis.aspectj;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.hugui.annotationbatis.entity.Order;

/**
 * @author HuGui
 * @class com.hugui.annotationbatis.aspectj NeedToSelectColumnAspectJ.java
 * @date 2018年5月23日
 */

@Aspect
@Component
public class NeedToSelectColumnAspectJ {

	@Pointcut("@annotation(com.hugui.annotationbatis.annotation.NeedtoSelectColumn)")
	public void annotationPointCut() {
	}

	@Before("annotationPointCut()")
	public void before(JoinPoint joinPoint) {
		MethodSignature sign = (MethodSignature) joinPoint.getSignature();
		Method method = sign.getMethod();
		System.out.println("===>" + method.getName());
	}

	@AfterReturning(returning = "rvt", pointcut = "annotationPointCut()")
	public void After(JoinPoint joinPoint, Object rvt) {
		Order order = (Order) rvt;
		System.out.println("--------->>>" + order.toString());
		System.out.println("===>" + joinPoint.getArgs()[0].toString());
	}
}
