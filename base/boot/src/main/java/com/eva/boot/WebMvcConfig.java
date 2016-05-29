package com.eva.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.eva.interceptor.DemoInterceptor;
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DemoInterceptor()).addPathPatterns("/opt/**");//这个操作需要验证用户权限
		super.addInterceptors(registry);
	}
   
}
