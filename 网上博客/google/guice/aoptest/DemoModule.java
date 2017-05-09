package com.google.guicetest.aoptest;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class DemoModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(ShowService.class).to(SmallWindowShowService.class);
		bindInterceptor(Matchers.any(), Matchers.any(), new HelloGoodbyeInterceptor());
	}

}
