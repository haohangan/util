package jersey.jaspring.conf.errorhandling;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import jersey.jaspring.conf.filter.AppConstants;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable exception) {

		ErrorMessage errorMessage = new ErrorMessage();
		setHttpStatus(exception, errorMessage);
		errorMessage.setCode(AppConstants.GENERIC_APP_ERROR_CODE);
		errorMessage.setMessage(exception.getMessage());
		StringWriter errorStackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(errorStackTrace));
		errorMessage.setDeveloperMessage(errorStackTrace.toString());
		errorMessage.setLink(AppConstants.BLOG_POST_URL);

		return Response.status(errorMessage.getStatus()).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
	}

	private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
		if (ex instanceof WebApplicationException) { // NICE way to combine both
														// of methods, say it in
														// the blog
			errorMessage.setStatus(((WebApplicationException) ex).getResponse().getStatus());
		} else {
			errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); // defaults
																							// to
																							// internal
																							// server
																							// error
																							// 500
		}
	}
}
