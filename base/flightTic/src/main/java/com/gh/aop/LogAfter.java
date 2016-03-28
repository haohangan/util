package com.gh.aop;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import com.common.util.LogManager;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月24日 上午10:22:04 类说明
 */
@Component("afterLog")
public class LogAfter implements AfterReturningAdvice {
	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		Logger logger = LogManager.getLogger(target.getClass());
		StringBuffer sb = new StringBuffer();
		sb.append("after:离开方法：");
		sb.append(method.getName());
		sb.append("返回值为：");
		sb.append(returnValue);
		sb.append("--||");
		logger.info(sb.toString());
	}

}
