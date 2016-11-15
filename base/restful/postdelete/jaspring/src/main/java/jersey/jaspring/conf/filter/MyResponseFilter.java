package jersey.jaspring.conf.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class MyResponseFilter  implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("MyResponseFilter.postFilter() enter");
        System.out.println("method:"+requestContext.getMethod());
        System.out.println("uri:"+requestContext.getUriInfo().getPath());
//        responseContext.setEntity(
//                responseContext.getEntity() + ":" + getClass().getSimpleName(), null, MediaType.TEXT_PLAIN_TYPE);
        System.out.println("MyResponseFilter.postFilter() exit");
    }
}
