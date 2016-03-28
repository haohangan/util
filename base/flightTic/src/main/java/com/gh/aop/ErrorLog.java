package com.gh.aop;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import com.common.util.LogManager;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月24日 上午10:28:53 类说明
 */
@Component("errorLog")
public class ErrorLog implements ThrowsAdvice {
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) {
		Logger logger = LogManager.getLogger(target.getClass());
		StringBuffer sb = new StringBuffer();
		sb.append("error:方法报错：");
		sb.append(method.getName());
		sb.append("错误栈为：");
		sb.append(ex.getMessage());
		sb.append("--||");
		logger.info(sb.toString());
		System.out.println("报错了");
	}
}
