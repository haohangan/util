package test.demo;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import test.demo.endpoint.StudentController;

@Component
@ApplicationPath("/rs")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
//        registerEndpoints();
    	packages("test.demo.endpoint");
    }

    private void registerEndpoints() {
         register(StudentController.class);
    }
}
