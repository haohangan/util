package jersey.jaspring.resources;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.jaspring.resources.reqvo.ReqVO;
import jersey.jaspring.service.GreetingService;

@Singleton
@Path("demo")
@Service
public class SpringRequestResource {
	private static final Logger logger = Logger.getLogger(SpringRequestResource.class.getName());

	AtomicInteger counter = new AtomicInteger();

	@Autowired
	private GreetingService greetingService;

	@GET
	@Path("{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello(@PathParam("name") String name) {
		logger.info("name:" + name);
		return greetingService.greet("world " + counter.incrementAndGet());
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String validate(@Valid ReqVO vo) {
		System.out.println(vo);
		return "name:" + vo.getName();
	}

}
