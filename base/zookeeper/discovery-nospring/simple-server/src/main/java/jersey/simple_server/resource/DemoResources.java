package jersey.simple_server.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("hello")
public class DemoResources {
	
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	public String demo(){
		return "hello";
	}
	
	@GET
	@Path("json")
	@Produces({MediaType.APPLICATION_JSON})
	public Json json(){
		return new Json("ok");
	}
}
