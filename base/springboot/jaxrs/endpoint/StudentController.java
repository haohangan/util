package test.demo.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/students")
@Component
@Scope("prototype")
@Produces("application/json")
public class StudentController {
    
	@GET
	public List<String> getAllStudents() {
		List<String> l = new ArrayList<>();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		return l;
	}
}
