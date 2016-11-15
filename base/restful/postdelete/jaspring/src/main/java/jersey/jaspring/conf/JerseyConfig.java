package jersey.jaspring.conf;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import jersey.jaspring.conf.filter.MyCsrfProtectionFilter;

public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
        register(RequestContextFilter.class);
//        register(FilterTestResource.MyResponseFilter.class);
        register(MyCsrfProtectionFilter.class);
        packages("jersey.jaspring");//resource所在的包路径
        register(LoggingFeature.class);
        EncodingFilter.enableFor(this, GZipEncoder.class); 
//        EncodingFilter.enableFor(this, );
    }
}
