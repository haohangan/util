package simple.rest.express.inject.controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import simple.rest.express.inject.service.MyService;


@Singleton
public class TimeController {
	@Inject
	private MyService service;

	public TimeController() {
		super();
		System.out.println("time controller init");
	}

	public String read(org.restexpress.Request req, org.restexpress.Response resp) {
		String num = req.getHeader("reqnum");
		System.out.println("reqnum:"+num);
		return service.service();
	}
}
