package jersey.jaspring.conf.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class MyCsrfProtectionFilter implements ContainerRequestFilter {

    /**
     * Name of the header this filter will attach to the request.
     */
    public static final String HEADER_NAME = "X-Requested-By";

    private static final Set<String> METHODS_TO_IGNORE;
    static {
        HashSet<String> mti = new HashSet<>();
        mti.add("GET");
        mti.add("POST");
        mti.add("DELETE");
        mti.add("PUT");
        mti.add("OPTIONS");
        mti.add("HEAD");
        METHODS_TO_IGNORE = Collections.unmodifiableSet(mti);
    }

    @Override
    public void filter(ContainerRequestContext rc) throws IOException {
        if (!METHODS_TO_IGNORE.contains(rc.getMethod()) && !rc.getHeaders().containsKey(HEADER_NAME)) {
            throw new BadRequestException();
        }
    }

}
