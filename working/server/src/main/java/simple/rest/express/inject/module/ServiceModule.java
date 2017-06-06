package simple.rest.express.inject.module;

import com.google.inject.AbstractModule;

import simple.rest.express.inject.service.MyService;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MyService.class);
	}

}
