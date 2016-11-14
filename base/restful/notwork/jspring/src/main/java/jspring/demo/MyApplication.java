package jspring.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import jspring.demo.resources.DemoResource;
import jspring.demo.resources.JerseyResource;

public class MyApplication extends ResourceConfig{
   
	public MyApplication(){
		register(RequestContextFilter.class);
        register(DemoResource.class);
        register(JerseyResource.class);
	}
}
