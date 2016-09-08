package com.eva.rpc.nios.rpc.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public enum ClassMap {
    INSTANCE;
	
//	private static final ConcurrentMap<Class<?>, Object> map = new ConcurrentHashMap<>();
	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	
	
	public void register(Class<?>... annotatedClasses){
		context.register(annotatedClasses);
		context.refresh();
	}
	
	public Object getBean(Class<?> clazz){
		return context.getBean(clazz);
	}
	
	protected ApplicationContext getContext(){
		return context;
	}
}
