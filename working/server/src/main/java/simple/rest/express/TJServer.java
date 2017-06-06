package simple.rest.express;

import org.restexpress.RestExpress;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.netty.handler.codec.http.HttpMethod;
import simple.rest.express.inject.controller.TimeController;
import simple.rest.express.inject.module.ControllerModule;
import simple.rest.express.inject.module.ServiceModule;
import simple.rest.express.serial.MySerializationProvider;

public class TJServer {

	public static void main(String[] args) {
		RestExpress.setDefaultSerializationProvider(new MySerializationProvider());
		RestExpress server = new RestExpress().setName("Echo").setPort(8888);
		
		Injector injector = Guice.createInjector(new ControllerModule(),new ServiceModule());
		
		server.uri("/obj", injector.getInstance(TimeController.class)).method(HttpMethod.GET);

		server.bind();
		server.awaitShutdown();
	}
}
