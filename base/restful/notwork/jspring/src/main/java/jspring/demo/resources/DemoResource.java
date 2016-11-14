package jspring.demo.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hah")
public class DemoResource {
      
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		return "sdada";
	}
}
