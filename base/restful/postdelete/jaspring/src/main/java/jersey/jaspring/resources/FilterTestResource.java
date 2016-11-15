package jersey.jaspring.resources;

//import java.util.logging.Logger;
//
//import javax.inject.Singleton;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.springframework.stereotype.Service;
//
//import jersey.jaspring.exception.Exceptions;
//
@Deprecated
//@Singleton
//@Path("filter")
//@Service
public class FilterTestResource {
//	private static final Logger logger = Logger.getLogger(FilterTestResource.class.getName());
	
//	@Provider
//    public static class MyResponseFilter implements ContainerResponseFilter {
//
//        @Override
//        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//            System.out.println("MyResponseFilter.postFilter() enter");
//            System.out.println("method:"+requestContext.getMethod());
//            System.out.println("uri:"+requestContext.getUriInfo());
////            responseContext.setEntity(
////                    responseContext.getEntity() + ":" + getClass().getSimpleName(), null, MediaType.TEXT_PLAIN_TYPE);
//            System.out.println("MyResponseFilter.postFilter() exit");
//        }
//    }

//    @Provider
//    static class WebApplicationExceptionFilter implements ContainerRequestFilter, ContainerResponseFilter {
//
//        @Override
//        public void filter(ContainerRequestContext context) throws IOException {
//            System.out.println("WebApplicationExceptionFilter.preFilter() enter");
//            String path = context.getUriInfo().getPath();
//            System.out.println("path:"+path);
//            System.out.println("WebApplicationExceptionFilter.preFilter() exit");
//        }
//
//        @Override
//        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//            System.out.println("WebApplicationExceptionFilter.postFilter() enter");
//            if (responseContext.hasEntity() && responseContext.getEntity().equals("Response Exception")) {
//                throw new WebApplicationException(Response.Status.OK);
//            }
//            System.out.println("WebApplicationExceptionFilter.postFilter() exit");
//        }
//    }
    
//    @GET
//	@Produces(MediaType.TEXT_PLAIN+ ";charset=utf-8")
//	public String defaultmethod() {
//		logger.info("filter getName");
//		return "你还啊啊啊啊啊";
//	}
//	
//	
//	@GET
//	@Path("{name}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getHello(@PathParam("name") String name) {
//		logger.info("filter get :"+name);
//		if("a".equals(name)){
//			Response r = Response.status(500).entity(name).build();
//	        throw new Exceptions.MyException(r);
//		}
//		return "name";
//	}
//	
//	@GET
//	@Path("name")
//	@Produces(MediaType.TEXT_PLAIN+ ";charset=utf-8")
//	public String getName() {
//		logger.info("filter getName");
//		Response r = Response.status(500).entity("你好啊，错误请求").build();
//	    throw new WebApplicationException(r);
//	}
}
