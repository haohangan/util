package jersey.jaspring.conf.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AppExceptionMapper implements ExceptionMapper<AppException> {

	@Override
	public Response toResponse(AppException exception) {
		return Response.status(exception.getStatus()).entity(new ErrorMessage(exception))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
