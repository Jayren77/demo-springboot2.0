package org.jren.aspect;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 数据层的切面
 */
@Component
@Aspect
public class DasAdvisor {
	private static final Logger CAT = LoggerFactory.getLogger(DasAdvisor.class);

	/**
	 * 
	 * <p>Title: dasMapperMethodsInvoke </p>
	 * <p>Description: dasMapperMethodsInvoke </p>
	 * @author jren
	 * Date: 2018年5月9日 下午3:31:32
	 * @param proceedingJoinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around(value = "execution(* org.jren.edb.dao.*.*(..))")
	public Object dasMapperMethodsInvoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String flag = getMethodString(proceedingJoinPoint);
		long start = System.currentTimeMillis();
		try {
			Object result = proceedingJoinPoint.proceed();
			StringBuilder sb = new StringBuilder();
			sb.append("Executed " + flag + " [ timeCost -> " + (System.currentTimeMillis() - start)
					+ " ms , result(size) -> ");
			if (result instanceof List) {
				sb.append(((List) result).size());
			} else {
				sb.append(result);
			}
			sb.append(" ]");
			CAT.info(sb.toString());
			return result;

		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Executed " + flag + " [ timeCost -> " + (System.currentTimeMillis() - start) + " ms ] ");
			sb.append(e.getClass().getName() + ",");
			sb.append(e.toString());
			CAT.error(sb.toString());
			throw e;
		}
	}
	/**
	 * 
	 * <p>Title: dasServiceMethodsInvoke </p>
	 * <p>Description: dasServiceMethodsInvoke </p>
	 * @author jren
	 * Date: 2018年5月9日 下午3:46:43
	 * @param proceedingJoinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around(value = "execution(* org.jren.edb.service.*.*(..))")
	public Object dasServiceMethodsInvoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return dasMapperMethodsInvoke(proceedingJoinPoint);
	}
	/**
	 * getMethodString:
	 * 
	 * @param proceedingJoinPoint
	 * @return String
	 */
	private static String getMethodString(ProceedingJoinPoint proceedingJoinPoint) {
		Signature signature = proceedingJoinPoint.getSignature();
		Method method = ((MethodSignature) signature).getMethod();
		StringBuffer sb = new StringBuffer();
		sb.append(method.getDeclaringClass().getSimpleName());
		sb.append("@[");
		sb.append(method.getReturnType().getSimpleName() + " ");
		sb.append(method.getName());
		sb.append("(");
		@SuppressWarnings("rawtypes")
		Class[] params = method.getParameterTypes();
		for (int j = 0; j < params.length; j++) {
			// TODO
			sb.append(params[j].getSimpleName());
			if (j < (params.length - 1)) {
				sb.append(",");
			}
		}
		sb.append(")]");
		return sb.toString();
	}
}
