package simple.rest.express.inject;


import com.google.inject.Guice;
import com.google.inject.Injector;

import simple.rest.express.inject.controller.TimeController;
import simple.rest.express.inject.module.ControllerModule;

public class MyInjector {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ControllerModule());
		for(int i = 0;i<10;i++){
			TimeController tc = injector.getInstance(TimeController.class);
			System.out.println(tc.read(null, null));
		}
	}
}
