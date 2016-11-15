package jersey.jaspring.conf.filter;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;

//@Provider
public class WebApplicationExceptionFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        System.out.println("WebApplicationExceptionFilter.preFilter() enter");
        String path = context.getUriInfo().getPath();
        System.out.println("path:"+path);
        System.out.println("WebApplicationExceptionFilter.preFilter() exit");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("WebApplicationExceptionFilter.postFilter() enter");
        if (responseContext.hasEntity() && responseContext.getEntity().equals("Response Exception")) {
            throw new WebApplicationException(Response.Status.OK);
        }
        System.out.println("WebApplicationExceptionFilter.postFilter() exit");
    }
}