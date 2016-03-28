package com.gh.aop;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import com.common.util.LogManager;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月23日 下午3:38:07 类说明
 */
@Component("loggerBean")
public class LogBefore implements MethodBeforeAdvice {
	// private static final Logger logger =
	// LogManager.getLogger(LogBefore.class);

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		Logger logger = LogManager.getLogger(target.getClass());
		StringBuffer sb = new StringBuffer();
		sb.append("before-->>method name:");
		sb.append(method.getName());
		sb.append(" params:");
		for (Object obj : args) {
			sb.append(obj);
			sb.append(",");
		}
		sb.delete(sb.lastIndexOf(","), sb.length());
		sb.append("--||");
		logger.info(sb.toString());
	}

}
