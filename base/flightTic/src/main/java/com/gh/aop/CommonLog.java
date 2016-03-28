package com.gh.aop;

import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.common.util.LogManager;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月23日 下午3:33:36 类说明
 */
@Component("commonLog")
public class CommonLog implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// System.out.println("进入:"+arg0.getThis().getClass().getName()+"||method:"+arg0.getMethod().getName());
		Class<?> clazz = arg0.getThis().getClass();
		Logger logger = LogManager.getLogger(clazz);
		StringBuffer sb = new StringBuffer();
		sb.append("class:");
		sb.append(clazz.getName());
		sb.append("||method:");
		sb.append(arg0.getMethod().getName());

		Object result = null;
		try {
			logger.info("进入" + sb.toString());
			result = arg0.proceed();
			// System.out.println("离开");
			logger.info("离开" + sb.toString());
			return result;
		} catch (Exception e) {
			// System.out.println("报错");
			logger.info("报错" + sb.toString());
			logger.warning(e.getMessage());
			throw e;
		}
	}
}
