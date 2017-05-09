package com.google.guicetest.aoptest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class HelloGoodbyeInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("Hello");
		Object obj = invocation.proceed();
		System.out.println("Goodbye");
		return obj;
	}

}
