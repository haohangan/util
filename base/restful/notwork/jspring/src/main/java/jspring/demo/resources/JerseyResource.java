package jspring.demo.resources;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jspring.demo.service.DateTimeService;
import jspring.demo.service.GreetingService;

@Path("jersey-hello")
@Component
public class JerseyResource {

	private static final Logger LOGGER = Logger.getLogger(JerseyResource.class.getName());

	@Autowired
	private GreetingService greetingService;

	@Inject
	private DateTimeService timeService;

	public JerseyResource() {
		LOGGER.fine("HelloWorldResource()");
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		return String.format("%s: %s", timeService.getDateTime(), greetingService.greet("world"));
	}
}
