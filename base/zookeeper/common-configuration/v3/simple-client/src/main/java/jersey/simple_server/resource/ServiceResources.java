package jersey.simple_server.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import jersey.simple_server.discovery.ServiceManager;

@Singleton
@Path("service")
public class ServiceResources {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public String json() throws Exception {
		String url = ServiceManager.SERVICE.get("hello");
		if (StringUtils.isNotBlank(url)) {
			return "{'url':'" + url + "'}";
		}
		return "{'url':'null'}";
	}
}
