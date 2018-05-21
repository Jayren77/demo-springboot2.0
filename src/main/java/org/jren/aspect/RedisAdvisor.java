package org.jren.aspect;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisAdvisor{
	
	
//	private static final Logger log = LoggerFactory.getLogger(RedisAdvisor.class);

	@Around(value = "execution(* org.jren.redis.service.*.*(..))")
	public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable  {
		
		String flag = getMethodString(proceedingJoinPoint);
		long start = System.currentTimeMillis();

		try{
			Object result = proceedingJoinPoint.proceed();
			StringBuilder sb = new StringBuilder();
			long cost =  System.currentTimeMillis() - start;
            sb.append("Executed " + flag + " [ timeCost -> " + cost + " ms , result(size) -> ");
            if (result instanceof List) {
                sb.append(((List) result).size());
            } else if (result instanceof Map) {
            	sb.append(((Map) result).size());
            } else {
                sb.append(result);
            }
            sb.append(" ]");
			return result;
	    }catch(RedisConnectionFailureException e){
	    	throw e;
		}catch(Exception e){
	    	StringBuilder sb = new StringBuilder();
            sb.append("Executed " + flag + " [ timeCost -> " + (System.currentTimeMillis() - start) + " ms ] ");
            sb.append(e.getClass().getName() + ",");
            sb.append(e.toString());
	    	throw e;
	    }
	}
	
	/**
     * GetMethodString: 获取MethodInvocation的输出文本
     *
     * @param invocation
     * @return 输出文本
     */
    private static String getMethodString(ProceedingJoinPoint proceedingJoinPoint) {
    	Signature signature =  proceedingJoinPoint.getSignature();
    	Method method = ((MethodSignature) signature).getMethod(); 
        StringBuffer sb = new StringBuffer();
        sb.append(method.getDeclaringClass().getSimpleName());
        sb.append("@[");
        sb.append(method.getReturnType().getSimpleName()).append(" ");
        sb.append(method.getName());
        sb.append("(");
        @SuppressWarnings("rawtypes")
        Class[] params = method.getParameterTypes();
        for (int j = 0; j < params.length; j++) {
            // TODO 参数是泛型是，无法区分两个方法，后续补充
            sb.append(params[j].getSimpleName());
            if (j < (params.length - 1)) {
                sb.append(",");
            }
        }
        sb.append(")]");
        return sb.toString();
    }

}
