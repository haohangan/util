package simple.rest.express.inject.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import simple.rest.express.inject.controller.TimeController;
import simple.rest.express.inject.interpector.TimeInterceptor;

public class ControllerModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(TimeController.class);
		bindInterceptor(Matchers.inPackage(Package.getPackage("simple.rest.express.inject.controller")), Matchers.any(), new TimeInterceptor());
	}

}
