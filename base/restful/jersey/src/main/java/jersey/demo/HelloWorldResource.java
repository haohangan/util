package jersey.demo;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import jersey.demo.vo.Person;

@Path("helloworld")
public class HelloWorldResource {
	@GET
	@Produces("text/plain")
	public String getHello() {
		return "Hello World!";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/a")
	public String getHello2() {
		Person  p = new Person(UUID.randomUUID().toString(),"Max");
		Gson g = new Gson();
		return g.toJson(p);
	}
}
