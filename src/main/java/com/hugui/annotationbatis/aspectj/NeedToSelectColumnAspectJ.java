package com.hugui.annotationbatis.aspectj;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hugui.annotationbatis.annotation.NeedtoSelectColumn;

/**
 * @author HuGui
 * @class com.hugui.annotationbatis.aspectj NeedToSelectColumnAspectJ.java
 * @date 2018年5月23日
 */

@Aspect
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class NeedToSelectColumnAspectJ {

	@Autowired
	ApplicationContext applicationContext;

	@Pointcut("@annotation(com.hugui.annotationbatis.annotation.NeedtoSelectColumn)")
	public void annotationPointCut() {
	}

	/**
	 *  java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表； Signature getSignature()
	 * ：获取连接点的方法签名对象； java.lang.Object getTarget() ：获取连接点所在的目标对象； java.lang.Object
	 * getThis() ：获取代理对象本身；
	 * 
	 * @param joinPoint
	 * @param rvt
	 * @param annotationArgs
	 * @throws Throwable
	 * @AfterReturning(returning = "rvt", pointcut =
	 *                           "annotationPointCut()&&@annotation(annotationArgs)")
	 *                           public void After(JoinPoint joinPoint, Object rvt,
	 *                           NeedtoSelectColumn annotationArgs) { if
	 *                           (rvt.getClass().isArray()) {
	 * 
	 *                           } else { Signature signature =
	 *                           joinPoint.getSignature(); Class returnType =
	 *                           ((MethodSignature) signature).getReturnType();
	 * 
	 *                           System.out.println("--------->>>" +
	 *                           order.toString()); System.out.println("===>" +
	 *                           annotationArgs.columnClassName().getName());
	 *                           System.out.println("===>" +
	 *                           annotationArgs.columnName());
	 *                           System.out.println("===>" +
	 *                           joinPoint.getArgs()[0].toString());
	 * 
	 *                           Method method = getMethod(joinPoint);
	 *                           NeedtoSelectColumn csc =
	 *                           method.getAnnotation(NeedtoSelectColumn.class); } }
	 */

	@Around(value = "annotationPointCut()&&@annotation(annotationArgs)")
	public Object around(ProceedingJoinPoint pjp, NeedtoSelectColumn annotationArgs) throws Throwable {
		Object obj = pjp.proceed();

		try {
			Class<?> clz = annotationArgs.fromClass();
			Method fromMethod = clz.getMethod(annotationArgs.fromMethod(), Serializable.class);
			Object bean = applicationContext.getBean(annotationArgs.fromClass());

			if (obj.getClass().isArray()) {
				List list = Arrays.asList(obj);
				if (list.isEmpty() || list.size() == 0) {
					return obj;
				}

				for (int i = 0; i < list.size(); i++) {
					Object fieldValue = getFieldValue(list.get(i), annotationArgs.findByColumn());
					Object findObj = fromMethod.invoke(bean, fieldValue);
					setFieldValue(list.get(i), annotationArgs.selectColumn(),
							getFieldValue(findObj, annotationArgs.selectColumn()));
				}
			} else {
				Object fieldValue = getFieldValue(obj, annotationArgs.findByColumn());
				Object findObj = fromMethod.invoke(bean, fieldValue);
				setFieldValue(obj, annotationArgs.selectColumn(),
						getFieldValue(findObj, annotationArgs.selectColumn()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static <T> Object getFieldValue(T object, String property) {
		if (object != null && property != null) {
			Class<T> currClass = (Class<T>) object.getClass();

			try {
				Field field = currClass.getDeclaredField(property);
				field.setAccessible(true);
				return field.get(object);
			} catch (NoSuchFieldException e) {
				throw new IllegalArgumentException(currClass + " has no property: " + property);
			} catch (IllegalArgumentException e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static <T> Object setFieldValue(T object, String property, Object reValue) {
		if (object != null && property != null) {
			Class<T> currClass = (Class<T>) object.getClass();

			try {
				Field field = currClass.getDeclaredField(property);
				field.setAccessible(true);
				field.set(object, reValue);
				return object;
			} catch (NoSuchFieldException e) {
				throw new IllegalArgumentException(currClass + " has no property: " + property);
			} catch (IllegalArgumentException e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Method getMethod(JoinPoint pjp) {
		// 获取参数的类型
		Object[] args = pjp.getArgs();
		Class[] argTypes = new Class[pjp.getArgs().length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}
		Method method = null;
		try {
			method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		return method;
	}
}
