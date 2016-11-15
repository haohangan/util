package jersey.jaspring.resources;

import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jersey.jaspring.common.result.Json;
import jersey.jaspring.conf.errorhandling.CustomReasonPhraseException;
import jersey.jaspring.entity.Ticket;
import jersey.jaspring.service.TicketService;

@Singleton
@Path("ticket")
@Component
public class TicketResource {
	
	private static final Logger logger = Logger.getLogger(TicketResource.class.getName());
	
	@Autowired
	private TicketService service;

	// @GET
	// @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	// public List<Ticket> list() throws CustomReasonPhraseException {
	//// try {
	//// return service.list();
	//// } catch (CustomReasonPhraseException e) {
	//// throw new CustomReasonPhraseException(1, "ticket 查询错误");
	//// }
	// return Response.status(Response.Status.OK)
	// .entity(manifestAttributes)
	// .build();
	// }

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response list() throws CustomReasonPhraseException {
		return Response.status(Response.Status.OK).entity(service.list()).build();
	}

	// @GET
	// @Path("{id}")
	// @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// public Ticket get(@PathParam("id") int id) {
	// try {
	// return service.get(id);
	// } catch (Exception e) {
	// Response r = Response.status(500).entity(e.getMessage()).build();
	// throw new Exceptions.MyException(r);
	// }
	// }

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response get(@PathParam("id") int id) throws Exception {
		return Response.status(Response.Status.OK).entity(service.get(id)).build();
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("id") int id) {
		Json json = new Json();
		try {
			service.delete(id);
			json.setMsg("删除成功");
			json.setSuccess(false);
			return Response.status(Response.Status.OK).entity(json).build();
		} catch (CustomReasonPhraseException e) {
			json.setMsg("删除失败");
			return Response.status(Response.Status.OK).entity(json).build();
		}
		// Json json = new Json();
		// try {
		// service.delete(id);
		// json.setCode("X001");
		// json.setMsg("删除成功");
		// json.setSuccess(true);
		// return json;
		// } catch (Exception e) {
		//
		// throw new Exceptions.MyException(r);
		// }
	}
	
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response add(@FormParam("id") int id,@FormParam("name") String name) {
		Json json = new Json();
		Ticket ticket = new Ticket(id,name);
		try{
			service.add(ticket);
			json.setMsg("添加成功");
			return Response.status(Response.Status.OK).entity(json).build();
		}catch(CustomReasonPhraseException ex){
			logger.info(ex.getMessage());
			json.setMsg("添加失败");
			json.setSuccess(false);
			return Response.status(Response.Status.OK).entity(json).build();
		}
	}
	
//	@POST
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response add(Ticket ticket) {
//		Json json = new Json();
//		try{
//			service.add(ticket);
//			json.setMsg("添加成功");
//			return Response.status(Response.Status.OK).entity(json).build();
//		}catch(CustomReasonPhraseException ex){
//			logger.info(ex.getMessage());
//			json.setMsg("删除失败");
//			json.setSuccess(false);
//			return Response.status(Response.Status.OK).entity(json).build();
//		}
//	}
}
