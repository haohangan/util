package jersey.simple_server.resource;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.simple_server.ServiceStart;

@Singleton
@Path("service")
public class ServiceResources {
   
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<?> json() throws Exception{
		return ServiceStart.list();
	}
}
